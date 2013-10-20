import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class ImageProcessingTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new ImageProcessingFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ImageProcessingFrame extends JFrame
{
	public ImageProcessingFrame()
	{
		setTitle("ImageProcessingTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		
		add(new JComponent()
		{
			public void paintComponent(Graphics g)
			{
				if(image != null) g.drawImage(image,0,0,null);
			}
		});

		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent  event)
			{
				openFile();
			}
		});
		fileMenu.add(openItem);

		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent  event)
			{
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);

		JMenu editMenu = new JMenu("Edit");
		JMenuItem blurItem = new JMenuItem("Blur");
		blurItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent  event) //模糊效果
			{
				float weight = 1.0f/9.0f;
				float[] elements = new float[9];
				for(int i = 0 ; i< 9;i++)
				{
					elements[i] = weight;
				}
				convolve(elements);
			}
		});
		editMenu.add(blurItem);

		JMenuItem sharpenItem = new JMenuItem("Sharpen");
		sharpenItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				float[] elements = {0.0f,-1.0f,0.0f,-1.0f,5.f,-1.0f,0.0f,-1.0f,0.0f};
				convolve(elements);
			}
		});
		editMenu.add(sharpenItem);

		JMenuItem brightenItem = new JMenuItem("Brighten");
		brightenItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				float a = 1.1f;
				float b = 0;
				RescaleOp op = new RescaleOp(a,b,null);
				filter(op);
			}
		});
		editMenu.add(brightenItem);

		JMenuItem edgeDetectItem = new JMenuItem("Edge Detect");
		edgeDetectItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				float[] elements = {0.0f,-1.0f,0.0f,-1.0f,4.f,-1.0f,0.0f,-1.0f,0.0f};
				convolve(elements);
			}
		});
		editMenu.add(edgeDetectItem);

		JMenuItem negativeItem = new JMenuItem("Negative");
		negativeItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				short[] negative = new short[256];
				for(int i = 0; i< 256;i++)
				{
					negative[i] = (short)(255-i);
				}
				ShortLookupTable tabel = new ShortLookupTable(0,negative);
				LookupOp op = new LookupOp(tabel,null);
				filter(op);
			}
		});
		editMenu.add(negativeItem);

		JMenuItem rotateItem = new JMenuItem("Rotate");
		rotateItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if(image == null) return;
				AffineTransform transform = AffineTransform.getRotateInstance(Math.toRadians(5),image.getWidth()/2,image.getHeight()/2);
				AffineTransformOp op = new AffineTransformOp(transform,AffineTransformOp.TYPE_BICUBIC);
				filter(op);
			}
		});
		editMenu.add(rotateItem);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
	}

	public void openFile()
	{
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		String[] extensions = ImageIO.getReaderFileSuffixes();
		chooser.setFileFilter(new FileNameExtensionFilter("Image File",extensions));
		int r = chooser.showOpenDialog(this);
		if( r != JFileChooser.APPROVE_OPTION) return;

		try
		{
			Image img = ImageIO.read(chooser.getSelectedFile());
			image = new BufferedImage(img.getWidth(null),img.getHeight(null),BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(img,0,0,null);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(this,e);
		}
		repaint();
	}

	private void filter(BufferedImageOp op)
	{
		if(image == null) return;

		image = op.filter(image,null);
		repaint();
	}
	private void convolve(float[] elements)
	{
		Kernel kernel = new Kernel(3,3,elements);
		ConvolveOp op = new ConvolveOp(kernel);
		filter(op);
	}

	private BufferedImage image;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
}

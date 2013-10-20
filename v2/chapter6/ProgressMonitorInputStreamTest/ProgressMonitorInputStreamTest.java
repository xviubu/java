import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class ProgressMonitorInputStreamTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new textFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class textFrame extends JFrame
{
	public textFrame()
	{
		setTitle("ProgressMonitorInputStreamTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		textArea = new JTextArea();
		add(new JScrollPane(textArea));

		chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu  fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		openItem  = new JMenuItem("Open");
		openItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					openFile();
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
		});

		fileMenu.add(openItem);
		exitItem  = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);

	}

	public void openFile() throws IOException
	{
		int r = chooser.showOpenDialog(this);
		if(r!= JFileChooser.APPROVE_OPTION) return;
		final File f = chooser.getSelectedFile();

		FileInputStream fileIn = new FileInputStream(f);
		ProgressMonitorInputStream  progressIn = new ProgressMonitorInputStream(this,"Reading"+ f.getName(),fileIn);
		final Scanner in = new Scanner(progressIn);

		textArea.setText("");

		SwingWorker<Void,Void> worker = new SwingWorker<Void,Void>()
		{
			protected Void doInBackground() throws Exception
			{
				while(in.hasNext())
				{
					String line = in.nextLine();
					textArea.append(line);
					textArea.append("\n");
				}
				in.close();
				return null;
			}
		};

		worker.execute();
	}

	private JMenuItem openItem;
	private JMenuItem exitItem;
	private JTextArea textArea;
	private JFileChooser chooser;
	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class RasterImageTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new RasterImageFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}

		});
	}
}
class  RasterImageFrame extends JFrame
{
	public RasterImageFrame()
	{
		setTitle("RasterImageTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		BufferedImage image = makeMandelbrot(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		add(new JLabel(new ImageIcon(image)));
	}

	public BufferedImage makeMandelbrot(int width,int height)
	{
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB); //构建一个图片
		WritableRaster raster = image.getRaster(); //获得修改图像像素的光栅
		ColorModel model = image.getColorModel(); //获得图像的颜色模型

		//用red填充没一个像素
		Color fractalColor = Color.red;
		int argb = fractalColor.getRGB();
		Object colorData = model.getDataElements(argb,null);

		for(int i =0 ;i< width;i++)
			for(int j = 0;j < height ;j++)
			{
				double a = XMIN + i * (XMAX -  XMIN)/width;
				double b = YMIN + j * (YMAX - YMIN)/height;
				if(!escapesToInfinity(a,b)) raster.setDataElements(i,j,colorData);

			}
		return image;

	}

	private boolean escapesToInfinity(double a,double b)
	{
		double x = 0.0;
		double y = 0.0;
		int iterations = 0;
		while(x <= 2 && y <= 2 && iterations <  MAX_ITERATIONS)
		{
			double xnew = x*x - y*y +a;
			double ynew = 2*x*y + b;
			x = xnew;
			y = ynew;
			iterations++;
		}
		return x>2 || y>2;
	}

	private static final double XMIN = -2;
	private static final double XMAX = 2;
	private static final double YMIN = -2;
	private static final double YMAX = 2;
	private static final int MAX_ITERATIONS = 16;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 400;
}

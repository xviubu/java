import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class DrawPerson
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new PersonFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class PersonFrame extends JFrame
{
	public PersonFrame()
	{
		setTitle("DrawPerson");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		JComponent thinPerson = new PersonThinComponent();
		add(thinPerson);

	}

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 400;
}

class PersonThinComponent extends JComponent
{
 	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.draw(new Ellipse2D.Double(50,20,30,30));
		g2.draw(new Rectangle2D.Double(60,50,10,50));
		g2.draw(new Line2D.Double(60,50,40,100));
		g2.draw(new Line2D.Double(70,50,90,100));
		g2.draw(new Line2D.Double(60,100,45,150));
		g2.draw(new Line2D.Double(70,100,85,150));
	}
}



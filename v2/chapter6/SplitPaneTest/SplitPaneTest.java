import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class SplitPaneTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new SplitPaneFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class SplitPaneFrame extends JFrame
{
	public SplitPaneFrame()
	{
		setTitle("SplitPaneTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		final JList planetList = new JList(planets);
		final JLabel planetImage = new JLabel();
		final JTextArea planetDescription = new JTextArea();

		planetList.addListSelectionListener(new ListSelectionListener()
		{
			public void valueChanged(ListSelectionEvent event)
			{
				Planet value = (Planet)planetList.getSelectedValue(); 

				planetImage.setIcon(value.getImage());
				planetDescription.setText(value.getDescription());
			}
		});

		JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,planetList,planetImage); //新建一个竖直的分割面板

		innerPane.setContinuousLayout(true); //设置连续布局
		innerPane.setOneTouchExpandable(true); //一触即展

		JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,innerPane,planetDescription);
		add(outerPane,BorderLayout.CENTER);

	}
	private Planet[] planets = { new Planet("Mercury", 2440, 0), new Planet("Venus", 6052, 0),
         new Planet("Earth", 6378, 1), new Planet("Mars", 3397, 2),
         new Planet("Jupiter", 71492, 16), new Planet("Saturn", 60268, 18),
         new Planet("Uranus", 25559, 17), new Planet("Neptune", 24766, 8),
         new Planet("Pluto", 1137, 1), };
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 300;
}

class Planet
{
   public Planet(String n, double r, int m)
   {
      name = n;
      radius = r;
      moons = m;
      image = new ImageIcon(name + ".gif");
   }

   public String toString()
   {
      return name;
   } 

   public String getDescription()
   {
      return "Radius: " + radius + "\nMoons: " + moons + "\n";
   }

   public ImageIcon getImage()
   {
      return image;
   }

   private String name;
   private double radius;
   private int moons;
   private ImageIcon image;
}

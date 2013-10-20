
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class TabbedPaneTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new TabbedPaneFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class TabbedPaneFrame extends JFrame
{
	public TabbedPaneFrame()
	{
		setTitle("TabbedPaneTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		tabbedPane = new JTabbedPane();

		ImageIcon icon = new ImageIcon("yellow-ball,gif");
		tabbedPane.addTab("Mercury",icon,null);
		tabbedPane.addTab("Venus",icon,null);
		tabbedPane.addTab("Earth",icon,null);
		tabbedPane.addTab("Mars",icon,null);
		tabbedPane.addTab("Jupiter",icon,null);
		tabbedPane.addTab("Saturn",icon,null);
		tabbedPane.addTab("Uranus",icon,null);
		tabbedPane.addTab("Neptune",icon,null);
		tabbedPane.addTab("Pluto",icon,null);

		final int plutoIndex = tabbedPane.indexOfTab("Pluto"); //得到索引
		JPanel plutoPanel = new JPanel();
		plutoPanel.add(new JLabel("Pluto",icon,SwingConstants.LEADING));
		JToggleButton plutoCheckBox = new JCheckBox();
		plutoCheckBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				tabbedPane.remove(plutoIndex);
			}
		});
		plutoPanel.add(plutoCheckBox);
		tabbedPane.setTabComponentAt(plutoIndex,plutoPanel); //设置选项板

		add(tabbedPane,"Center");

		tabbedPane.addChangeListener(new ChangeListener()
		{
			public void stateChanged(ChangeEvent event)
			{
				if(tabbedPane.getSelectedComponent() == null)
				{
					int n = tabbedPane.getSelectedIndex();
					loadTab(n);
				}
			}
		});
		loadTab(0);

		JPanel buttonPanel = new JPanel();
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton wrapButton = new JRadioButton("Wrap tabs");
		wrapButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			}
		});
		buttonPanel.add(wrapButton);
		buttonGroup.add(wrapButton);
		wrapButton.setSelected(true);

		JRadioButton scrollButton = new JRadioButton("Scroll tabs");
		scrollButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			}
		});
		buttonPanel.add(scrollButton);
		buttonGroup.add(scrollButton);
		add(buttonPanel,BorderLayout.SOUTH);


	}

	public void loadTab(int n)
	{
		String title = tabbedPane.getTitleAt(n);
		ImageIcon planetIcon = new ImageIcon(title+".gif");
		tabbedPane.setComponentAt(n,new JLabel(planetIcon));
		tabbedPane.setIconAt(n,new ImageIcon("red-ball.gif"));

	}

	private JTabbedPane tabbedPane;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}


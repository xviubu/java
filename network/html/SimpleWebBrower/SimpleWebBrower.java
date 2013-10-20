import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SimpleWebBrower
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new SimpleWebBrowerFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class SimpleWebBrowerFrame extends JFrame
{
	public SimpleWebBrowerFrame()
	{

		setTitle("SimpleWebBrower");
		setSize(512,342);
		
		urlField = new JTextField();	
		urlField.setEditable(true);
		urlField.addKeyListener(new updateURLListener());


		jep = new JEditorPane();
		jep.setEditable(false);
		jep.addHyperlinkListener(new LinkFollowListener(jep));

		JScrollPane scrollPane = new JScrollPane(jep);
		add(urlField,BorderLayout.PAGE_START);
		add(scrollPane,BorderLayout.CENTER);

	}
	class updateURLListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent event)
		{
			if(event.getKeyCode() == KeyEvent.VK_ENTER)
			{
				initialPage = urlField.getText();
				try
				{
					jep.setPage(initialPage);
				}
				catch(IOException ex)
				{
					System.err.println(ex);
					System.exit(-1);
				}

			}
		}
	}
	private String initialPage;
	private JTextField urlField;
	private JEditorPane jep;
}
class LinkFollowListener implements HyperlinkListener	
{
	public LinkFollowListener(JEditorPane pane)
	{
		this.pane = pane;
	}

	public void hyperlinkUpdate(HyperlinkEvent event)
	{
		if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
		{
			try
			{
				pane.setPage(event.getURL());	
			}
			catch(Exception ex)
			{
				pane.setText("<html>Could not load"+event.getURL() +"</html>");
			}
		}
	}

	private JEditorPane pane;
}

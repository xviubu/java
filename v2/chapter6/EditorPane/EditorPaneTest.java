import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class EditorPaneTest
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new EditorPaneFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class EditorPaneFrame extends JFrame
{
	public EditorPaneFrame()
	{
		setTitle("EditorPaneTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		final Stack<String> urlStack = new Stack<String>();
		final JEditorPane editorPane = new JEditorPane();
		final JTextField url = new JTextField(30);

		editorPane.setEditable(false);
		editorPane.addHyperlinkListener(new HyperlinkListener()
		{
			public void hyperlinkUpdate(HyperlinkEvent event)
			{
				if(event.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
				{
					try
					{
						urlStack.push(event.getURL().toString());
						url.setText(event.getURL().toString());
						editorPane.setPage(event.getURL());
					}	
					catch(IOException e)
					{
						editorPane.setText("Exception"+e);
					}
				}
			}
		});

		final JCheckBox editable = new JCheckBox();
		editable.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				editorPane.setEditable(editable.isSelected());
			}
		});

		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					urlStack.push(url.getText());
					editorPane.setPage(url.getText());
				}
				catch(IOException e)
				{
					editorPane.setText("Exception"+e);
				}
			}
		};

		JButton loadButton = new JButton("Load");
		loadButton.addActionListener(listener);
		url.addActionListener(listener);
		
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if(urlStack.size() <= 1) return;
				try
				{
					urlStack.pop();
					String urlString = urlStack.peek();
					url.setText(urlString);
					editorPane.setPage(urlString);
				}
				catch(IOException e)
				{
					editorPane.setText("Exception"+e);
				}
			}
		});
		add(new JScrollPane(editorPane),BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.add(new JLabel("URL"));
		panel.add(url);
		panel.add(loadButton);
		panel.add(backButton);
		panel.add(new JLabel("Editable"));
		panel.add(editable);

		add(panel,BorderLayout.SOUTH);


	}

	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;
}

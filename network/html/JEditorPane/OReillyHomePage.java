import java.net.*;
import javax.swing.*;
import javax.swing.text.*;
import java.io.*;
import java.awt.*;


public class OReillyHomePage
{
	public static void main(String[] args)
	{
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					JFrame frame = new JEPFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			});
	}
}

class JEPFrame extends JFrame
{
	public JEPFrame()
	{
		setTitle("Localhost");
		setSize(512,342);
		JEditorPane jep = new JEditorPane();
		jep.setEditable(false);
		try
		{
			jep.setPage("http://localhost");
		}
		catch(IOException ex)
		{
			jep.setContentType("text/html");
			jep.setText("<html>Could not loda http://localhost </html>");
		}
		JScrollPane scrollPane = new JScrollPane(jep);
		add(scrollPane);
	}
}

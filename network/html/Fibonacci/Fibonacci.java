import java.awt.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.*;

public class Fibonacci
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new FibonacciFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class FibonacciFrame extends JFrame
{
	public FibonacciFrame()
	{
		setTitle("Fibonacci Sequence");
		setSize(512,342);

		result = createFibonacci();
		JEditorPane jep = new JEditorPane("text/html",result.toString());
		jep.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(jep);
		add(scrollPane);
	}
	public StringBuffer createFibonacci()
	{
		StringBuffer result = new StringBuffer("<html><body><h1>Fibonacci Sequence</h1><ol>");
		long f1 = 0;
		long f2 = 1;
		for(int i = 0;i < 50;i++)
		{
			result.append("<li>");	
			result.append(f1);
			long tmp = f2;
			f2 = f1 + f2;
			f1 = tmp;
		}

		return result;
	}

	private StringBuffer result;
}

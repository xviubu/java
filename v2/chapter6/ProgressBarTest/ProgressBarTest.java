import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class ProgressBarTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new ProgressBarFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ProgressBarFrame extends JFrame
{
	public ProgressBarFrame()
	{
		setTitle("ProgressBarTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		textArea = new JTextArea();

		final int MAX = 1000;
		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		progressBar = new JProgressBar(0,MAX);
		progressBar.setStringPainted(true);
		panel.add(startButton);
		panel.add(progressBar);

		checkBox = new JCheckBox("indeterminate");
		checkBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				progressBar.setIndeterminate(checkBox.isSelected()); //设置不确定
				progressBar.setStringPainted(!progressBar.isIndeterminate());
			}
		});
		panel.add(checkBox);
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);

		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				startButton.setEnabled(false);
				activity = new SimulatedActivity(MAX);
				activity.execute();
			}
		});
	}

	class SimulatedActivity extends SwingWorker<Void,Integer>
	{
		public SimulatedActivity(int target)
		{
			this.current = 0;
			this.target = target;
		}

		protected Void doInBackground() throws Exception
		{
			try
			{
				while(current < target)
				{
					Thread.sleep(100);
					current++;
					publish(current);
				}
			}
			catch(InterruptedException e)
			{

			}
			return null;
		}
		protected void process(List<Integer> chunks)
		{
			for(Integer chunk : chunks)
			{
				textArea.append(chunk + "\n");
				progressBar.setValue(chunk);
			}
		}

		protected void done()
		{
			startButton.setEnabled(true);
		}

		private int current;
		private int target;
	}

	private JButton startButton;
	private JProgressBar progressBar;
	private JTextArea textArea;
	private JCheckBox checkBox;
	private SimulatedActivity activity;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

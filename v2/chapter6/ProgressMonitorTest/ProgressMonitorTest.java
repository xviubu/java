import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ProgressMonitorTest
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new ProgressMonitorFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
class ProgressMonitorFrame extends JFrame
{
	public ProgressMonitorFrame()
	{
		setTitle("ProgressMonitorTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		textArea = new JTextArea();

		JPanel panel = new JPanel();
		startButton = new JButton("Start");
		panel.add(startButton);

		add(new JScrollPane(textArea),BorderLayout.CENTER);
		add(panel,BorderLayout.SOUTH);

		startButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				startButton.setEnabled(false);
				final int MAX = 1000;

				activity = new SimulatedActivity(MAX);
				activity.execute();

				progressDialog = new ProgressMonitor(ProgressMonitorFrame.this,"Waiting for Simulated Activity",null,0,MAX); //父主件
				cancelMonitor.start();
			}
		});

		cancelMonitor = new Timer(500,new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				if(progressDialog.isCanceled())
				{
					activity.cancel(true);
					startButton.setEnabled(true);
				}
				else if(activity.isDone())
				{
					progressDialog.close();
					startButton.setEnabled(true);
				}
				else
				{
					progressDialog.setProgress(activity.getProgress());
				}
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
					textArea.append(current+"\n");
					setProgress(current); //设置值
				}
			}
			catch(InterruptedException e)
			{

			}
			return null;
		}
		private int current;
		private int target;
	}

	private Timer cancelMonitor;
	private JButton startButton;
	private ProgressMonitor progressDialog;
	private JTextArea textArea;
	private SimulatedActivity activity;

	public static final int DEFAULT_WIDTH = 400;
	public static final int DEFAULT_HEIGHT = 200;
}

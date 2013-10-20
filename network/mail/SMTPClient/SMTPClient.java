import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.swing.*;


public class SMTPClient 
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new SMTPFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class SMTPFrame extends JFrame
{
	public SMTPFrame()
	{
		setTitle("SMTPClient");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(4,2));
		northPanel.add(new JLabel("SMTP Server:"));
		SMTPServer = new JTextField(40);
		northPanel.add(SMTPServer);
		northPanel.add(new JLabel("To:"));
		To = new JTextField(40);
		northPanel.add(To);
		northPanel.add(new JLabel("From:"));
		From = new JTextField(40);
		northPanel.add(From);
		northPanel.add(new JLabel("Subject:"));
		Subject = new JTextField(40);
		northPanel.add(Subject);

		JPanel centerPanel = new JPanel();
		Content = new JTextArea(30,70);
		JScrollPane scrollPane = new JScrollPane(Content);
		centerPanel.add(scrollPane);

		JPanel southPanel = new JPanel();
		sendButton = new JButton("Send");
		sendButton.addActionListener(new sendActionListener());
		southPanel.add(sendButton);



		add(northPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);

	}

	class sendActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			try
			{
				Properties props = new Properties();		
				props.put("mail.host",SMTPServer.getText());

				Session mailConnection = Session.getInstance(props,null);
				final Message msg = new MimeMessage(mailConnection);

				Address to = new InternetAddress(To.getText());
				Address from = new InternetAddress(From.getText());

				msg.setFrom(from);
				msg.setRecipient(Message.RecipientType.TO,to);
				msg.setSubject(Subject.getText());
				msg.setContent(Content.getText(),"text/plain");

				Runnable r = new Runnable()
				{
					public void run()
					{
						try
						{
							Transport.send(msg);
						}
						catch(Exception ex)
						{
							ex.printStackTrace();
						}
					}
				};
				Thread t = new Thread(r);
				t.start();

				Content.setText("");


			}
			catch(Exception ex)
			{
				 JOptionPane.showInternalMessageDialog(SMTPFrame.this,ex,"Error",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	private JTextField SMTPServer;
	private JTextField To;
	private JTextField From;
	private JTextField Subject;
	private JTextArea  Content;
	private JButton sendButton;
	
}

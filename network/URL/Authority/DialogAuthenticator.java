import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogAuthenticator extends Authenticator
{
	public DialogAuthenticator()
	{
		this("",new JFrame());
	}
	public DialogAuthenticator(String username)
	{
		this(username,new JFrame());
	}
	public DialogAuthenticator(JFrame parent)
	{
		this("",parent);
	}
	public DialogAuthenticator(String username,JFrame parent)
	{
		passwordDialog = new JDialog(parent,true);
		Container pane = passwordDialog.getContentPane();
		pane.setLayout(new GridLayout(4,1));
		
		mainLabel = new JLabel("Please enter username and password: ");
		pane.add(mainLabel);

		JPanel p2 = new JPanel();
		JLabel userLabel = new JLabel("Username: ");
		usernameField = new JTextField(20);
		ActionListener al = new OKResponse();
		usernameField.addActionListener(al);
		p2.add(userLabel);
		p2.add(usernameField);

		JPanel p3 =new JPanel();
		JLabel passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField(20);
		passwordField.addActionListener(al);
		p3.add(passwordLabel);
		p3.add(passwordField);

		JPanel p4 = new JPanel();
		okButton = new JButton("OK");
		okButton.addActionListener(al);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelResponse());
		p4.add(okButton);
		p4.add(cancelButton);

		pane.add(p2);
		pane.add(p3);
		pane.add(p4);

		passwordDialog.pack();
		

	}

	private void show()
	{
		String prompt = getRequestingPrompt();
		if(prompt == null)
		{
			String site = getRequestingSite().getHostName();
			String protocol = getRequestingProtocol();
			int port = getRequestingPort();
			if(site != null && protocol != null)
			{
				prompt = protocol + "://" + site;
				if(port > 0)
				{
					prompt += ":" + port;
				}
			}
			else
			{
				prompt = "";
			}
		}
		mainLabel.setText("Please enter username and password for " + prompt + ": ");
		passwordDialog.pack();
		passwordDialog.show();
	}

	class OKResponse implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			passwordDialog.hide();
			char[] password = passwordField.getPassword();
			String username = usernameField.getText();

			passwordField.setText("");
			response = new PasswordAuthentication(username,password);
		}
	}

	class CancelResponse implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			passwordDialog.hide();
			passwordField.setText("");
			response = null;
		}
	}
	public PasswordAuthentication getPasswordAuthentication()
	{
		return response;
	}
	private PasswordAuthentication response;
	private JDialog passwordDialog;
	private JTextField usernameField;
	private JPasswordField  passwordField;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel mainLabel;
}

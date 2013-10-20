import javax.mail.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MailAuthenticator extends Authenticator
{
	public MailAuthenticator()
	{
		this("");
	}

	public MailAuthenticator(String username)
	{
		Container pane = passwordDialog.getContentPane();
		pane.setLayout(new GridLayout(4,1));
		pane.add(mainLabel);

		JPanel p2 = new JPanel();
		p2.add(userLabel);
		p2.add(usernameField);
		usernameField.setText(username);
		pane.add(p2);

		JPanel p3 = new JPanel();
		p3.add(passwordLabel);
		p3.add(passwordField);
		pane.add(p3);

		JPanel p4 = new JPanel();
		p4.add(okButton);
		pane.add(p4);

		passwordDialog.pack();

		ActionListener al = new HideDialog();
		okButton.addActionListener(al);
		usernameField.addActionListener(al);
		passwordField.addActionListener(al);
	}

	class HideDialog implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			passwordDialog.hide();
		}
	}

	public PasswordAuthentication getPasswordAuthentication()
	{
		passwordDialog.show();
		String password = new String(passwordField.getPassword());
		String username = usernameField.getText();

		passwordField.setText("");
		return new PasswordAuthentication(username,password);
	}

	private JDialog passwordDialog = new JDialog(new JFrame(),true);
	private JLabel mainLabel = new JLabel("Please enter your username and password : ");
	private JLabel userLabel = new JLabel("User name: ");
	private JLabel passwordLabel = new JLabel("password: ");
	private JTextField usernameField = new JTextField(20);
	private JPasswordField passwordField = new JPasswordField(20);
	private JButton okButton = new JButton("OK");
}

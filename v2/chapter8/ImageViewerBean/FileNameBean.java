import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.*;

public class FileNameBean extends JPanel
{
	public FileNameBean()
	{
		dialogButton = new JButton("...");	
		nameField = new JTextField(30);

		chooser = new JFileChooser();
		setPreferredSize(new Dimension(XPREFSIZE,YPREFSIZE));

		setLayout(new GridLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 100;
		gbc.weighty = 100;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		add(nameField,gbc);

		dialogButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				chooser.setFileFilter(new FileNameExtensionFilter(Arrays.toString(extensions),extensions));
				int r = chooser.showOpenDialog(null);
				if(r == JFileChooser.APPROVE_OPTION)
				{
					File f = chooser.getSelectedFile();		
					String name = f.getAbsolutePath();
					setFileName(name);

				}
			}
		});
		nameField.setEditable(false);

		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.fill  = GridBagConstraints.NONE;
		gbc.gridx = 1;
		add(dialogButton,gbc);
	}

	public void setFileName(String newValue)
	{
		String oldValue = nameField.getText();
		nameField.setText(newValue);
		firePropertyChange("fileName",oldValue,newValue);
	}
	public String getFileName()
	{
		return nameField.getText();
	}

	public String[] getExtensions()
	{
		return extensions;
	}
	public void setExtensions(String[] newValue)
	{
		extensions = newValue;
	}
	public String getExtensions(int i)
	{
		if(i >= 0 && i< extensions.length)
			return extensions[i];
		else
			return "";
	}
	public void setExtensions(int i,String newValue)
	{
		if(i>=0 && i<extensions.length)
			extensions[i] = newValue;
	}

	private static final int XPREFSIZE = 200;
	private static final int YPREFSIZE = 200;
	private JButton dialogButton;
	private JTextField nameField;
	private JFileChooser chooser;
	private String[] extensions = {"gif","png"};
}

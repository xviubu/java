import javax.swing.*;

public class HTMLLabelApplet extends JApplet
{
	public void init()
	{
		JLabel theText = new JLabel("<html>Hello! This is a multiline label with <b>bold</b> "
									+"and  <i>italic</i> text. <p> "
									+"It can use paragraphs,horizontal lines, <hr> "
									+"<font color=red>colors</font>"
									+"and most of the other basic features of HTML 3.2</html>");
		add(theText);
	}
}

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class ClassTree
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new ClassTreeFrame();		
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ClassTreeFrame extends JFrame
{
	public ClassTreeFrame()
	{
		setTitle("ClassTree");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		/*以object 类为根节点构建树 */
		root = new DefaultMutableTreeNode(java.lang.Object.class);
		model = new DefaultTreeModel(root);
		tree = new JTree(model);

		addClass(getClass());
		/*设置节点图标*/
		ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
		renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
		renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
		renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
		tree.setCellRenderer(renderer);
		
		tree.addTreeSelectionListener(new TreeSelectionListener()
		{
			public void valueChanged(TreeSelectionEvent event) //将选择的类的域描述放在textArea
			{
				TreePath path = tree.getSelectionPath();
				if(path == null) return;
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)path.getLastPathComponent();	
				Class<?> c = (Class<?>)	selectedNode.getUserObject();
				String description = getFieldDescription(c);
				textArea.setText(description);	
			}
		});
		/*设置为单选模式*/
		int model = TreeSelectionModel.SINGLE_TREE_SELECTION;
		tree.getSelectionModel().setSelectionMode(model);

		textArea = new JTextArea();

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JScrollPane(tree));
		panel.add(new JScrollPane(textArea));

		add(panel,BorderLayout.CENTER);

		addTextField();

	}

	public void addTextField()
	{
		JPanel panel = new JPanel();
		ActionListener addListener = new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				try
				{
					String text = textField.getText();
					addClass(Class.forName(text));
					textField.setText("");
				}
				catch(ClassNotFoundException e)
				{
					JOptionPane.showMessageDialog(null,"ClassNotFound");
				}
			}
		};

		textField = new JTextField(20);
		textField.addActionListener(addListener);
		panel.add(textField);

		JButton addButton = new JButton("Add");
		addButton.addActionListener(addListener);
		panel.add(addButton);

		add(panel,BorderLayout.SOUTH);
	}

	@SuppressWarnings("unchecked")
	public DefaultMutableTreeNode findUserObject(Object obj)
	{
		Enumeration<TreeNode> e = (Enumeration<TreeNode>) root.breadthFirstEnumeration();
		while(e.hasMoreElements())
		{
			DefaultMutableTreeNode node =(DefaultMutableTreeNode) e.nextElement();
			if(node.getUserObject().equals(obj))
				return node;
		}
		return null;
	}

	public DefaultMutableTreeNode addClass(Class<?> c)
	{
		if(c.isInterface() || c.isPrimitive()) return null;

		DefaultMutableTreeNode node = findUserObject(c);
		if(node != null) return node;

		Class<?> s = c.getSuperclass();

		DefaultMutableTreeNode parent;
		if(s == null) parent = root;
		else parent = addClass(s);

		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
		model.insertNodeInto(newNode,parent,parent.getChildCount());
		/* 显示插入节点*/
		TreePath path  = new TreePath(model.getPathToRoot(newNode));
		tree.makeVisible(path);

		return newNode;
	}

	public static String getFieldDescription(Class<?> c)
	{
		StringBuilder r = new StringBuilder();
		Field[] fields = c.getDeclaredFields();
		for(int i = 0; i< fields.length;i++)
		{
			Field f = fields[i];
			if((f.getModifiers() & Modifier.STATIC) != 0)
				r.append("static");
			r.append(f.getType().getName());
			r.append("");
			r.append(f.getName());
			r.append("\n");
		}

		return r.toString();
	}

	private DefaultMutableTreeNode root;
	private DefaultTreeModel model;
	private JTree tree;
	private JTextArea textArea;
	private JTextField textField;

	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT = 300;
}

class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer
{
	public Component getTreeCellRendererComponent(JTree tree,Object value,boolean selected,boolean expanded,boolean leaf,int row,boolean hasFocus)
	{
		Component comp = super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);

		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		Class<?> c = (Class<?>) node.getUserObject();

		if(plainFont == null)
		{
			plainFont = getFont();
			if(plainFont != null)
				italicFont = plainFont.deriveFont(Font.ITALIC);
		}

		if((c.getModifiers() & Modifier.ABSTRACT) == 0) setFont(plainFont);
		else comp.setFont(italicFont);
		return comp;
	}

	private Font plainFont = null;
	private Font italicFont = null;
}



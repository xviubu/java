import java.awt.*;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;

public class ObjectInpectorTest
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new ObjectInpectorFrame();		
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class ObjectInpectorFrame extends JFrame
{
	public ObjectInpectorFrame()
	{
		setTitle("ObjectInpectorTest");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		Variable v = new Variable(getClass(),"this",this);
		ObjectTreeModel model = new ObjectTreeModel();
		model.setRoot(v);
		tree = new JTree(model);
		add(new JScrollPane(tree),BorderLayout.CENTER);
	}
	private JTree tree;
	private static final int DEFAULT_WIDTH =400;
	private static final int DEFAULT_HEIGHT=300;
}

class ObjectTreeModel implements TreeModel
{
	public ObjectTreeModel()
	{
		root = null;
	}

	public void setRoot(Variable v)
	{
		Variable oldRoot = v;
		root = v;
		fireTreeStructureChanged(oldRoot);
	}

	public Object getRoot()
	{
		return root;
	}

	public int getChildCount(Object parent)
	{
		return ((Variable)parent).getFields().size();
	}

	public Object getChild(Object parent,int index)
	{
		ArrayList<Field> fields = ((Variable) parent).getFields();
		Field f = (Field) fields.get(index);
		Object parentValue = ((Variable) parent).getValue();
		try
		{
			return new Variable(f.getType(),f.getName(),f.get(parentValue));
		}
		catch(IllegalAccessException e)
		{
			return null;
		}
	}


	public int getIndexOfChild(Object parent,Object child)
	{
		int n = getChildCount(parent);
		for(int i =0 ;i<n;i++)
		{
			if(getChild(parent,i).equals(child))
				return i;
		}
		return -1;

	}

	public boolean isLeaf(Object node)
	{
		return getChildCount(node) == 0;
	}

	public void valueForPathChanged(TreePath path,Object newValue)
	{

	}
	public void addTreeModelListener(TreeModelListener l)
	{
		listenerList.add(TreeModelListener.class,l);
	}
	public void removeTreeModelListener(TreeModelListener l)
	{
		listenerList.remove(TreeModelListener.class,l);
	}
	public void fireTreeStructureChanged(Object oldRoot)
	{
		TreeModelEvent event = new TreeModelEvent(this,new Object[]{oldRoot});
		EventListener[] listeners = listenerList.getListeners(TreeModelListener.class);
		for(int i = 0;i<listeners.length;i++)
		{
			((TreeModelListener) listeners[i]).treeStructureChanged(event);
		}
	}

	private Variable root;
	private EventListenerList listenerList = new EventListenerList();

}

class Variable
{
	public Variable(Class<?> aType,String aName,Object aValue)
	{
		type = aType;
		name = aName;
		value = aValue;
		fields = new ArrayList<Field>();

		if(!type.isPrimitive() && !type.isArray() && !type.equals(String.class) && value != null)
		{
			for(Class<?> c = value.getClass();c!=null;c = c.getSuperclass())
			{
				Field[] fs = c.getDeclaredFields();
				AccessibleObject.setAccessible(fs,true);

				for(Field f:fs)
					if((f.getModifiers() & Modifier.STATIC) == 0) 
						fields.add(f);
			}

		}
	}

	public Object getValue()
	{
		return value;
	}

	public ArrayList<Field> getFields()
	{
		return fields;
	}

	public String toString()
	{
		String r = type + " " + name;
		if(type.isPrimitive()) r += ("=" + value);
		else if(type.equals(String.class)) r += ("=" + value);
		else if(value == null) r += "=null";
		
		return r;
	}

	private Class<?> type;
	private String name;
	private Object value;
	private ArrayList<Field> fields;
}

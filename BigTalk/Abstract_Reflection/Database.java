/*用反射机制改进抽象工厂模式*/
import java.lang.reflect.*;
import java.io.*;
import java.util.*;

public class Database
{
	public static void main(String[] args)
	{
		User user = new User();
		Department department = new Department();

		IUser iu =  DataAccess.createUser();
		IDepartment idp = DataAccess.createDepartment();
		iu.insertUser(user);
		iu.getUser(1);
		idp.insertDepartment(department);
		idp.getDepartment(1);
		
	}
}

class User
{
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	private int id;
	private String name;
}

interface IUser
{
  	public void insertUser(User user);	
	public User getUser(int id);
}

class SqlserverUser implements IUser
{
	public void insertUser(User user)
	{
		System.out.println("在 SQL Server 中给User表增加一条记录");
	}

	public User getUser(int id)
	{
		System.out.println("在 SQL Server 中根据ID得到User表中的一条记录");
		return null;
	}
}

class AccessUser implements IUser
{
	public void insertUser(User user)
	{
		System.out.println("在 Access 中给User表增加一条记录");
	}

	public User getUser(int id)
	{
		System.out.println("在 Access 中根据ID得到User表中的一条记录");
		return null;
	}
}

class Department
{
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getDeptName()
	{
		return deptName;
	}
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}
	private int id;
	private String deptName;
}

interface IDepartment
{
	public void insertDepartment(Department department);
	public Department getDepartment(int id);
}

class SqlserverDepartment implements IDepartment
{
	public void insertDepartment(Department department)
	{
		System.out.println("在 SQL Server 中给Department插入一条记录");
	}

	public Department getDepartment(int id)
	{
		System.out.println("在 SQL Server 中根据ID得到一条记录");
		return null;
	}
}

class AccessDepartment implements IDepartment
{
	public void insertDepartment(Department department)
	{
		System.out.println("在 Access 中给Department插入一条记录");
	}

	public Department getDepartment(int id)
	{
		System.out.println("在 Access 中根据ID得到一条记录");
		return null;
	}
}

class DataAccess
{
//	private static final String  UserClassName = "AccessUser";
//	private static final String  DepartmentClassName = "AccessDepartment";
	public  static String getUserProperty()  
	{
		Properties props = new Properties();
		try
		{
			props.load(new FileInputStream(new File("database.properties")));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return props.getProperty("User");
	}
	public static String getDepartmentProperty()
	{
		Properties props = new Properties();
		try
		{
			props.load(new FileInputStream(new File("database.properties")));
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		return props.getProperty("Department");
	}
	public static IUser createUser()	
	{
		String UserClassName =getUserProperty();
		IUser result = null;
		try
		{
			result = (IUser)Class.forName(UserClassName).newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

	public static IDepartment createDepartment()
	{
		String DepartmentClassName = getDepartmentProperty();
		IDepartment result = null;
		try
		{
			result = (IDepartment)Class.forName(DepartmentClassName).newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return result;
	}

}

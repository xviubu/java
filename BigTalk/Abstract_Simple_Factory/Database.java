/*用简单工厂模式改进 抽象工厂模式*/
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
	public static IUser createUser()
	{
		IUser result = null;
		switch(db)
		{
			case "Sqlserver":
				result = new SqlserverUser();
				break;
			case "Access":
				result = new AccessUser();
				break;
		}
		return result;
	}

	public static IDepartment createDepartment()
	{
		IDepartment result = null;
		switch(db)
		{
			case "Sqlserver":
				result = new SqlserverDepartment();
				break;
			case "Access":
				result = new AccessDepartment();
				break;
		}
		return result;
	}
//	private static final String db = "Sqlserver";
	private static final String db = "Access";
}

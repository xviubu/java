/*抽象工厂模式*/
public class Database
{
	public static void main(String[] args)
	{
		User user = new User();
		Department department = new Department();

		IFactory factory = new AccessFactory();
		IUser iu = factory.createUser();
		IDepartment idp = factory.createDepartment();
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
interface IFactory
{
	public IUser createUser();
	public IDepartment createDepartment();
}

class SqlserverFactory implements IFactory
{
	public IUser createUser()
	{
		return new SqlserverUser();
	}
	public IDepartment createDepartment()
	{
		return new SqlserverDepartment();
	}
}

class AccessFactory implements IFactory
{
	public IUser createUser()
	{
		return new AccessUser();
	}
	public IDepartment createDepartment()
	{
		return new AccessDepartment();
	}
}

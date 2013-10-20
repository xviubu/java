/*适配器模式，翻译者就是适配器*/
public class NBA
{
	public static void main(String[] args)
	{
		
		Player baidi = new Forwards("巴蒂尔");
		baidi.attack();

		Player maidi = new Guards("麦迪"); 
		maidi.attack();

		Player yaoming= new Translator("姚明");
		yaoming.attack();
		yaoming.defense();
	}

}
/*目标*/
abstract class Player
{
	public Player(String name)
	{
		this.name = name;
	}
	public abstract void attack();
	public abstract void defense();
	protected String name;
}

class Forwards extends Player
{
	public Forwards(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("前锋" + name + "进攻");
	}
	public void defense()
	{
		System.out.println("前锋" + name + "防守");
	}
}
class Center extends Player
{
	public Center(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("中锋" + name + "进攻");
	}
	public void defense()
	{
		System.out.println("中锋" + name + "防守");
	}
}
class Guards extends Player
{
	public Guards(String name)
	{
		super(name);
	}
	
	public void attack()
	{
		System.out.println("后卫" + name + "进攻");
	}
	public void defense()
	{
		System.out.println("后卫" + name + "防守");
	}
}

/*需要适配的类*/
class ForeignCenter
{
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public void 进攻()
	{
		System.out.println("外籍中锋" + name + "进攻");
	}

	public void 防守()
	{
		System.out.println("外籍中锋" + name + "防守");
	}
	private String name;
}

/*适配器,继承于目标,将需要适配的类作为自己的引用*/
class Translator extends Player
{
	public Translator(String name)
	{
		super(name);
		wjzf.setName(name);
	}

	public void attack()
	{
		wjzf.进攻();
	}
	public void defense()
	{
		wjzf.防守();
	}
	private ForeignCenter wjzf = new ForeignCenter();
}




public class LoveStory
{
	public static void main(String[] args)
	{
		SchoolGirl jiaojiao = new SchoolGirl("李娇娇");

		Proxy daili = new Proxy(jiaojiao);
		
		daili.GiveDolls();
		daili.GiveFlowers();
		daili.GiveChocolate();
	}
}

class SchoolGirl
{
	public SchoolGirl()
	{

	}
	public SchoolGirl(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	private String name;
}
class Pursuit implements IGiveGift
{
	SchoolGirl mm;

	public Pursuit(SchoolGirl mm)
	{
		this.mm = mm;
	}

	public void GiveDolls()
	{
		System.out.println(mm.getName() + "送你洋娃娃" );
	}

	public void GiveFlowers()
	{
		System.out.println(mm.getName() + "送你鲜花");
	}

	public void GiveChocolate()
	{
		System.out.println(mm.getName() + "送你巧克力");
	}
}

class Proxy implements IGiveGift
{
	Pursuit gg;

	public Proxy(SchoolGirl mm)
	{
		gg = new Pursuit(mm);
	}

	public void GiveDolls()
	{
		gg.GiveDolls();
	}

	public void GiveFlowers()
	{
		gg.GiveFlowers();
	}

	public void GiveChocolate()
	{
		gg.GiveChocolate();
	}
}

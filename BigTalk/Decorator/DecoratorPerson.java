public class DecoratorPerson
{
	public static void main(String[] args)
	{
		Person xiaocai = new Person("小菜");
		
		System.out.println("第一种装扮： ");
		
		Sneakers poqiuxie = new Sneakers();
		BigTrouser kuaku = new BigTrouser();
		TShirts datxue = new TShirts();

		poqiuxie.Decorate(xiaocai);
		kuaku.Decorate(poqiuxie);
		datxue.Decorate(kuaku);
		datxue.Show();

		System.out.println("第二种装扮：");
		LeatherShoes pixie = new LeatherShoes();
		Tie  lingdai = new Tie();
		Suit xizhuang = new Suit();

		pixie.Decorate(xiaocai);
		lingdai.Decorate(pixie);
		xizhuang.Decorate(lingdai);
		xizhuang.Show();


	}
}
class Person
{
	public Person()
	{

	}
	public Person(String name)
	{
		this.name = name;
	}

	public void Show()
	{
		System.out.println("装扮的" + name);
	}
	private String name;
}

class Finery extends Person
{
	public void Decorate(Person component)
	{
		this.component = component;
	}

	public void Show()
	{
		if(component != null)
		{
			component.Show();
		}
	}
	protected Person component;
}

class TShirts extends Finery
{
	public void Show()
	{
		System.out.println("大T恤");
		super.Show();
	}
}

class BigTrouser extends Finery
{
	public void Show()
	{
		System.out.println("绔裤");
		super.Show();
	}
}

class Sneakers extends Finery
{
	public void Show()
	{
		System.out.println("破球鞋");
		super.Show();
	}
}

class Suit extends Finery
{
	public void Show()
	{
		System.out.println("西装");
		super.Show();
	}
}

class Tie extends Finery
{
	public void Show()
	{
		System.out.println("领带");
		super.Show();
	}
}

class LeatherShoes extends Finery
{
	public void Show()
	{
		System.out.println("皮鞋");
		super.Show();
	}
}



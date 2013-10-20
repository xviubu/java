/* 工厂模式 一个具体产品对应一个具体的工厂*/
public class XueLeiFeng
{
	public static void main(String[] args)
	{
		IFactory factory = new VolunteerFactory(); 
		LeiFeng stduent =factory.createLeiFeng();
		stduent.BuyRice();
		stduent.Sweep();
		stduent.Wash();
	}
}
class LeiFeng
{
	public void Sweep()
	{
		System.out.println("扫地");
	}

	public void Wash()
	{
		System.out.println("洗衣");
	}

	public void BuyRice()
	{
		System.out.println("买米");
	}
}

class Undergraduate extends LeiFeng
{

}
class Volunteer extends LeiFeng
{

}

interface IFactory
{
	LeiFeng createLeiFeng();
}

class UndergraduateFactory implements IFactory
{
	public LeiFeng createLeiFeng()
	{
		return new Undergraduate();
	}
}

class VolunteerFactory implements IFactory
{
	public LeiFeng createLeiFeng()
	{
		return new Volunteer();
	}
}


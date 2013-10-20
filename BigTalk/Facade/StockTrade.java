/*外观模式，外观类将事物交给具体的子系统处理，而子系统不知道该外观类*/
public class StockTrade
{
	public static void main(String[] args)
	{
		Fund jijin = new Fund();
		jijin.BuyFund();
		jijin.SellFund();
	}
}

class Fund
{
	public Fund()
	{
		gu1 = new Stock1();
		gu2 = new Stock2();
		gu3 = new Stock3();
		nd1 = new NationalDebt1();
		rt1 = new Realty1();
	}

	public void BuyFund()
	{
		gu1.Buy();
		gu2.Buy();
		gu3.Buy();
		nd1.Buy();
		rt1.Buy();
	}

	public void SellFund()
	{
		gu1.Sell();
		gu2.Sell();
		gu3.Sell();
		nd1.Sell();
		rt1.Sell();
	}

	private Stock1 gu1;
	private Stock2 gu2;
	private Stock3 gu3;
	private NationalDebt1 nd1;
	private Realty1  rt1;
}

class Stock1
{
	public void Sell()
	{
		System.out.println("股票1卖出");
	}

	public void Buy()
	{
		System.out.println("股票1买入");
	}
}


class Stock2
{
	public void Sell()
	{
		System.out.println("股票2卖出");
	}

	public void Buy()
	{
		System.out.println("股票2买入");
	}
}

class Stock3
{
	public void Sell()
	{
		System.out.println("股票3卖出");
	}

	public void Buy()
	{
		System.out.println("股票3买入");
	}
}

class NationalDebt1
{
	public void Sell()
	{
		System.out.println("国债1卖出");
	}
	public void Buy()
	{
		System.out.println("国债1买入");
	}
}


class Realty1
{
	public void Sell()
	{
		System.out.println("房地产1卖出");
	}
	public void Buy()
	{
		System.out.println("房地产1买入");
	}
}

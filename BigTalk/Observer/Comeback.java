import java.util.*;

public class Comeback
{
	public static void main(String[] args)
	{
		Boss huhansan = new Boss(); 
		StockObserver tongshi1 = new StockObserver("魏关姹",huhansan);
		NBAObserver tongshi2 = new NBAObserver("易管查",huhansan);

		huhansan.Attach(tongshi1);
		huhansan.Attach(tongshi2);
		huhansan.Detach(tongshi2);
		
		huhansan.setSubjectState("我胡汉三又回来了！");
		huhansan.Notify();

		Secretary tongzhizhe = new Secretary();
		StockObserver tongshi3 = new StockObserver("魏关姹",tongzhizhe);
		NBAObserver tongshi4 = new NBAObserver("易管查",tongzhizhe);
		tongzhizhe.Attach(tongshi3);
		tongzhizhe.Attach(tongshi4);

		tongzhizhe.setSubjectState("老板回来了");
		tongzhizhe.Notify();
	}
}

class Boss implements Subject
{
	public void Attach(Observer observer)
	{
		observers.add(observer);
	}

	public void Detach(Observer observer)
	{
		observers.remove(observer);
	}

	public void Notify()
	{
		for(Observer o : observers)
		{
			o.Update();
		}
	}

	public String getSubjectState()
	{
		return action;
	}
	public void setSubjectState(String action)
	{
		this.action = action;	
	}

	private List<Observer> observers = new LinkedList<Observer>();
	private String action;
}


class Secretary implements Subject
{
	public void Attach(Observer observer)
	{
		observers.add(observer);
	}

	public void Detach(Observer observer)
	{
		observers.remove(observer);
	}

	public void Notify()
	{
		for(Observer o : observers)
		{
			o.Update();
		}
	}

	public String getSubjectState()
	{
		return action;
	}
	public void setSubjectState(String action)
	{
		this.action = action;	
	}

	private List<Observer> observers = new LinkedList<Observer>();
	private String action;
}
class StockObserver implements Observer
{
	public StockObserver(String name,Subject sub)
	{
		this.name = name;
		this.sub = sub;
	}
	public void Update()
	{
		System.out.println(sub.getSubjectState()+ name + "关闭股市行情，继续工作");
	}
	private String name;
	private Subject sub;
}

class NBAObserver implements Observer
{
	public NBAObserver(String name,Subject sub)
	{
		this.name = name;
		this.sub = sub;
	}
	public void Update()
	{
		System.out.println(sub.getSubjectState()+ name + "关闭NBA直播，继续工作");
	}
	private String name;
	private Subject sub;
}


public class WorkState
{
	public static void main(String[] args)
	{
		Work emergencyProjects = new Work();
		emergencyProjects.setHour(9);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(10);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(12);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(13);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(14);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(17);
		emergencyProjects.WriteProgram();

		emergencyProjects.setFinished(true);

		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(19);
		emergencyProjects.WriteProgram();
		emergencyProjects.setHour(22);
		emergencyProjects.WriteProgram();
	}
}


class Work
{
	public Work()
	{
		current = new ForenoonState();
	}
	public double getHour()
	{
		return hour;
	}
	public void setHour(double hour)
	{	
		this.hour = hour;
	}

	public boolean isFinished()
	{
		return finish;
	}
	public void setFinished(boolean finish)
	{
		this.finish = finish;
	}
	public void setState(State s)
	{
		current = s;
	}

	public void WriteProgram()
	{
		current.WriteProgram(this);
	}

	private State current;
	private double hour;
	private boolean finish = false;
}
abstract class State
{
	public abstract void WriteProgram(Work work);
}

class ForenoonState  extends State
{
	public void WriteProgram(Work work)
	{
		if(work.getHour() < 12)
		{
			System.out.println("当前时间" + work.getHour()+"点，上午工作，精神百倍");
		}
		else
		{
			work.setState(new NoonState());
			work.WriteProgram();
		}
	}
}

class NoonState  extends State
{
	public void WriteProgram(Work work)
	{
		if(work.getHour() < 13)
		{
			System.out.println("当前时间" + work.getHour()+"点，饿了，午饭;犯困，午休。");
		}
		else
		{
			work.setState(new AfternoonState());
			work.WriteProgram();
		}
	}
}

class AfternoonState  extends State
{
	public void WriteProgram(Work work)
	{
		if(work.getHour() < 17)
		{
			System.out.println("当前时间" + work.getHour()+"点，下午状态还不错，继续努力");
		}
		else
		{
			work.setState(new EveningState());
			work.WriteProgram();
		}
	}
}

class EveningState extends State
{
	public void WriteProgram(Work work)
	{
		if(work.isFinished())
		{
			work.setState(new RestState());
			work.WriteProgram();
		}
		else
		{
			if(work.getHour() < 21)
			{
				System.out.println("当前时间" + work.getHour()+" 加班哦，疲劳之极");
			}
			else
			{
				work.setState(new SleepingState());
				work.WriteProgram();
			}
		}
	}
}

class SleepingState extends State
{
	public void WriteProgram(Work work)
	{
		System.out.println("当前时间" + work.getHour() + "不行了，睡着了");
	}
}

class RestState extends State
{
	public void WriteProgram(Work work)
	{
		System.out.println("当前时间" + work.getHour() +"下班了，回家了");
	}
}

/*备忘录模式*/
/*客户端通过备忘录管理者来保存和恢复状态，不直接访问具体的细节*/
public class Game
{
	public static void main(String[] args)
	{
		GameRole lixiaoyao  = new GameRole();
		lixiaoyao.GetInitState();
		lixiaoyao.StateDisplay();

		RoleStateCaretaker stateAdmin = new RoleStateCaretaker();
		stateAdmin.setMemento(lixiaoyao.SaveState());

		lixiaoyao.Fight();
		lixiaoyao.StateDisplay();

		lixiaoyao.RecoveryState(stateAdmin.getMemento());
		lixiaoyao.StateDisplay();
	}
}
class GameRole
{
	public int getVitality()
	{
		return vitality;
	}
	public void setVitality(int vitality)
	{
		this.vitality = vitality;
	}

	public int getAttack()
	{
		return attack;
	}
	public void setAttack(int attack)
	{
		this.attack = attack;
	}

	public int getDefense()
	{
		return defense;
	}
	public void setDefense(int defense)
	{
		this.defense = defense;
	}


	public RoleStateMemento SaveState()
	{
		return new RoleStateMemento(vitality,attack,defense);
	}

	public void RecoveryState(RoleStateMemento memento)
	{
		this.vitality = memento.getVitality();
		this.attack = memento.getAttack();
		this.defense = memento.getDefense();
	}
	public void StateDisplay()
	{
		System.out.println("角色当前状态");
		System.out.println("体力: " + getVitality());
		System.out.println("攻击力 : " +getAttack());
		System.out.println("防御力 : " +getDefense());
		System.out.println();
	}

	public void GetInitState()
	{
		this.vitality = 100;
		this.attack = 100;
		this.defense = 100;
	}

	public void Fight()
	{
		this.vitality = 0;
		this.attack = 0;
		this.defense = 0;
	}
	private int vitality;
	private int attack;
	private int defense;
}

/*备忘录,对内保存角色的各种状态,对外提供角色状态*/
class RoleStateMemento 
{

	public RoleStateMemento(int vitality,int attack,int defense)
	{
		this.vitality = vitality;
		this.attack = attack;
		this.defense = defense;
	}
	public int getVitality()
	{
		return vitality;
	}
	public void setVitality(int vitality)
	{
		this.vitality = vitality;
	}

	public int getAttack()
	{
		return attack;
	}
	public void setAttack(int attack)
	{
		this.attack = attack;
	}

	public int getDefense()
	{
		return defense;
	}
	public void setDefense(int defense)
	{
		this.defense = defense;
	}
	private int vitality;
	private int attack;
	private int defense;

}
/*状态管理者，用来设置和获取状态，封装了具体的细节*/
class RoleStateCaretaker
{
	public RoleStateMemento getMemento()
	{
		return memento;
	}
	public void setMemento(RoleStateMemento memento)
	{
		this.memento = memento;
	}
	private RoleStateMemento memento;
}

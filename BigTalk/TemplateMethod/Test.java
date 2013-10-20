/** 模板方法 进可能的将子类中相同的代码放在父类的模板中，实现代码的复用 **/
public class Test
{
	public static void main(String[] args)
	{
		System.out.println("学生1的测试");
		TestPaper stduent1 = new TestPaper1(); 
		stduent1.TestQuestion1();
		stduent1.TestQuestion2();
		stduent1.TestQuestion3();

		System.out.println("学生2的测试");
		TestPaper stduent2 = new TestPaper2(); 
		stduent2.TestQuestion1();
		stduent2.TestQuestion2();
		stduent2.TestQuestion3();
	}
}

class TestPaper
{
	public void TestQuestion1()
	{
		System.out.println("杨过得到后，后来给了郭靖，炼成倚天剑，屠龙刀的玄铁可能是[] a.球磨铸铁 b.马口铁 c.高速合金钢 d.碳素纤维");
		System.out.println("答案 " + Answer1());
	}
	public void TestQuestion2()
	{
		System.out.println("杨过，程英，陆无双产出了情花，造成了[] a.是这种植物不再害人 b.使一种珍惜植物灭绝"+
							"c.破坏了那个生物圈的生态平衡 d.造成该地区沙漠化");
		System.out.println("答案：" + Answer2());
	}
	public void TestQuestion3()
	{
		System.out.println("蓝凤凰致使华山师徒，桃谷六仙呕吐不止,如果你是大夫，会给他们开什么药 a.阿司匹林 b.牛黄解毒片 c.氟哌酸 d.大量生牛奶");
		System.out.println("答案 " + Answer3());
	}

	protected String Answer1()
	{
		return "";
	}
	protected String Answer2()
	{
		return "";
	}
	protected String Answer3()
	{
		return "";
	}
}

class TestPaper1 extends TestPaper
{
	public String Answer1()
	{
		return "b";
	}

	public String Answer2()
	{
		return "c";
	}

	public String Answer3()
	{
		return "a";
	}
}


class TestPaper2 extends TestPaper
{
	public String Answer1()
	{
		return "c";
	}

	public String Answer2()
	{
		return "a";
	}

	public String Answer3()
	{
		return "a";
	}
}

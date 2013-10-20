package foo;

public class Person
{
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Dog getDog()
	{
		return dog;
	}
	public void setDog(Dog dog)
	{
		this.dog = dog;
	}
	private Dog dog;
	private String name;
}

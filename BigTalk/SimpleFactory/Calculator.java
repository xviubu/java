import java.io.*;
import java.util.Scanner;

public class Calculator
{
	public static void main(String[] args) throws IOException
	{
		Operation oper = null;

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter number A: ");
		double numberA = scanner.nextDouble();
		System.out.println("Please enter number B: ");
		double numberB = scanner.nextDouble();
		System.out.println("Please enter Operation:");
		String operation = scanner.next();

		oper = OperationFactory.createOperation(operation);
		oper.setNumberA(numberA);
		oper.setNumberB(numberB);
		double result = oper.GetResult();
		System.out.println("result = " + result);
	}
}

abstract class Operation
{
	public double getNumberA()
	{
		return numberA;
	}
	public void setNumberA(double numberA)
	{
		this.numberA = numberA;
	}
	public double getNumberB()
	{
		return numberB;
	}
	public void setNumberB(double numberB)
	{
		this.numberB = numberB;
	}

	public double GetResult()
	{
		double result = 0;
		return result;
	}
	private double numberA ;
	private double numberB;
}

class OperationAdd extends Operation
{
	public double GetResult()
	{
		double result = super.GetResult();
		result = super.getNumberA() +super.getNumberB();
		return result;
	}
}

class OperationSub extends Operation
{
	public double GetResult()
	{
		double result = super.GetResult();
		result = super.getNumberA() - super.getNumberB();
		return result;
	}
}

class OperationMul extends Operation
{
	public double GetResult()
	{
		double result = super.GetResult();
		result = super.getNumberA() * super.getNumberB();
		return result;
	}
}

class OperationDiv extends Operation
{
	public double GetResult()
	{
		double result = super.GetResult();
		if(super.getNumberB() != 0)
		{
			result = super.getNumberA() / super.getNumberB();
		}
		return result;
	}
}

class OperationFactory
{
	public static Operation createOperation(String operation)
	{
		Operation oper = null;
		switch(operation)
		{
			case "+":
				oper = new OperationAdd();
				break;
			case "-":
				oper = new OperationSub();
				break;
			case "*":
				oper = new OperationMul();
				break;
			case "/":
				oper = new OperationDiv();
				break;
		}
		return oper;
	}
}


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


public class Supermarket
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				JFrame frame = new CashFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

class CashFrame extends JFrame
{
	public CashFrame()
	{
		setTitle("Supermarket Cash");
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new GridLayout(3,3));
		JLabel priceLabel = new JLabel("单价：");
		priceField = new JTextField(10);
		okButton = new JButton("确定");
		okButton.addActionListener(new calculateListener());

		northPanel.add(priceLabel);
		northPanel.add(priceField);
		northPanel.add(okButton);

		JLabel amountLabel = new JLabel("数量：");
		amountField = new JTextField(10);
		resetButton = new JButton("重置");
		resetButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				priceField.setText("");
				amountField.setText("");
			}
		});

		northPanel.add(amountLabel);
		northPanel.add(amountField);
		northPanel.add(resetButton);

		JLabel cashLabel = new JLabel("计算方式：");
		cashButton = new JComboBox();
		cashButton.setEditable(false);
		cashButton.addItem("打8折");
		cashButton.addItem("正常收费");
		cashButton.addItem("满300返100");

		northPanel.add(cashLabel);
		northPanel.add(cashButton);

		JPanel centerPanel = new JPanel();
		processArea = new JTextArea(15,20);
		centerPanel.add(processArea);
		
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,2));
		southPanel.add(new JLabel("总计："));
		resultField = new JTextField();
		southPanel.add(resultField);
		
		add(northPanel,BorderLayout.NORTH);
		add(centerPanel,BorderLayout.CENTER);
		add(southPanel,BorderLayout.SOUTH);


	}

	class calculateListener implements ActionListener
	{
		StringBuffer process = new StringBuffer();
		public void actionPerformed(ActionEvent event)
		{
			CashContext cc = null;
			switch(cashButton.getSelectedItem().toString())
			{
				case "正常收费":
					cc = new CashContext(new CashNormal());
					break;
				case "打8折":
					cc = new CashContext(new CashRebate(0.8));
					break;
				case "满300返100":
					cc = new CashContext(new CashReturn(300,100));
					break;
			}
			double totalPrice = 0;
			totalPrice = cc.GetResult(Double.valueOf(priceField.getText()) * Double.valueOf(amountField.getText()));

			process.append("单价：" + priceField.getText() + " 数量: " + amountField.getText());
			process.append(" " + cashButton.getSelectedItem().toString() + " ");

			double total = 0;
			total += totalPrice;
			resultField.setText(total + "");
			process.append("合计：" + total + "\n");
			processArea.setText(process.toString());
		}
	}

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_HEIGHT = 400;

	private JButton okButton;
	private JButton resetButton;
	private JComboBox cashButton;
	private final JTextField priceField;
	private final JTextField amountField;
	private JTextArea processArea;
	private JTextField resultField;
}



class CashNormal implements CashSuper
{
	public CashNormal()
	{

	}
	public double acceptCash(double money)
	{
		return money;
	}
}

class CashRebate implements CashSuper
{
	public CashRebate(double moneyRebate)
	{
		this.moneyRebate = moneyRebate;
	}
	public double acceptCash(double money)
	{
		return money * moneyRebate;
	}
	private double moneyRebate;
}

class CashReturn implements CashSuper
{
	public CashReturn(double moneyCondition,double moneyReturn)
	{
		this.moneyCondition = moneyCondition;	
		this.moneyReturn = moneyReturn;
	}

	public double acceptCash(double money)
	{
		double result = money;
		if(money >= moneyCondition)
		{
			result = money - Math.floor(money / moneyCondition) * moneyReturn;
		}
		return result;
	}

	private double moneyCondition;
	private double moneyReturn;
}

class CashContext
{
	public CashContext(CashSuper cs) 
	{
		this.cs = cs;
	}
	public double GetResult(double money)
	{
		return cs.acceptCash(money);
	}
	private CashSuper cs;

}

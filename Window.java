import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import java.util.FormatFlagsConversionMismatchException;
import java.util.FormatterClosedException;
import java.util.logging.Formatter;
import java.util.*;
import java.lang.Math;
import javax.lang.model.util.ElementScanner14;
import javax.swing.*;

public class Window implements ActionListener
{
    private Account a;
    private JLabel food;
    private JLabel entertainment;
    private JLabel utilities;
    private JLabel investment;
    private JLabel balance;
    private JLabel foodSpent;
    private JLabel entertainmentSpent;
    private JLabel utilitiesSpent;
    private JLabel investmentSpent;
    private JLabel getDropdownOption;
    private JLabel budgetLabel;
    private JLabel budgetQ;
    private JLabel dropdownQ;
    private JLabel backgroundImg;
    private JFrame frame;
    private JFrame popupFrame;
    private JFrame budgetFrame;
    private JTextField balanceField;
    private JTextField foodField;
    private JTextField entertainmentField;
    private JTextField investmentField;
    private JTextField utilField;
    private JTextField budgetField;
    private JButton balanceButton;
    private JButton calculateButton;
    private JButton summarizeButton;
    private JButton addSpending;
    private JButton logoutButton;
    private JButton addBudgetButt;
    private JButton closeBudgetButt;
    private JButton reset;
    private JComboBox<String> dropdown;
    private JSeparator vLine;
    private JSeparator hLine1;
    private JSeparator hLine2;
    private JSeparator hLine3;
    private JButton select;
    private JButton back;
    private double userBudget;
    public Window(Account accInfo)
    {
        ImageIcon bg = new ImageIcon("backgroundS.png");
        backgroundImg = new JLabel(bg);
        a = accInfo;
        food = new JLabel("Food");
        entertainment = new JLabel("Entertainment");
        utilities = new JLabel("Utilities");
        investment = new JLabel("Investment");
        balance = new JLabel("Monthly Spending Limit:");
        foodSpent = new JLabel(String.valueOf(a.getFoodSpend()));
        entertainmentSpent = new JLabel(String.valueOf(a.getEntertainmentSpend()));
        investmentSpent = new JLabel(String.valueOf(a.getInvestmentSpend()));
        utilitiesSpent = new JLabel(String.valueOf(a.getUtilSpend()));
        budgetLabel = new JLabel(String.valueOf(a.getOverallBalance()));
        getDropdownOption = new JLabel("");
        dropdownQ = new JLabel("Where would you like amount added to?");
        budgetQ = new JLabel("How much would you like to add?");
        frame = new JFrame();
        popupFrame = new JFrame();
        budgetFrame = new JFrame();
        balanceField = new JTextField();
        foodField = new JTextField();
        entertainmentField = new JTextField();
        investmentField = new JTextField();
        utilField = new JTextField();
        budgetField = new JTextField();
        balanceButton = new JButton("Add Initial Budget");
        calculateButton = new JButton("Calculate Spendings");
        summarizeButton = new JButton("Summarize Spendings");
        addSpending = new JButton("Add Spending");
        logoutButton = new JButton("Logout");
        addBudgetButt = new JButton("Add Budget");
        closeBudgetButt = new JButton("Back");
        reset = new JButton("Reset");
        String[] foodChoices = {"Food", "Entertainment", "Utilities", "Investment"};
        dropdown = new JComboBox<>(foodChoices);
        vLine = new JSeparator(JSeparator.VERTICAL);
        hLine1 = new JSeparator(JSeparator.HORIZONTAL);
        hLine2 = new JSeparator(JSeparator.HORIZONTAL);
        hLine3 = new JSeparator(JSeparator.HORIZONTAL);
        select = new JButton("Add");
        back = new JButton("Back");


        balance.setBounds(200, -25, 300, 100);
        balance.setVisible(true);
        balance.setFont(new Font("Arial", Font.BOLD, 20));
        
        balanceField.setBounds(65, 150, 250, 50);

        balanceButton.setBounds(775, 12, 200, 25);
        balanceButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                budgetFrame.setVisible(true);
            }
        });
        addSpending.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                popupFrame.setVisible(true);
            }
        }
        );
        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                if(userBudget > 0)
                {
                    double[] allSpendings = new double[4];
                    allSpendings[0] = a.getFoodSpend();
                    allSpendings[1] = a.getEntertainmentSpend();
                    allSpendings[2] = a.getUtilSpend();
                    allSpendings[3] = a.getInvestmentSpend();
                    int foodInd = 0;
                    int entertainmentInd = 1;
                    int utilInd = 2;
                    int investmentInd = 3;
                    for(int i = 0; i < allSpendings.length - 1; i++)
                    {
                        int minIndex = i; //index of the minimum value
                        for(int j = i + 1; j < allSpendings.length; j++)
                        {
                          if(allSpendings[j] < allSpendings[minIndex])
                          {
                            minIndex = j; //find the minimum
                          }
                        }
                        //swap the minimum with the value at index i
                        if(i != minIndex)
                        {
                          double temp = allSpendings[minIndex];
                          allSpendings[minIndex] = allSpendings[i];
                          allSpendings[i] = temp;
                          //count the swaps
                        }
                    }
                    if(allSpendings[0] == allSpendings[1] && allSpendings[0] == allSpendings[2] && allSpendings[0] == allSpendings[3])
                    {
                        System.out.println("You spent the same amount this month on all categories.");
                        if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                        {
                            System.out.println("You went over your target monthly budget.");
                        }
                        else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                        {
                            System.out.println("You did not go over your target monthly budget.");
                        }
                        else
                        {
                            System.out.println("You spent exactly the same as your target monthly budget.");
                        }
                    }
                    else if(allSpendings[0] == a.getFoodSpend())
                    {
                        if(allSpendings[1] == a.getEntertainmentSpend())
                        {
                            if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on entertainment, the third least on utilities, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on entertainment, the third least on future investments, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getUtilSpend())
                        {
                            if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on utilities, the third least on entertainment, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on utilities, the third least on future investments, and the most on future entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getInvestmentSpend())
                        {
                            if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on future investments, the third least on entertainment, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on food, the second least on future investments, the third least on utilities, and the most on entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                    }
                    else if(allSpendings[0] == a.getEntertainmentSpend())
                    {
                        if(allSpendings[1] == a.getFoodSpend())
                        {
                            if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on food, the third least on utilities, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on food, the third least on future investments, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getUtilSpend())
                        {
                            if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on utilities, the third least on food, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on utilities, the third least on future investments, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getInvestmentSpend())
                        {
                            if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on future investments, the third least on utilities, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on entertainment, the second least on future investments, the third least on food, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                    }
                    else if(allSpendings[0] == a.getInvestmentSpend())
                    {
                        if(allSpendings[1] == a.getFoodSpend())
                        {
                            if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on food, the third least on utilities, and the most on entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on food, the third least on entertainment, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getUtilSpend())
                        {
                            if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on utilities, the third least on food, and the most on entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on utilities, the third least on entertainment, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getEntertainmentSpend())
                        {
                            if(allSpendings[2] == a.getUtilSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on entertainment, the third least on utilities, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getUtilSpend())
                                {
                                    System.out.println("You spent the least amount this month on future investments, the second least on entertainment, the third least on food, and the most on utilities.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                    }
                    else if(allSpendings[0] == a.getUtilSpend())
                    {
                        if(allSpendings[1] == a.getFoodSpend())
                        {
                            if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on food, the third least on future investments, and the most on entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on food, the third least on entertainment, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getInvestmentSpend())
                        {
                            if(allSpendings[2] == a.getEntertainmentSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on future investments, the third least on entertainment, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getEntertainmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on future investments, the third least on food, and the most on entertainment.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                        else if(allSpendings[1] == a.getEntertainmentSpend())
                        {
                            if(allSpendings[2] == a.getFoodSpend())
                            {
                                if(allSpendings[3] == a.getInvestmentSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on entertainment, the third least on food, and the most on future investments.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                            else if(allSpendings[2] == a.getInvestmentSpend())
                            {
                                if(allSpendings[3] == a.getFoodSpend())
                                {
                                    System.out.println("You spent the least amount this month on utilities, the second least on entertainment, the third least future investments, and the most on food.");
                                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > userBudget)
                                    {
                                        System.out.println("You went over your target monthly budget.");
                                    }
                                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < userBudget)
                                    {
                                        System.out.println("You did not go over your target monthly budget.");
                                    }
                                    else
                                    {
                                        System.out.println("You spent exactly the same as your target monthly budget.");
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(frame, "Please have an initial monthly budget before calculating");
                    System.out.println("Please have an initial monthly budget before calculating.");
                }
                
            }
            
        }
        
        );

        summarizeButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                if(a.getInvestmentSpend() > 0 && a.getOverallBalance() > 0)
                {
                    double[] allSpendings = new double[4];
                    allSpendings[0] = a.getFoodSpend();
                    allSpendings[1] = a.getEntertainmentSpend();
                    allSpendings[2] = a.getUtilSpend();
                    allSpendings[3] = a.getInvestmentSpend();
                    double foodPercent = roundPercent(a.getFoodSpend()/a.getOverallBalance());
                    double entertainmentPercent = roundPercent(a.getEntertainmentSpend()/a.getOverallBalance());
                    double utilPercent = roundPercent(a.getUtilSpend()/a.getOverallBalance());
                    double investmentPercent = roundPercent(a.getInvestmentSpend()/a.getOverallBalance());
                    double[] percentList = new double[4];
                    percentList[0] = foodPercent;
                    percentList[1] = entertainmentPercent;
                    percentList[2] = utilPercent;
                    percentList[3] = investmentPercent;
                    for(int i = 1; i < allSpendings.length; i++)
                    {
                        //store the value at index i
                        double key = allSpendings[i];
                        int j = i - 1;
                        //check if the value to the left is greater
                        while(j >= 0 && allSpendings[j] > key)
                        {
                        //if it's greater, move it to the right one space
                        allSpendings[j + 1] = allSpendings[j];
                        //move the key to the left
                        j--;
                    }
                    //and insert the value at index i in its proper position
                    allSpendings[j + 1] = key;
                    //also count the number of insertions
                    }
                    for(int i = 1; i < percentList.length; i++)
                    {
                        //store the value at index i
                        double key = percentList[i];
                        int j = i - 1;
                        //check if the value to the left is greater
                        while(j >= 0 && percentList[j] > key)
                        {
                        //if it's greater, move it to the right one space
                        percentList[j + 1] = percentList[j];
                        //move the key to the left
                        j--;
                    }
                    //and insert the value at index i in its proper position
                    percentList[j + 1] = key;
                    //also count the number of insertions
                    }
                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() == a.getOverallBalance())
                    {
                        System.out.println("You spent exactly the same amount as your target monthly budget. Don't do that. Think about your future next time.");
                    }
                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() > a.getOverallBalance())
                    {
                        System.out.println("You managed your monthly budget poorly this month. You spent over your target budget. Think about your future next time");
                    }
                    else if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < a.getOverallBalance())
                    {
                        System.out.println("You did a good job managing your monthly budget effectively this time. You got some pocket change after allocating your resources. Do whatever with it.");
                    }
                    else
                    {
    
                    }
                    System.out.print("Spendings: ");
                    for(double d : allSpendings)
                    {
                        System.out.print(d + " ");
                    }
                    System.out.println();
                    System.out.print("Percentages: ");
                    for(double s : percentList)
                    {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                    System.out.println();
                    if(a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() < a.getOverallBalance() || a.getFoodSpend() + a.getEntertainmentSpend() + a.getUtilSpend() + a.getInvestmentSpend() == a.getOverallBalance())
                    {
                        System.out.println("You spent " + a.getFoodSpend() + " this month on food, " + a.getEntertainmentSpend() + " this month on entertainment, " + a.getUtilSpend() + " this month on utilities, and " + a.getInvestmentSpend() + " this month on future investments.");
                        System.out.println();
                        if(foodPercent <= 20.0)
                        {
                            System.out.println("You spent less than or equal to 20 percent of your monthly budget on food. That is recommended.");
                        }
                        else{
                            System.out.println("You spent more than 20 percent of your monthly budget on food. Don't do that. Don't eat A5 Wagyu Steak every day.");
                        }
                        if(entertainmentPercent <= 10.0)
                        {
                            System.out.println("You spent less than or equal to 10 percent of your monthly budget on entertainment. That is recommended.");
                        }
                        else
                        {
                            System.out.println("You spent more than 10 percent of your monthly budget on entertainment. Try not to do that. Having fun isn't everything.");
                        }
                        if(utilPercent <= 50.0)
                        {
                            System.out.println("You spent less than or equal to 50 percent of your monthly budget on utilities. That is recommended.");
                        }
                        else
                        {
                            System.out.println("You spent more than 50 percent of your monthly budget on utilities. Surely you can save some electricity or water or gas.");
                        }
                        System.out.println("It doesn't matter how much percent of your monthly budget is put on investments. It is good to have even the slightest percent put down on it.");
                    }
                    else
                    {
                        System.out.println("Shameful spending this month. Try better.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(frame, "Please add some spendings or budget first");
                }
               
            }
        }
        );
        select.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                if(budgetField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(frame, "Please enter in at least a value of 0 for your spending.");
                    System.out.println("Please enter in at least a value of 0 for your spending.");
                }
                else if(!acceptableBudget(budgetField.getText()))
                {
                    JOptionPane.showMessageDialog(frame, "Please enter only numbers");
                    System.out.println("Please enter only numbers.");
                }
                else if(Double.valueOf(budgetField.getText()) < 0)
                {
                    JOptionPane.showMessageDialog(frame, "Please enter in a value that isn't negative");
                    System.out.println("Please enter in a value that isn't negative.");
                }
                else if(acceptableBudget(budgetField.getText()))
                {
                    if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("Food"))
                    {
                        if(a.getFoodSpend() > 0)
                        {
                            a.setFoodSpend(a.getFoodSpend() + Double.parseDouble(budgetField.getText()));
                            foodSpent.setText(Double.toString(a.getFoodSpend()));
                            foodSpent.setVisible(true);
                        }
                        else
                        {
                            a.setFoodSpend(Double.parseDouble(budgetField.getText()));
                            foodSpent.setText(budgetField.getText());
                            foodSpent.setVisible(true);
                        }
                    }
                    else if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("Entertainment"))
                    {
                        if(a.getEntertainmentSpend() > 0)
                        {
                            a.setEntertainmentSpend(a.getEntertainmentSpend() + Double.parseDouble(budgetField.getText()));
                            entertainmentSpent.setText(Double.toString(a.getEntertainmentSpend()));
                            entertainmentSpent.setVisible(true);
                        }
                        else
                        {
                            a.setEntertainmentSpend(Double.parseDouble(budgetField.getText()));

                            entertainmentSpent.setText(budgetField.getText());
                            entertainmentSpent.setVisible(true);
                        }
                    }
                    else if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("Investment"))
                    {
                        if(a.getInvestmentSpend() > 0)
                        {
                            a.setInvestmentSpend(a.getInvestmentSpend() + Double.parseDouble(budgetField.getText()));
                            investmentSpent.setText(Double.toString(a.getInvestmentSpend()));
                            investmentSpent.setVisible(true);
                        }
                        else
                        {
                            a.setInvestmentSpend(Double.parseDouble(budgetField.getText()));
;
                            investmentSpent.setText(budgetField.getText());
                            investmentSpent.setVisible(true);
                        }
                    }
                    else if(dropdown.getItemAt(dropdown.getSelectedIndex()).equals("Utilities"))
                    {
                        if(a.getUtilSpend() > 0)
                        {
                            a.setUtilSpend(a.getUtilSpend() + Double.parseDouble(budgetField.getText()));
                            utilitiesSpent.setText(budgetField.getText());
                            utilitiesSpent.setVisible(true);
                        }
                        else
                        {
                            a.setUtilSpend(Double.parseDouble(budgetField.getText()));
                            utilitiesSpent.setText(budgetField.getText());
                            utilitiesSpent.setVisible(true);
                        }
                    }
                }
                else
                {
                    System.out.println("Please only enter numbers.");
                }
            }
        }
        );
        back.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                popupFrame.dispose();
            }
        }
        );
        logoutButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                frame.dispose();
                new GUI();
            }
        }
        );
        addBudgetButt.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {

                if(balanceField.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(frame, "Please enter in at least a value greater than 0 for your budget");
                    System.out.println("Please enter in at least a value greater than 0 for your budget.");
                }
                else if(!acceptableBudget(balanceField.getText()))
                {
                    JOptionPane.showMessageDialog(frame, "Please only enter numbers");
                    System.out.println("Please only enter numbers");
                }
                else if(Double.valueOf(balanceField.getText()) <= 0)
                {
                    JOptionPane.showMessageDialog(frame, "Please enter in a value that is not negative or 0");
                    System.out.println("Please enter in a value that is not negative or 0.");
                }
                else if(acceptableBudget(balanceField.getText()))
                {
                    userBudget = Double.parseDouble(balanceField.getText());
                    a.setOverallBalance(Double.parseDouble(balanceField.getText()));
                    balanceButton.setText("Edit Budget");
                    addBudgetButt.setText("Edit Budget");
                    budgetLabel.setText(String.valueOf(a.getOverallBalance()));
                }
                else
                {
                    System.out.println("Please only enter numbers.");
                }
            }
        });
        closeBudgetButt.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                budgetFrame.dispose();
            }
        });
        reset.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent click)
            {
                a.setOverallBalance(0);
                a.setFoodSpend(0);
                a.setInvestmentSpend(0);
                a.setUtilSpend(0);
                a.setEntertainmentSpend(0);
                foodSpent.setText(String.valueOf(a.getFoodSpend()));
                budgetLabel.setText(String.valueOf(a.getOverallBalance()));
                entertainmentSpent.setText(String.valueOf(a.getEntertainmentSpend()));
                utilitiesSpent.setText(String.valueOf(a.getUtilSpend()));
                investmentSpent.setText(String.valueOf(a.getInvestmentSpend()));
            }
        });


        food.setVisible(true);
        entertainment.setVisible(true);
        utilities.setVisible(true);
        investment.setVisible(true);
        foodSpent.setVisible(true);
        entertainmentSpent.setVisible(true);
        investmentSpent.setVisible(true);
        utilitiesSpent.setVisible(true);
        addSpending.setVisible(true);
        calculateButton.setVisible(true);
        summarizeButton.setVisible(true);
        vLine.setVisible(true);
        hLine1.setVisible(true);
        hLine2.setVisible(true);
        hLine3.setVisible(true);
        dropdown.setVisible(true);
        select.setVisible(true);
        back.setVisible(true);
        getDropdownOption.setVisible(true);
        dropdownQ.setVisible(true);
        budgetField.setVisible(true);
        logoutButton.setVisible(true);
        budgetLabel.setVisible(true);
        budgetQ.setVisible(true);
        addBudgetButt.setVisible(true);
        closeBudgetButt.setVisible(true);
        reset.setVisible(true);

        food.setBounds(50, 150, 100, 100);
        food.setFont(new Font("Arial", Font.BOLD, 20));
        entertainment.setBounds(50, 250, 300, 100);
        entertainment.setFont(new Font("Arial", Font.BOLD, 20));
        utilities.setBounds(50, 350, 250, 100);
        utilities.setFont(new Font("Arial", Font.BOLD, 20));
        investment.setBounds(50, 450, 250, 100);
        investment.setFont(new Font("Arial", Font.BOLD, 20));
        addSpending.setBounds(1000, 150, 200, 100);
        calculateButton.setBounds(1000, 275, 200, 100);
        summarizeButton.setBounds(1000, 400, 200, 100);
        vLine.setBounds(250, 150, 1000, 400);
        vLine.setBackground(Color.black);
        hLine1.setBounds(50, 250, 700, 150);
        hLine1.setBackground(Color.black);
        hLine2.setBounds(50, 350, 700, 150);
        hLine2.setBackground(Color.black);
        hLine3.setBounds(50, 450, 700, 150);
        hLine3.setBackground(Color.black);
        dropdown.setBounds(90, 175, 200, 50);
        select.setBounds(135, 275, 100, 50);
        back.setBounds(135, 350, 100, 50);
        dropdownQ.setBounds(80, 100, 300, 50);
        getDropdownOption.setBounds(95,425,300, 50 );
        budgetField.setBounds(90, 50, 200, 50);
        budgetLabel.setBounds(600, 5, 200, 50);
        budgetLabel.setFont(new Font("Arial", Font.BOLD, 20));
        budgetQ.setBounds(95, 100, 300, 50);
        budgetQ.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setBounds(1100, 10, 100, 50);
        foodSpent.setBounds(450, 150, 300, 100);
        foodSpent.setFont(new Font("Arial", Font.BOLD, 20));
        entertainmentSpent.setBounds(450, 250, 300, 100);
        entertainmentSpent.setFont(new Font("Arial", Font.BOLD, 20));
        utilitiesSpent.setBounds(450, 350, 300, 100);
        utilitiesSpent.setFont(new Font("Arial", Font.BOLD, 20));
        investmentSpent.setBounds(450, 450, 300, 100);
        investmentSpent.setFont(new Font("Arial", Font.BOLD, 20));
        addBudgetButt.setBounds(140, 250, 100, 50);
        closeBudgetButt.setBounds(140, 325, 100, 50);
        reset.setBounds(1000, 525, 200, 100);

        


        frame.setLocationRelativeTo(null);
        frame.setBounds(0, 0, 1280, 720); 

        frame.setTitle("SpendyPendy Services");
        frame.setLayout(null);
        //frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

        frame.add(balance);
        frame.add(budgetLabel);
        //frame.add(balanceField);
        frame.add(balanceButton);
        frame.add(food);
        frame.add(entertainment);
        frame.add(utilities);
        frame.add(investment);
        frame.add(addSpending);
        frame.add(calculateButton);
        frame.add(summarizeButton);
        frame.add(vLine);
        frame.add(hLine1);
        frame.add(hLine2);
        frame.add(hLine3);
        frame.add(logoutButton);
        frame.add(foodSpent);
        frame.add(entertainmentSpent);
        frame.add(utilitiesSpent);
        frame.add(investmentSpent);
        frame.add(backgroundImg);
        frame.add(reset);

        budgetFrame.setLocationRelativeTo(null);
        budgetFrame.setBounds(0, 0, 400, 600);

        budgetFrame.setLayout(null);
        budgetFrame.setVisible(false);
        budgetFrame.setResizable(false);
        budgetFrame.setTitle("Add/Edit Budget");
        budgetFrame.add(budgetQ);
        budgetFrame.add(balanceField);
        budgetFrame.add(addBudgetButt);
        budgetFrame.add(closeBudgetButt);
        
        popupFrame.setLocationRelativeTo(null);
        popupFrame.setBounds(0, 0, 400, 600);

        popupFrame.setLayout(null);
        popupFrame.setVisible(false);
        popupFrame.setResizable(false);
        popupFrame.setTitle("Add Spending");
        popupFrame.add(budgetField);
        popupFrame.add(dropdownQ);
        popupFrame.add(dropdown);
        popupFrame.add(select);
        popupFrame.add(back);
        popupFrame.add(getDropdownOption);
    }
    private static boolean checkSpecialChars(String password)
    {
        String specialChars="!@#$%^&*";
        for (int i=0;i<specialChars.length(); i++)
        {
            if (password.indexOf(specialChars.substring(i,i+1))!=-1)
            {
                return true;
            }
        }
        return false;
    }
    private static boolean checkUppercase(String password)
    {
        String upperChars="QWERTYUIOPASDFGHJKLZXCVBNM";
        for (int i=0;i<upperChars.length(); i++)
        {
            if (password.indexOf(upperChars.substring(i,i+1))!=-1)
            {
                return true;
            }
        }
        return false;
    }
    private static boolean checkLowercase(String password) 
    {
        String lowerChars="qwertyuiopasdfghjklzxcvbnm";
        for (int i=0;i<lowerChars.length(); i++)
        {
            if (password.indexOf(lowerChars.substring(i,i+1))!=-1)
            {
                return true;
            }
        }
        return false;
    }
    public static boolean acceptableBudget(String input) 
    {
        if (checkSpecialChars(input)==true)
        {
            return false;
        }
        if (checkUppercase(input)==true)
        {
            return false;
        }
        if (checkLowercase(input)==true)
        {
            return false;
        }
        return true;
    }
    public static double roundPercent(double x)
    {
        return (Math.round(10000.0 * x)/10000.0) * 100.0;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
    }

}


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.*;
import java.util.FormatFlagsConversionMismatchException;
import java.util.FormatterClosedException;
import java.util.logging.Formatter;
import java.util.*;

import javax.swing.*;

public class GUI implements ActionListener
{
  private JLabel userLabel;
  private JLabel passwordLabel;
  private JLabel backgroundImg;
  private JFrame frame;
  private JTextField userField;
  private JPasswordField passwordField;
  private JButton register;
  private JButton login;
  private JButton delete;
  private ArrayList<Account> LogInInfo = new ArrayList<>();
  public GUI()
  {
    LogInInfo = Main.getLogInInfo();
    frame = new JFrame();
    ImageIcon bg = new ImageIcon("backgroundS.png");
    backgroundImg = new JLabel(bg);
    userLabel = new JLabel();
    passwordLabel = new JLabel();
    userField = new JTextField();
    passwordField = new JPasswordField();
    register = new JButton("Register");
    login = new JButton("Login");
    delete = new JButton("Delete Account");


    userLabel = new JLabel("Username:");
    passwordLabel = new JLabel("Password:");

    userLabel.setBounds(150, 250, 200, 50);
    userLabel.setFont(new Font("Arial", Font.BOLD, 20));
    passwordLabel.setBounds(150, 312, 100, 50);
    passwordLabel.setFont(new Font("Arial", Font.BOLD, 20));
    userField.setBounds(500, 250, 300, 50);
    passwordField.setBounds(500, 312, 300, 50);
    register.setBounds(527, 375, 250, 50);
    login.setBounds(527, 440, 250, 50);
    delete.setBounds(527, 505, 250, 50);

    backgroundImg.setVisible(true);
    backgroundImg.setBounds(0,0, 1280, 720);
    
    //panel = new JPanel();
    //panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
    //panel.setLayout(new GridLayout(0, 1));
    //panel.add(button);
    //panel.add(label);
    //panel.add(labelTwo);

    //frame.add(panel, BorderLayout.CENTER);
    frame.setLocationRelativeTo(null);
    frame.setBounds(0, 0, 1280, 720); 
 
    frame.setTitle("SpendyPendy Services");
    frame.setLayout(null);
    //frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);






    frame.add(userLabel);
    frame.add(passwordLabel);
    frame.add(userField);
    frame.add(passwordField);
    frame.add(register);
    frame.add(login);
    frame.add(delete);
    frame.add(backgroundImg);

    register.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent click)
      {
        boolean dupe = false;
        for(Account a : Main.LogInInfo)
        {
          if(a.getUsername().equals(userField.getText()))
          {
            dupe = true;
          }
        }
        if(dupe)
        {
          JOptionPane.showMessageDialog(frame, "Username already taken. Choose a different username.");
          System.out.println("Username already taken. Choose a different username.");
        }
        if(userField.getText().equals(""))
        {
          JOptionPane.showMessageDialog(frame, "Must have a valid username before registering.");
          System.out.println("Must have a valid username before registering.");
        }
        if(String.valueOf(passwordField.getPassword()).equals(""))
        {
          JOptionPane.showMessageDialog(frame, "Please input a password");
          System.out.print("Please input a password.");
        }
        if(passwordIsStrongEnough(String.valueOf(passwordField.getPassword())).equals("Strong"))
        {
          //LogInInfo.add(new Account(userField.getText(), String.valueOf(passwordField.getPassword())));
          Main.addAccount(new Account(userField.getText(), String.valueOf(passwordField.getPassword())));
          JOptionPane.showMessageDialog(frame, "Account registered");
          System.out.println("Account registered");
        }
        else
        {
          System.out.println(passwordIsStrongEnough(String.valueOf(passwordField.getPassword())));
        }
      }
    }
    );
    login.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent click)
      {
        Account acc = new Account(userField.getText(), String.valueOf(passwordField.getPassword()));
        boolean foundUser = false;
        boolean foundPass = false;
        for(Account a : Main.LogInInfo)
        {
          if(acc.equals(a))
          {
            foundUser = true;
            foundPass = true;
            JOptionPane.showMessageDialog(frame, "Login sucessful");
            System.out.println("Login successful.");
            new Window(a);
            frame.dispose();
          }
        }
        if(!foundUser)
        {
          JOptionPane.showMessageDialog(frame, "Username not found/registered");
          System.out.println("Username not found/registered.");
        }
        else if(!foundPass)
        {
          JOptionPane.showMessageDialog(frame, "Password is incorrect");
          System.out.println("Password is incorrect.");
        }
        else
        {
          
        }
      }
    }
    );
    delete.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent click)
      {
        boolean correctUser = false;
        boolean correctPass = false;
        for(Account a : Main.LogInInfo)
        {
          if(userField.getText().equals(a.getUsername()))
          {
            correctUser = true;
            if(String.valueOf(passwordField.getPassword()).equals(a.getPassword()))
            {
              correctPass = true;
            }
          }
        }
        if(correctUser && correctPass)
        {
          for(int i = 0; i < Main.LogInInfo.size(); i++)
          {
            if(Main.LogInInfo.get(i).getUsername().equals(userField.getText()))
            {
              Main.deleteAccount(i);
              i--;
            }
          }
          JOptionPane.showMessageDialog(frame, "Account has been deleted.");
        }
        else
        {
          if(!correctUser)
          {
            JOptionPane.showMessageDialog(frame, "Username not found/registered");
            System.out.println("Username not found/registered.");
          }
          else if(!correctPass)
          {
            JOptionPane.showMessageDialog(frame, "Password is incorrect");
            System.out.println("Password is incorrect.");
          }
          else
          {
            
          }
        }
      }
      }
    );
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
    public static String passwordIsStrongEnough(String pass) //checks to see if password is strong enough
    {
        if (checkSpecialChars(pass)==false)
        {
            return "Password does not contain a special character.";
        }
        if (pass.length()<8)
        {
            return "Password is too short. ";
        }
        if (checkUppercase(pass)==false)
        {
            return "Password does not contain an uppercase. ";
        }
        if (checkLowercase(pass)==false)
        {
            return "Password does not contain a lowercase. ";
        }
        return "Strong";
    }
  @Override
    public void actionPerformed(ActionEvent e)
  {
  }
    //{
      //count++;
      //label.setText("Number of clicks: " + count);
      //if (count == 20)
      //{
        //labelTwo.setText("");
      //}
    //}
}
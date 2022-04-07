import javax.swing.*;
import java.util.*;
import java.lang.Math;
//MAKE ACCOUNT CLASS AND STORE AN ARRAYLIST OF ACCOUNTS
public class Main 
{
  public static ArrayList<Account> LogInInfo = new ArrayList<>();
  public static void addAccount(Account a)
  {
    LogInInfo.add(a);
  }
  public static ArrayList<Account> getLogInInfo()
  {
    return LogInInfo;
  }
  public static void deleteAccount(int i)
  {
    LogInInfo.remove(i);
  }  
  public static void main(String[] args) 
  {
    Admin[][] accString = new Admin[1][2];
    accString[0][0] = new Admin();
    accString[0][1] = new Admin();
    for(Admin[] a : accString)
    {
      System.out.println(a);
    }
    for(int r = 0; r < accString.length; r++)
    {
      for(int c = 0; c < accString[0].length; c++)
      {
        System.out.println(accString[r][c]);
      }
    }
    for(int row = 0; row < accString[0].length; row++)
    {
      for(int col = 0; col < accString.length; col++)
      {
        System.out.println(accString[col][row]);
      }
    }
    LogInInfo.add(new Admin());
    Account a = new Account("ZhongXina", "Johncena23!");
    Admin.makeAdmin(a);
    System.out.println(a);
    Account b = new Admin();
    System.out.println(b);
    Account[] accountList = {a, b};
    System.out.println(accountList);
    ArrayList<Account> arrayAccount = new ArrayList<>();
    arrayAccount.add(a);
    arrayAccount.add(b);
    System.out.println(arrayAccount);
    System.out.println("---------------------------------------------------------------------------------------------");
    //ArrayList<Account> LogInInfo = new ArrayList<>();
    //LogInInfo.add(new Account("omegaadmin", "omegapassword"));
    new GUI();
    //new Window();
    //ArrayList<Double[]> AccountInfo = new ArrayList<Double[]>();
    //LogInInfo.add(new ArrayList<String>());
    //AccountInfo.add(new Double[]{100000.0});
  }
}
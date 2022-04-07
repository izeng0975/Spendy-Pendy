import java.util.*;
public class Admin extends Account
{
    private int money;
    public Admin()
    {
        super("omegaadmin", "omegapassword");
        money = Integer.MAX_VALUE;
    }
    public Admin(String u, String p)
    {
        super(u, p);
        money = Integer.MAX_VALUE;
    }
    public static void makeAdmin(Account a)
    {
        System.out.println(new Admin(a.getUsername(), a.getPassword()));
    }
    public boolean equals(Admin a)
    {
        return true;
    }
    public String toString()
    {
        return "This is the omega admin account.";
    }
}

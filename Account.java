public class Account
  {
    private String username;
    private String password;
    private double overallBalance;
    private double foodSpend;
    private double utilSpend;
    private double investmentSpend;
    private double entertainmentSpend;
    
    public Account(String u, String p)
    {
      username = u;
      password = p;

    }
    public Account()
    {
      
    }

    public String getUsername()
    {
      return username;
    }
    public String getPassword()
    {
      return password;
    }
    public double getOverallBalance()
    {
      return overallBalance;
    }
    public double getFoodSpend()
    {
      return foodSpend;
    }
    public double getUtilSpend()
    {
      return utilSpend;
    }
    public double getInvestmentSpend()
    {
      return investmentSpend;
    }
    public double getEntertainmentSpend()
    {
      return entertainmentSpend;
    }
    public void setUsername(String u)
    {
      username = u;
    }
    public void setPassword(String p)
    {
      password = p;
    }
    public void setOverallBalance(double n)
    {
      overallBalance = n;
    }
    public void setFoodSpend(double n)
    {
      foodSpend = n;
    }
    public void setUtilSpend(double n)
    {
      utilSpend = n;
    }
    public void setInvestmentSpend(double n)
    {
      investmentSpend = n;
    }
    public void setEntertainmentSpend(double n)
    {
      entertainmentSpend = n;
    }
    public boolean equals(Object account)
    {
      return (username.equals(((Account)account).username)) && (password.equals(((Account)account).password));
    }
    public String toString()
    {
      return "The account username is " + username + " with a password of " + password;
    }
    
  }
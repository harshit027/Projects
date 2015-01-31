class NICSystem
{
  public static PnCClerkSystem pncClerk;
  public static AccountClerkSystem accountClerk;
  public static MainUI m;
  public static void main(String args[])
  {
    NIC.init();
    m = new MainUI();
    String cont="Y";
    while(cont.equalsIgnoreCase("Y"))
    {
      int option = m.getOption();
      if(option == 1)
      {
        pncClerk=new PnCClerkSystem();
        pncClerk.run();
      }
      else
        if(option == 2)
      {
        
        accountClerk=new AccountClerkSystem();
        accountClerk.run();
      }
      else
       if(option == 0)
      {
        break;
      }
      
      else
      {
        m.displayErrorMessage("Invalid option selected in Main Menu");
      }
   //   cont = m.getContinueSignal();
    }
    m.displayErrorMessage("Thank you for using the NIC System !");
  }
}
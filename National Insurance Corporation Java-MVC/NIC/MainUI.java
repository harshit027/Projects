import java.util.Scanner;

class MainUI
{
  private Scanner in;
  
  public int getOption()
  {
      int option;
      displayMainMenu();
      in = new Scanner(System.in);
      option = Integer.parseInt(in.nextLine());
      return option;  
  }
  
  public void displayErrorMessage(String errorMessage) 
  {
    System.out.println(errorMessage);
  }
    
  public String getContinueSignal() 
  {
      in = new Scanner(System.in);
      System.out.print("Do you wish to continue? Y/N : ");
      String response = in.nextLine();
      return(response);
  }
  
  public void displayMainMenu()
  {
    System.out.println();
    System.out.println("-----------Main Menu-------------");
    System.out.println("1. P&C Clerk");
    System.out.println("2. Account Clerk");
    System.out.println("0. Exit");
    System.out.print("Select option : ");
  }
  
}
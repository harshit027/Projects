import java.util.Scanner;

class PnCClerkUI
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
    
  public void displayMainMenu()
  {
    System.out.println();
    System.out.println("-----------P&C Clerk-------------");
    System.out.println("1. Add Claim");
    System.out.println("2. Update Claim");
    System.out.println("0. Go Back");
    System.out.print("Select option : ");
  }
  
  
}
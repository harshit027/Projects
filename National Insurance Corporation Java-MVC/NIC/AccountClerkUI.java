import java.util.Scanner;

class AccountClerkUI
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
    System.out.println("-----------Account Clerk-------------");
    System.out.println("1. Add Customer");
    System.out.println("2. Settle Claim");
    System.out.println("3. Process Matured Policies");
    System.out.println("4. Generate Product Report");
    System.out.println("5. Generate New Policies Report");
    System.out.println("6. Generate Matured Policies Report");
    System.out.println("7. Generate Claims Report");
    System.out.println("0. Go Back");
    System.out.print("Select option : ");
  }
  
  
}
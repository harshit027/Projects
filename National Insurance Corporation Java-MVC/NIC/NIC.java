import java.util.ArrayList;
import java.util.Date;

class NIC
{
  static ArrayList<Customer> customerList = new ArrayList<Customer>();
  static ArrayList<Policy> policyList= new ArrayList<Policy> ();
  static ArrayList<Claim> claimList= new ArrayList<Claim>();
  static int[] productReport;
  static int newPolicyReport;
  static int maturedPolicyReport;
  static int claimsReport;
  
  
  public static void init()
  {
    policyList.add(new Policy(101,100000,20000,9,1,6));
    policyList.add(new Policy(101,100000,20000,9,2,7));
    policyList.add(new Policy(101,100000,20000,9,3,8));
    policyList.add(new Policy(102,200000,38000,9.5,1,6.5));
    policyList.add(new Policy(102,200000,38000,9.5,2,7.5));
    policyList.add(new Policy(102,200000,38000,9.5,3,8.5));
    policyList.add(new Policy(103,300000,56000,10,1,7.5));
    policyList.add(new Policy(103,300000,56000,10,2,8.5));
    policyList.add(new Policy(103,300000,56000,10,3,9.5));
    policyList.add(new Policy(104,500000,92500,11,1,9.5));
    policyList.add(new Policy(104,500000,92500,11,2,11));
    policyList.add(new Policy(104,500000,92500,11,3,13));   
  }
  
  public static String addCustomer(int policyId, int duration, String name, char sex, String nomineeDetails, String address)
  {
    String display="";
    Date date = new Date();
    date.setYear(date.getYear()-3);
    Policy policy=null;
    for(int i=0;i<policyList.size();i++)
    {
      if(policyList.get(i).checkPolicy(policyId,duration))
      {
        policy=policyList.get(i);
        break;
      }
    }
    
    if(policy!=null)
    {
      int newCustId=1;
      if(customerList.size()>0)
        newCustId=(customerList.get(customerList.size()-1).getCustomerId())+1;
      Customer newCustomer=new Customer(policy,newCustId ,name, sex,nomineeDetails, address,date);
      customerList.add(newCustomer);
      display="Customer added. CustomerId= "+newCustId;
    }
    else
      display="Error finding that policy"; 
  return display;
  }
  
  public static Customer getCustomer(int customerId)
  {
    for(Customer c: customerList)
    {
      if(c.getCustomerId()==customerId)
      {
        return c;
      }
    }
    return null;
  }
  
  public static String addClaim(int customerId)
  {
    Customer c=getCustomer(customerId);
    String display="";
    String status="new";
    if(c==null)
    {
      display="CustomerId not found";
    }
    else
    {
      int newClaimId=1;
      if(claimList.size()>0)
        newClaimId=(claimList.get(claimList.size()-1).getClaimId())+1;
      Claim newClaim=new Claim(newClaimId, customerId, status);
      claimList.add(newClaim);
      c.addClaim(newClaim);
      display="Claim added for customer. ClaimId= "+newClaimId;
    }
    return display;
  }
  
  public static Claim getClaim(int claimId)
  {
    for(Claim c: claimList)
    {
      if(c.getClaimId()==claimId)
        return c;
    }
    return null;
  }
  
  public static String updateClaim(int claimId, String status)
  {
    String display="ClaimId not found";
    Claim c = getClaim(claimId);
    if(c!=null)
    {
      c.setClaimStatus(status);
      display="Status updated successfully";
    }
    return display;
  }
  
  public static String settleClaim(int claimId)
  {
    String display="ClaimId not found";
    Claim c = getClaim(claimId);
    if(c!=null)
    {
      String status=c.getClaimStatus();
      if(status.equalsIgnoreCase("Accepted"))
      {
        c.setClaimStatus("Settled");
        display="Claim settled";
      }
      else
        display="Error settling claim. Status is "+status;
    }
    return display;   
  }
  
  public static void processMaturedPolicies()
  {
    for(Customer cust: customerList)
    {
      cust.checkMaturedStatus();
    }
  }
  
  public static int[] generateProductReport()
  {
    int[] productReport=new int[policyList.size()];
    for(int i=0;i<productReport.length;i++)
      productReport[i]=0;
    
    for(Customer cust: customerList)
    {
      Policy p = cust.getPolicy();
      for(int j=0;j<policyList.size();j++)
      {
        if(p.equals(policyList.get(j)))
        {
          productReport[j]++;
          break;
        }
      }
    }
    return productReport;
  }
  
  public static int generateNewPolicyReport()
  {
    Date currDate=new Date();
    int currYear=currDate.getYear();
    int currMonth=currDate.getMonth();
    newPolicyReport=0;
    
    for(Customer cust: customerList)
    {
      Date custStartDate=cust.getStartDate();
      int custYear=custStartDate.getYear();
      int custMonth=custStartDate.getMonth();
      if(custYear==currYear && custMonth==currMonth)
        newPolicyReport++;
    }
    return newPolicyReport;
  }
  
  public static int generateMaturedPolicyReport()
  {
    Date currDate=new Date();
    int currYear=currDate.getYear();
    int currMonth=currDate.getMonth();
    maturedPolicyReport=0;
    for(Customer cust: customerList)
    {
      double maturedAmount=cust.getMaturedAmount();
      if(maturedAmount>0)
      {
      Date maturedDate=cust.checkMaturedStatus();
      int custYear=maturedDate.getYear();
      int custMonth=maturedDate.getMonth();
      if(custYear==currYear && custMonth==currMonth)
        maturedPolicyReport++;
      }
    }
    return maturedPolicyReport;
  }
  
  public static int generateClaimsReport()
  {
    Date currDate=new Date();
    int currYear=currDate.getYear();
    int currMonth=currDate.getMonth();
    claimsReport=0;
    
    for(Claim claim: claimList)
    {
      if(claim.getClaimStatus().equals("Settled"))
      {
      Date claimDate=claim.getDate();
      int custYear=claimDate.getYear();
      int custMonth=claimDate.getMonth();
      if(custYear==currYear && custMonth==currMonth)
        claimsReport++;
      }
    }
    return claimsReport;
  }
  
  
  
  
}


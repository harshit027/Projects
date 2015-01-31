class ReportGenerator
{
  public static void generateReport(int type)
  {
    switch(type)
    {
      case 1:
      {
        NIC.processMaturedPolicies();
        break;
      }
      case 2:
      {
        int[] ret= NIC.generateProductReport();
        DisplayReport.display("Product report for each policy",ret);
        break;
      }
      case 3:
      {
        int ret = NIC.generateNewPolicyReport();
        DisplayReport.display("Number of new policies in the month ",ret);
        break;
      }
      case 4:
      {
        int ret = NIC.generateMaturedPolicyReport();
        DisplayReport.display("Number of matured policies in the month ",ret);
        break;
      }
      case 5:
      {
        int ret = NIC.generateClaimsReport();
        DisplayReport.display("Number of new claims in the month ",ret);
        break;
      }
      default :
        break;
    }
  }
}
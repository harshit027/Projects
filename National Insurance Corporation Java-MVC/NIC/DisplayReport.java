class DisplayReport
{
  public static void display(String reportName, int reportValue)
  {
    System.out.println(reportName+"---->"+reportValue);
  }
  
  public static void display(String reportName, int [] reportValue)
  {
    System.out.println(reportName);
    for(int i=0;i<reportValue.length;i++)
    {
      System.out.println("policy "+(i+1)+":"+reportValue[i]);
    }
    
  }
}
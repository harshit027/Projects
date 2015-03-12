package webCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crawler {

	private String seedUrl;
	private String seedWord;
	public static int counter =0;
	private ArrayList<MyThread> myThreadList;
	public static ArrayList<String> restrictedList;
	Crawler(String seedUrl, String seedWord)
	{
		myThreadList=new ArrayList<MyThread>();
		restrictedList= new ArrayList<String>();
		this.seedUrl=seedUrl;
		this.seedWord=seedWord;
	}
	public void processPage() 
	{
		int counter =0;
		try
		{
			Document doc = Jsoup.connect(this.seedUrl).get();

			Elements questions = doc.select("a[href]");
			for(Element link: questions){
				System.out.println(link.attr("abs:href"));
				savePage(link.attr("abs:href"), ""+counter++);
				break;
			}
		}
		catch(IOException ioex)
		{
			//ioex.printStackTrace();
		}
	}

	public ConcurrentMap crawl(String seedUrl, String seedWord, int noOfProcessors) 
	{

		Resource resourceObj= new Resource();
		try
		{
			for(int i=0; i<noOfProcessors;i++)
			{
				MyThread myThread= new MyThread(resourceObj,seedUrl, seedWord, myThreadList);
				myThread.join();
			}
			for(MyThread myThread : myThreadList)
			{
				myThread.start();
			}
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}

		return resourceObj.getVisitedList();
	}

	public void stopCrawling()
	{
		for(MyThread myThread : myThreadList)
			myThread.kill();
	}

	public static void savePage(String link, String filename)
	{
		try
		{
			URL oracle = new URL(link);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\MyStuffs\\Workspaces\\MyWorkspace\\Downloads\\"+filename+".htm"));

			String inputLine;
			while ((inputLine = in.readLine()) != null){
				try{
					writer.write(inputLine);
				}
				catch(IOException e){
					//e.printStackTrace();
					return;
				}
			}
			in.close();
			writer.close();
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
		}
	}


	public ArrayList<String> readRobotFile(String seedUrl) 
	{

		ArrayList<String> restrictedLinksList=new ArrayList<String>();;
		try
		{

			URL oracle = new URL(seedUrl+"/robots.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(oracle.openStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
			{
				if(inputLine.contains("Disallow"))
				{
					inputLine.replaceAll(" ", "");
					restrictedLinksList.add(inputLine.split(":")[1]);
				}
			}
			in.close();
			//System.out.println(restrictedLinksList);
		}
		catch(Exception ex)
		{

		}
		return restrictedLinksList;
	}

}



//Parallel processing
class MyThread extends Thread
{
	Resource resourceObj;
	String seedUrl;
	String seedWord;
	boolean isRunning;
	MyThread(Resource localResourceObj, String seedUrl, String seedWord, ArrayList<MyThread> myThreadList)
	{
		this.resourceObj=localResourceObj;
		this.seedUrl=seedUrl;
		this.seedWord=seedWord;
		isRunning=true;
		myThreadList.add(this);
		//start();
	}

	public void run()
	{

		while(Driver.isRunning)
		{
			try
			{
				if(this.seedWord==null)
				{
					if(resourceObj.getVisitedListSize()==0)
					{
						resourceObj.addVisitedList(this.seedUrl);
						if(!this.isRestricted(seedUrl))
						{
							Document doc = Jsoup.connect(this.seedUrl).get();
							Elements questions = doc.select("a[href]");
							for(Element link: questions){
								System.out.println(link.attr("abs:href"));
								resourceObj.addVisitedList(link.attr("abs:href"));
								Crawler.savePage(link.attr("abs:href"), ""+Crawler.counter++);
							}
						}

					}
					else
					{
						Iterator iterator=resourceObj.getVisitedList().keySet().iterator();
						while(iterator.hasNext())
						{
							this.seedUrl=resourceObj.getNextLink();
							if(this.seedUrl!=null && !this.isRestricted(seedUrl))
							{
								Document doc = Jsoup.connect(this.seedUrl).get();
								Elements questions = doc.select("a[href]");
								for(Element link: questions){
									this.seedUrl=link.attr("abs:href");
									if(this.seedUrl.contains(Interface.hostName))
									{
										//System.out.println(link.attr("abs:href"));
										resourceObj.addVisitedList(link.attr("abs:href"));
										Crawler.savePage(link.attr("abs:href"), ""+Crawler.counter++);
									}
									
								}
							}
						}
						

					}
				}
				else
				{
					if(resourceObj.getVisitedListSize()==0)
					{
						resourceObj.addVisitedList(this.seedUrl);
						if(!this.isRestricted(seedUrl))
						{
							Document doc = Jsoup.connect(this.seedUrl).get();
							Elements questions = doc.select("a[href]");
							for(Element link: questions)
							{
								
								this.seedUrl=link.attr("abs:href");
								if(this.seedUrl.contains(this.seedWord))
								{
									System.out.println(link.attr("abs:href"));
									resourceObj.addVisitedList(link.attr("abs:href"));
									Crawler.savePage(link.attr("abs:href"), ""+Crawler.counter++);
								}

							}
						}
						else
						{
							//System.out.println("No URLs found.Seed word out of seed URL's context.");
						}

					}
					else
					{
						Iterator iterator=resourceObj.getVisitedList().keySet().iterator();
						while(iterator.hasNext())
						{
							this.seedUrl=resourceObj.getNextLink();
							{
								if(this.seedUrl!=null && !this.isRestricted(seedUrl))
								{
									Document doc = Jsoup.connect(this.seedUrl).get();
									Elements questions = doc.select("a[href]");
									for(Element link: questions)
									{
										
										this.seedUrl=link.attr("abs:href");
										if(this.seedUrl.contains(this.seedWord))
										{
											System.out.println(link.attr("abs:href"));
											resourceObj.addVisitedList(link.attr("abs:href"));
											Crawler.savePage(link.attr("abs:href"), ""+Crawler.counter++);
										}
									}
								}
							}
						}
					}

				}



			}
			catch(IOException ioex)
			{
				//ioex.printStackTrace();
			}
		}


	}

	public void kill()
	{
		Driver.isRunning=false;
	}

	public boolean isRestricted(String url)
	{
		boolean flag=false;
		for(String restrictedPhrase : Crawler.restrictedList)
		{
			if(url.contains(restrictedPhrase))
			{
				flag=true;
				break;
			}
		}
		return flag;
	}
}

class Resource 
{
	private ConcurrentMap visitedList = new ConcurrentHashMap();
	private Iterator iterator=this.visitedList.keySet().iterator();

	synchronized void addVisitedList(String key)
	{
		if(!this.visitedList.containsKey(key));
		this.visitedList.put(key,key);

	}
	synchronized String getNextLink()
	{
		if(iterator.hasNext())
			return iterator.next().toString();
		else
			return null;
	}

	synchronized int getVisitedListSize()
	{
		return visitedList.size();
	}

	public ConcurrentMap getVisitedList()
	{
		return this.visitedList;
	}
}

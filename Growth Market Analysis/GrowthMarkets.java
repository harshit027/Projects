package algos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GrowthMarkets {

	public static void main(String args[])
	{
		//Data file name
		String dataFile="F:\\MyStuffs\\Workspaces\\MyWorkspace\\Downloads\\75f647c2ac77-Metropolitan_Populations_2010-2012_.csv";
		
	
		System.out.println("\nTop five cities to target based on highest population growth (% change) between 2010-2012. (50,000 population minimum)");
		Heap heap= new Heap(5,"MIN");
		getHighestCities(heap,dataFile);
		
		System.out.println("\nTop five cities to avoid based on the most shrinking population (% change) between 2010-2012. (50,000 population minimum)");
		heap=new Heap(5,"MAX");
		getLowestCities(heap,dataFile);
		
		System.out.println("\nTop five states with highest cumulative growth (% change combined across all metropolitan areas) between 2010-2012.");
		heap = new Heap(5,"MIN");
		getHighestStates(heap,dataFile);
	}
	
	/*Function to get the top 5 cities of interest*/
	public static void getHighestCities(Heap heap, String dataFile)
	{
		Place[] places=null;
		BufferedReader br=null;
		String line;
		try
		{
			br= new BufferedReader(new FileReader(dataFile));
			br.readLine();
			int j=0;
			places= new Place[5];
			
			//initializing the heap with first 5 cities
			while((line=br.readLine())!= null && j<5)
			{
				
				String [] values= line.split(",");
				if(Integer.parseInt(values[2])>50000)
				{
					double populationGrowth=calculateGrowth(Double.parseDouble(values[2]),Double.parseDouble(values[3]),Double.parseDouble(values[4]));
					Place place= new Place((values[0]+","+values[1]).replaceAll("\"", ""), populationGrowth);
					places[j]=place;
					j++;
				}
				
			}
		}
		catch(IOException ex)
		{
			ex.toString();
		}
		
		//Sorting the heap based on population growths
		heap.sort(places);
    	
    	//Updating the heap with new cities based on population growths
    	try
    	{
    		while((line=br.readLine())!= null)
    		{
    			
    			String [] values= line.split(",");
    			if(Integer.parseInt(values[2])>50000)
    			{
    				
    				double populationGrowth=calculateGrowth(Double.parseDouble(values[2]),Double.parseDouble(values[3]),Double.parseDouble(values[4]));
    				Place place= new Place((values[0]+","+values[1]).replaceAll("\"", ""), populationGrowth);
    				if(places[0].getPopulationGrowth()<place.getPopulationGrowth())
    				{
    					places[0]=place;
    					//Rearranging heap based on population growths
    					heap.sort(places);
    				}
    				
    			}
    			
    		}
    	}
    	catch(IOException ex)
    	{
    		ex.toString();
    	}
    	
    	//printing city names	
    	for(int i=0;i<places.length;i++)
    	{
    		System.out.println(places[i].getName());
    	}
	}
	
	public static void getLowestCities(Heap heap, String dataFile)
	{
		Place[] places=null;
		BufferedReader br=null;
		String line;
		try
		{
			br= new BufferedReader(new FileReader(dataFile));
			br.readLine();
			int j=0;
			places= new Place[5];
			
			//initializing the heap with first 5 cities
			while((line=br.readLine())!= null && j<5)
			{
				
				String [] values= line.split(",");
				if(Integer.parseInt(values[2])>50000)
				{
					double populationGrowth=calculateGrowth(Double.parseDouble(values[2]),Double.parseDouble(values[3]),Double.parseDouble(values[4]));
					Place place= new Place((values[0]+","+values[1]).replaceAll("\"", ""), populationGrowth);
					places[j]=place;
					j++;
				}
				
			}
		}
		catch(IOException ex)
		{
			ex.toString();
		}
		
		//Sorting top five cities based on population growths
		heap.sort(places);
    	
    	
		//Updating the heap with new cities based on population growths
    	try
    	{
    		while((line=br.readLine())!= null)
    		{
    			
    			String [] values= line.split(",");
    			if(Integer.parseInt(values[2])>50000)
    			{
    				
    				double populationGrowth=calculateGrowth(Double.parseDouble(values[2]),Double.parseDouble(values[3]),Double.parseDouble(values[4]));
    				Place place= new Place((values[0]+","+values[1]).replaceAll("\"", ""), populationGrowth);
    				if(places[0].getPopulationGrowth()>place.getPopulationGrowth())
    				{
    					places[0]=place;
    					heap.sort(places);
    				}
    				
    			}
    			
    		}
    	}
    	catch(IOException ex)
    	{
    		ex.toString();
    	}
    	
    		
    	for(int i=0;i<places.length;i++)
    	{
    		System.out.println(places[i].getName());
    	}
	}
	
	
	/*Function to get top five states*/
	public static void getHighestStates(Heap heap, String dataFile)
	{
		Place[] places= null;
		String line=null;
		String currState=null, prevState=null;
		BufferedReader br= null;
		double sum2010=0,sum2011=0,sum2012=0;
		try
		{
			br= new BufferedReader(new FileReader(dataFile));
			br.readLine();
			places=new Place[5];
			prevState=br.readLine().split(",")[1];
			
			int i=0;
			while((line=br.readLine())!= null && i<5)
			{
				currState=line.split(",")[1];
				
				//Checking if the new state has started
				if(!prevState.equals(currState)){
					Place place= new Place(prevState.replaceAll("\"", ""),calculateGrowth(sum2010, sum2011, sum2012));
					places[i]= place; 
					sum2010=Double.parseDouble(line.split(",")[2]);
					sum2011=Double.parseDouble(line.split(",")[3]);
					sum2012=Double.parseDouble(line.split(",")[4]);
					prevState=currState;
					i++;
					continue;
				}
				
				//Adding the population of all the cities of one state
				sum2010+=Double.parseDouble(line.split(",")[2]);
				sum2011+=Double.parseDouble(line.split(",")[3]);
				sum2012+=Double.parseDouble(line.split(",")[4]);
				prevState=currState;
			}
			
			
		}
		catch(IOException ioex)
		{
			ioex.toString();
		}
		
		heap.sort(places);
		
		
		//Updating the heap with new states based on their population growth
		try
		{
			while((line=br.readLine())!= null)
			{
				currState=line.split(",")[1];
				if(!prevState.equals(currState)){
					Place place= new Place(prevState.replaceAll("\"", ""),calculateGrowth(sum2010, sum2011, sum2012));
					if(places[0].getPopulationGrowth()<place.getPopulationGrowth())
    				{
    					places[0]=place;
    					heap.sort(places);
    				}
					sum2010=Double.parseDouble(line.split(",")[2]);
					sum2011=Double.parseDouble(line.split(",")[3]);
					sum2012=Double.parseDouble(line.split(",")[4]);
					prevState=currState;
					
					continue;
				}		
				sum2010+=Double.parseDouble(line.split(",")[2]);
				sum2011+=Double.parseDouble(line.split(",")[3]);
				sum2012+=Double.parseDouble(line.split(",")[4]);
				prevState=currState;
			}
			
		}
		catch(IOException ioex)
		{
			ioex.toString();
		}
		
		for(int j=0;j<5;j++)
		{
			System.out.println(places[j].getName());
		}
	}
	
	/*Function to calculate population growth, average of growth in between 2010-11 and 2011-12*/
	public static double calculateGrowth(double y2010,double y2011, double y2012)
	{
		return ((((y2011-y2010)*100)/y2011)+(((y2012-y2011)*100)/y2012))/2;
	}
	
}

/*Class Place for representing the node Heap*/
class Place
{
	String name;
	double populationGrowth;
	
	Place(String name, double popultionGrowth)
	{
		this.name=name;
		this.populationGrowth=popultionGrowth;
	}
	
	String getName()
	{
		return name;
	}
	
	double getPopulationGrowth()
	{
		return populationGrowth;
	}
}



/*Heap implementation for class Place, sorts on population growth*/
class Heap 
{
	private Place []a;
	private int size;
	private int left, right, smallest,largest;
	private String type;
	
	Heap(int size, String type)
	{
		this.size=size;
		a=new Place[size];
		this.type=type;
	}
	public void buildHeap(Place []a)
	{
		size= a.length-1;
		for(int i=size/2;i>=0;i--)
		{
			if(this.type=="MIN")
			minHeapify(a,i);
			if(this.type=="MAX")
			maxHeapify(a,i);
		}
	}
	
	/*For updating min heap*/
	public void minHeapify(Place []a, int i)
	{
		left=2*i+1;
    	right=2*i+2;
    	if(left <= size && a[left].getPopulationGrowth() > a[i].getPopulationGrowth()){
    		smallest=left;
    	}
    	else{
    		smallest=i;
    	}
    	
    	if(right <= size && a[right].getPopulationGrowth() > a[smallest].getPopulationGrowth()){
    		smallest=right;
    	}
    	if(smallest!=i){
    		swap(i,smallest);
    		minHeapify(a, smallest);
    	}
	}
	
	/*For updating max heap*/
	public void maxHeapify(Place []a, int i)
	{
		left=2*i+1;
    	right=2*i+2;
    	if(left <= size && a[left].getPopulationGrowth() < a[i].getPopulationGrowth()){
    		largest=left;
    	}
    	else{
    		largest=i;
    	}
    	
    	if(right <= size && a[right].getPopulationGrowth() < a[largest].getPopulationGrowth()){
    		largest=right;
    	}
    	if(largest!=i){
    		swap(i,largest);
    		maxHeapify(a, largest);
    	}
	}
	public void swap(int i, int j)
	{
    	Place place =this.a[i];
    	this.a[i]=a[j];
    	this.a[j]=place; 
    }
	
	public void sort(Place []a)
	{
		this.a=a;
		buildHeap(a);
		
		for(int i=size;i>0;i--){
    		swap(0, i);
    		size=size-1;
 
    		if(this.type=="MIN")
    		minHeapify(a,0);
    		if(this.type=="MAX")
    		maxHeapify(a,0);
    	}
	}

}

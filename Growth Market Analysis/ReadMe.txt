Problem

1) Top five cities to target based on highest population growth (% change) between 2010-2012. (50,000 population minimum)

2) Top five cities to avoid based on the most shrinking population (% change) between 2010-2012. (50,000 population minimum)

3) Top five states with highest cumulative growth (% change combined across all metropolitan areas) between 2010-2012.

Data Set
75f647c2ac77-Metropolitan_Populations_2010-2012_.csv

Approach
1. Create min heap for finding the top five cities/states with highest population growth.
2. Create max heap for finding the top five cities with lowest population growth.
3. Cities/States are represented in Heap as objects of class "Place".
4. "GrowthMarkets" class is the driver class which calculates these metrics with the following functions :
getHighestCities(Heap heap,String dataFileName)
getLowestCities(Heap heap,String dataFileName)
getHighestStates(Heap heap,String dataFileName)

The functions print the names on the console

5. Heap is initialized using constructor Heap(int size, String type), where type must be "MIN" or "MAX"
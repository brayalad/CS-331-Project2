
import java.util.Scanner; //Scanner used to read in user input
 
public class Knapsack{ //Class that contains both knapsack problems
	
	static Scanner input = new Scanner(System.in);//Creating an instance of the scanner class
  
    public static void main(String args[]){ //Main method where program starts
      
    
    	runProgram();//Running the program
  
    
    }
    
    
    public static void runProgram(){ 
 	
    		while(true){//While loop that will run the program until user exits
    		
    			System.out.println("Please enter which operation you would like to run:");//Prints out the menu
    			System.out.println("1) Fractional Knapsack");//Enter 1 for fractional knapsack problem
    			System.out.println("2) Zero-One Knapsack");//Enter 2 for 0/1 knapsack problem
    			System.out.println("3) Exit Program");//Enter 3 to exit
    		
    			int answer = input.nextInt();//Reading user input
    		
    			if(answer == 1) //If user enters 1, run fractional knapsack
    				getInfo(1);//Get info for fractional knapsack
    			else if(answer == 2)//If user enters 2, run 0/1 knapsack
    				getInfo(2);//get info for 0/1 knapsack
    			else if(answer == 3)//If user enters 3, exit program
    				System.exit(0);//exit program
    			else//if user enters invalid input
    				System.out.println("Invalid input.\nPlease enter a valid input\n");//prompt user for valid input
    		
    		}
    	
    }
    
    public static void getInfo(int operation){ //Method that gets info from user
    	
    
   
    		if(operation == 1) { //getting info for fractional knapsack
    			
    			
    			System.out.println("Enter number of items:");  //Ask user for number of items
    			int numberItems = input.nextInt();  //get input
    			int[][] items=new int[2][numberItems]; //create 2D array of items 
    			
    			System.out.println("Enter the weights of each items:"); //Ask user for weights of each items
    			for(int i=0;i<numberItems;i++) //for loop runs for number of items
    				items[0][i]=input.nextInt(); //placing user input in first row of 2D array
 
    			System.out.println("Enter the values of each items:");//Ask user for profit of items
    			for(int i=0;i<numberItems;i++)  //for loop runs for number of items
    				items[1][i]=input.nextInt(); //placing user input in second row of 2D array
 
    			System.out.println("Enter maximum volume of knapsack:"); //asking user for max capacity
    			int maxVolume=input.nextInt();  //get input
    	
    			fractionalKnapsack(numberItems, maxVolume, items); //run fractional knapsack
        
    	}
    	
    		else if(operation == 2){//Method for getting info from users
    	
            System.out.println("Enter number of items:");//Ask user for number of items
            int numberItems=input.nextInt(); //get input
            int[] weights = new int[numberItems+1]; //creates array to hold weights 
            int[] profit = new int[numberItems+1]; //creates array to hold profits
            
            System.out.println("Enter the weights of each items:");//asks user for items weights
            for(int i=0;i<numberItems;i++) //for loop running for number of items
                weights[i]=input.nextInt();  //saves input in weight array  
     
            System.out.println("Enter the values of each items:");//asks user for items profits
            for(int i=0;i<numberItems;i++)  //for loop running for number of items
                profit[i]=input.nextInt(); //saves input in profit array  
     
            System.out.println("Enter maximum volume of knapsack:"); //Ask user for maximum volume
            int maxVolume=input.nextInt(); //read input
    		
    			zerOneKnapsack(numberItems, maxVolume, weights, profit);// runs 0/1 knapsack
        	
        	
    		}
    	
    	
    }
    
    

    public static void fractionalKnapsack(int numberItems, int maxVolume, int[][] items){ //fractional knapsack funtion
    	
    	int j = 0; //index initilized to 0
    	float sum = 0; //the sum of the max profit
    float max; //variable to check for highest profit/weight item 
          
  
        while(maxVolume>=0){  //loop runs while knapsack is not full
        	
           max = 0;  //max item initilized to 0
           
            for(int i=0;i<numberItems;i++){ //loop runs for number of items
            	
                if(((float)items[1][i])/((float)items[0][i])>max){ //checks if the profit/weight ratio is bigger than the current max item
                	
                    max=((float)items[1][i])/((float)items[0][i]);  //sets max item to new max item
                    j=i;  //sets j to index of current max item
                }  
            }  
            if(items[0][j]>maxVolume) {  //checks if the current max item is greater than the max capacity of knapsack
            
            	 System.out.println(((float)maxVolume/items[0][j]*100) + "% of item: " + (j+1) + " is taken. Adding a weight of " + maxVolume + " with a profit of " + (float)(items[1][j])/(items[0][j]/maxVolume)); //Informs the user of the item and the percentage of that item chosen for the knapsack 
                sum += maxVolume * max; //Adds the profit to the total profit of the knapsack
                maxVolume =- 1; //decreases the max volume by 1
            }  
            else{ //If current max item is less than the max capacity 
             
                System.out.println("100% of item: " + (j+1) + " is taken. Adding a weight of " + items[0][j] + " with a profit of " + (float)items[1][j]); // Informs user which item was added to knapsack 
                maxVolume -= items[0][j];  //max capacity is decreased by weight of item added
                sum += (float)items[1][j];  //total profit is increased by the profit if the item added
                items[1][j]=0;  //the current max item profit is set to 0 so that it will no be chosen again
            }  
        }  
        System.out.println("The total profit is: " + sum + "\n"); //prints out the knapsack total profit
        
    }  
    	
    
    
    
    public static void zerOneKnapsack(int numberItems, int maxVolume, int[] weights, int[] profit) { //runs 0/1 knapsack function
  
  	  int[][] items = new int[numberItems+1][maxVolume+1]; //2D array to hold items taken
  	  int[][] solution = new int[numberItems+1][maxVolume+1];//2D array to hold the problems solution
  	 
  	  for(int i = 1; i <= numberItems; i++) //Filling in the 2D arrays
  	   for(int j = 0; j <= maxVolume; j++) //running for size of knapsack
  	    if((weights[i-1] <= j) && (profit[i-1]+solution[i-1][j-weights[i-1]] > solution[i-1][j])){ //Checking if current element weight is less than index and if the sum of the current profit and solution is greater than the current solution
  	     solution[i][j] = profit[i-1] + solution[i-1][j-weights[i-1]]; //sets the current solution to the sum of the current solution and current element
  	     items[i][j] = 1; //Sets the current element in the item 2D array to 1
  	    }
  	    else{ //If not then set current solution to the solution from the previous index
  	     solution[i][j] = solution[i-1][j]; //setting a new solution
  	     items[i][j] = 0; //Sets current items solution to 0
  	    }
  	  
  	  
  
  	  int knapsack = maxVolume; //setting variable knapsack to the max capacity
  	  for(int i = numberItems; i >= 1; i--) //loop running down for number of items
  	   if(items[i][knapsack] == 1){ //If item index is 1, means item was taken
  		   
  		   System.out.println("Item " +i+ " with weight " +weights[i-1]+ " and profit " +profit[i-1]+ " is taken" );//Prints out item taken
  		  
  	    knapsack -= weights[i-1]; //decreases max capacity by weight of item taken
  	   }
  	  
  	 
  	  System.out.println("The total profit is: " + solution[numberItems][maxVolume] + "\n");//Prints out the total profit
  	 }

    	
    }
    
    
    
    
    
    
    
    
    
    

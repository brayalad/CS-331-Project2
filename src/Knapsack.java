
import java.util.Scanner;
 
public class Knapsack{
	
	Scanner input = new Scanner(System.in);
  
    public static void main(String args[]){ 
      
    	Knapsack ks = new Knapsack();
    	ks.runProgram();
  
    
    }
    
    
    public void runProgram(){ 
 	
    		while(true){
    		
    			System.out.println("Please enter which operation you would like to run:");
    			System.out.println("1) Fractional Knapsack");
    			System.out.println("2) Zero-One Knapsack");
    			System.out.println("3) Exit Program");
    		
    			int answer = input.nextInt();
    		
    			if(answer == 1)
    				getInfo(1);
    			else if(answer == 2)
    				getInfo(2);
    			else if(answer == 3)
    				System.exit(0);
    			else
    				System.out.println("Invalid input.\nPlease enter a valid input");
    		
    		}
    	
    }
    
    public void getInfo(int operation){
    	
    
   
    		if(operation == 1) {
    			
    			
    			System.out.println("Enter number of items:");  
    			int numberItems = input.nextInt(); 
    			int[][] items=new int[2][numberItems];  
    			
    			System.out.println("Enter the weights of each items:");
    			for(int i=0;i<numberItems;i++)  
    				items[0][i]=input.nextInt();    
 
    			System.out.println("Enter the values of each items:");
    			for(int i=0;i<numberItems;i++)  
    				items[1][i]=input.nextInt(); 
 
    			System.out.println("Enter maximum volume of knapsack:");  
    			int maxVolume=input.nextInt();  
    	
    			fractionalKnapsack(numberItems, maxVolume, items); 
        
    	}
    	
    		else if(operation == 2){
    	
            System.out.println("Enter number of items:");  
            int numberItems=input.nextInt(); 
            int[] weights = new int[numberItems+1];
            int[] profit = new int[numberItems+1];
            
            System.out.println("Enter the weights of each items:");
            for(int i=0;i<numberItems;i++)  
                weights[i]=input.nextInt();    
     
            System.out.println("Enter the values of each items:");
            for(int i=0;i<numberItems;i++)  
                profit[i]=input.nextInt(); 
     
            System.out.println("Enter maximum volume of knapsack:");  
            int maxVolume=input.nextInt();  
    		
    			zerOneKnapsack(numberItems, maxVolume, weights, profit);
        	
        	
    		}
    	
    	
    }
    
    
    
    public void zerOneKnapsack(int numberItems, int maxVolume, int[] weights, int[] profit) {
    	  //table for backtracking to get the items chosen
    	  int[][] items = new int[numberItems+1][maxVolume+1];
    	  int[][] solution = new int[numberItems+1][maxVolume+1];
    	  //filling tables
    	  for(int i = 1; i <= numberItems; i++)
    	   for(int j = 0; j <= maxVolume; j++)
    	    if((weights[i-1] <= j) && (profit[i-1]+solution[i-1][j-weights[i-1]] > solution[i-1][j])){
    	     solution[i][j] = profit[i-1] + solution[i-1][j-weights[i-1]];
    	     items[i][j] = 1;
    	    }
    	    else{
    	     solution[i][j] = solution[i-1][j];
    	     items[i][j] = 0;
    	    }
    	  
    	  
    	  //backtracking
    	  System.out.printf("Items Chosen\n%5s%7s%7s\n", "Item","Weight","Profit");
    	  int knapsack = maxVolume;
    	  for(int i = numberItems; i >= 1; i--)
    	   if(items[i][knapsack] == 1){
    	    System.out.printf("%5d%7d%7d\n",i,weights[i-1],profit[i-1]);
    	    knapsack -= weights[i-1];
    	   }
    	  System.out.println("Maximum profit : " + solution[numberItems][maxVolume]);
    	 }
    	
 
    
    
    public void fractionalKnapsack(int n, int maxVolume, int[][] items){
    	
    	int j = 0;
    	float sum = 0;
    float max;  
          
  
        while(maxVolume>=0){  
        	
           max = 0;  
           
            for(int i=0;i<n;i++){
            	
                if(((float)items[1][i])/((float)items[0][i])>max){
                	
                    max=((float)items[1][i])/((float)items[0][i]);  
                    j=i;  
                }  
            }  
            if(items[0][j]>maxVolume) {  
              
                System.out.println("Quantity of item number: " +  (j+1) + " added is " +maxVolume);  
                sum += maxVolume * max;  
                maxVolume =- 1;  
            }  
            else{  
             
                System.out.println("Quantity of item number: " + (j+1) + " added is " + items[0][j]);  
                maxVolume -= items[0][j];  
                sum += (float)items[1][j];  
                items[1][j]=0;  
            }  
        }  
        System.out.println("The total profit is " + sum);
        
    }  
    	

    	
    }
    
    
    
    
    
    
    
    
    
    

import java.io.IOException;
import java.util.Scanner;
 
public class Knapsack{
	
	Scanner input = new Scanner(System.in);
  
    public static void main(String args[]) throws IOException{ 
      
    	Knapsack ks = new Knapsack();
        ks.getInfo(2);
    
    
    
    }
    
    public void getInfo(int operation){
    	
    	
    	
    	
    	
    	  
      
   
    	if(operation == 1) {
        System.out.println("Enter number of items:");  
        int numberItems=input.nextInt(); 
        int items[][]=new int[2][numberItems];  
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
    	
    	else if(operation ==2){
    		

        	
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
    
    
    
    
    public void zerOneKnapsack(int N, int W, int[] weights, int[] profit) {
    	
    	int NEGATIVE_INFINITY = Integer.MIN_VALUE;
        int[][] m = new int[N + 1][W + 1];
        int[][] sol = new int[N + 1][W + 1];
        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= W; j++)
            {
                int m1 = m[i - 1][j];
                int m2 = NEGATIVE_INFINITY; 
                if (j >= weights[i])
                    m2 = m[i - 1][j - weights[i]] + profit[i];
                m[i][j] = Math.max(m1, m2);
                sol[i][j] = m2 > m1 ? 1 : 0;
            }
        }        
        int[] selected = new int[N + 1];
        for (int n = N, w = W; n > 0; n--)
        {
            if (sol[n][w] != 0)
            {
                selected[n] = 1;
                w = w - weights[n];
            }
            else
                selected[n] = 0;
        }
        System.out.print("\nItems with weight ");
        for (int i = 0; i < N ; i++)
            if (selected[i] == 1)
                System.out.print(profit[i] +" ");
        System.out.println("are selected by knapsack algorithm.");
    	
    }
    
    
    public void fractionalKnapsack(int n, int maxVolume, int[][] items){
    	
    	int j = 0;
    	float sum = 0;
        float max;  
          
        int m = maxVolume;
        
        
        while(m>=0)  
        {  
           max=0;  
            for(int i=0;i<n;i++)  
            {  
                if(((float)items[1][i])/((float)items[0][i])>max)  
                {  
                    max=((float)items[1][i])/((float)items[0][i]);  
                    j=i;  
                }  
            }  
            if(items[0][j]>m)  
            {  
                System.out.println("Quantity of item number: " +  (j+1) + " added is " +m);  
                sum += m * max;  
                m =- 1;  
            }  
            else  
            {  
                System.out.println("Quantity of item number: " + (j+1) + " added is " + items[0][j]);  
                m -= items[0][j];  
                sum += (float)items[1][j];  
                items[1][j]=0;  
            }  
        }  
        System.out.println("The total profit is " + sum);
        
    }  
    	
    
    
    
    
    
    
    
    	
    }
    
    
    
    
    
    
    
    
    
    

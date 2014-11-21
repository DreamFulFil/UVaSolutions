package work.dreamFulFil.UVa100;

import java.io.PrintWriter;
import java.util.Scanner;

class Main {
	
	private int[] cache=new int[1000000];
	
	//Find the length of cycle for the input number
	private int findCycleLength(int num){
		int count=0;
		int number=num;
		//check if cycle length is calculated
		if(cache[num]!=0){
			return cache[num];
		}

		while(num!=1){//terminal condition
			if(num % 2 ==1){//odd
				num=num*3+1;
				count++;//increment count
			}
			else{//even
				num=num>>1;
				count++;//increment count
			}
		}
		count++;
		
		cache[number]=count;
		return count;
	}
	
	//Find maximum cycle length within a range of numbers
	public int findMaxCycleLength(int start,int end){
		int max=0;
		for(int i=start;i<=end;i++){
			int cycleLength=findCycleLength(i);
			if(cycleLength>max){
				max=cycleLength;
			}
		}
		return max;
	}
	
	public static void main(String[] args){
	    Main main=new Main();
	    
	    //references for input and output
		Scanner in = new Scanner(System.in);
	    PrintWriter out = new PrintWriter(System.out, true);
	    
	    // while there is some input to read
	    while (in.hasNextInt()) {
	        int num1 = in.nextInt();
	        int num2 = in.nextInt();
	        int from = Math.min(num1, num2);
	        int to = Math.max(num1, num2);
	        int max = 0;
	        
	        max=main.findMaxCycleLength(from,to);
	    	
	        //print result
	        out.printf("%d %d %d\n", num1, num2, max);
	    }
	    //close scanner to prevent resource leak
	    in.close();
	}
}

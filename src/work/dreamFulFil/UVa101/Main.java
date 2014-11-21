package work.dreamFulFil.UVa101;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

class Main {
	//ArrayDeque as Stack
	private static List<Deque<Integer>> listOfBlocks;
	
	//ArrayList that keeps positions of each block
	private static List<Integer> listOfPositions;
	
	public static void moveOnto(int target, int destination){
		
		//check if two blocks are already in the same stack
		if(checkIfSameStack(target,destination))return;
		
		int temp;//temporary variable for moving blocks
		int posTarget;
		int posDestination;
		
		//find position of target block
		posTarget=listOfPositions.get(target);
		
		//remove blocks above target block to where it belongs
		while(listOfBlocks.get(posTarget).peek()!=target){//while there's still blocks on top of target block
			//pop one block from stack of target
			temp=listOfBlocks.get(posTarget).pop();
			//push temp block to where it belongs
			listOfBlocks.get(temp).push(temp);
			//update temp block's position
			listOfPositions.set(temp, temp);
		}
		
		//find position of destination block
		posDestination=listOfPositions.get(destination);
		
		//remove blocks from destination block in correct order
		while(listOfBlocks.get(posDestination).peek()!=destination){//while there's still blocks on top of destination block
			//pop one block from stack of destination
			temp=listOfBlocks.get(posDestination).pop();
			//push temp block to where it belongs
			listOfBlocks.get(temp).push(temp);
			//update temp block's position
			listOfPositions.set(temp, temp);
		}
		
		//pop target block from stack and push into destination stack
		temp=listOfBlocks.get(posTarget).pop();
		listOfBlocks.get(posDestination).push(temp);
		
		//set target block's new position
		listOfPositions.set(temp,posDestination);
	}
	
	public static void moveOver(int target,int destination){
		
		//check if two blocks are already in the same stack
		if(checkIfSameStack(target,destination))return;
		
		int temp;//temporary variable for moving blocks
		int posTarget;
		int posDestination;
		
		//find position of target block
		posTarget=listOfPositions.get(target);
				
		//remove blocks above targeting block to where it belongs
		while(listOfBlocks.get(posTarget).peek()!=target){//while there's still blocks on top of target block
			//pop one block from stack of target
			temp=listOfBlocks.get(posTarget).pop();
			//push temp block to where it belongs
			listOfBlocks.get(temp).push(temp);
			//update temp block's position
			listOfPositions.set(temp, temp);
		}
		
		//find position of destination block
		posDestination=listOfPositions.get(destination);
		
		//pop target block from stack and push into destination stack
		temp=listOfBlocks.get(posTarget).pop();
		listOfBlocks.get(posDestination).push(temp);
		
		//set target block's new position
		listOfPositions.set(temp,posDestination);
	}
	
	public static void pileOnto(int target,int destination){
		//check if two blocks are already in the same stack
		if(checkIfSameStack(target,destination))return;
				
		int temp;//temporary variable for moving blocks
		int posTarget;
		int posDestination;
		
		//find position of target block
		posTarget=listOfPositions.get(target);
		
		//find position of destination block
		posDestination=listOfPositions.get(destination);
				
		//remove blocks from destination block in correct order
		while(listOfBlocks.get(posDestination).peek()!=destination){//while there's still blocks on top of destination block
			//pop one block from stack of destination
			temp=listOfBlocks.get(posDestination).pop();
			//push temp block to where it belongs
			listOfBlocks.get(temp).push(temp);
			//update temp block's position
			listOfPositions.set(temp, temp);
		}
				
		Deque<Integer> dequeTemp=new ArrayDeque<Integer>();
		while(listOfBlocks.get(posTarget).peek()!=target){
			dequeTemp.push(listOfBlocks.get(posTarget).pop());
		}
		dequeTemp.push(listOfBlocks.get(posTarget).pop());
		
		while(dequeTemp.size()!=0){
			temp=dequeTemp.pop();
			listOfBlocks.get(posDestination).push(temp);
			listOfPositions.set(temp, posDestination);
		}
	}
	
	public static void pileOver(int target,int destination){
		//check if two blocks are already in the same stack
		if(checkIfSameStack(target,destination))return;
				
		int temp;//temporary variable for moving blocks
		int posTarget;
		int posDestination;
				
		//find position of target block
		posTarget=listOfPositions.get(target);
		
		//find position of destination block
		posDestination=listOfPositions.get(destination);
				
		Deque<Integer> dequeTemp=new ArrayDeque<Integer>();
		while(listOfBlocks.get(posTarget).peek()!=target){
			dequeTemp.push(listOfBlocks.get(posTarget).pop());
		}
		dequeTemp.push(listOfBlocks.get(posTarget).pop());
		
		while(dequeTemp.size()!=0){
			temp=dequeTemp.pop();
			listOfBlocks.get(posDestination).push(temp);
			listOfPositions.set(temp, posDestination);
		}
	}
	
	//Method to check if two blocks are already in the same stack
	private static boolean checkIfSameStack(int target, int destination){
		if(listOfPositions.get(target)==listOfPositions.get(destination))return true;
		else return false;
	}
	
	public static void main(String[] args){
		//Scanner for user input
		Scanner in=new Scanner(System.in);		
		//PrintWriter for displaying output
		PrintWriter out=new PrintWriter(System.out,true);	
		//String to save 1st action, should be move or pile
		String moveOrPile=null;		
		//String to save 2nd action, should be onto or over
		String ontoOrOver=null;
		//int to save number of blocks
		int numberOfBlocks=0;
		//int to save targeting block
		int target=0;		
		//int to save destination block
		int destination=0;
		
		//list of block stacks
		
		
		try{
			//read number of blocks
			numberOfBlocks=in.nextInt();
			
			//initialize the ArrayList of Deque for keeping the actual stack
			listOfBlocks=new ArrayList<Deque<Integer>>(numberOfBlocks);
			//initialize the ArrayList of Integer for keeping the position of each block
			listOfPositions=new ArrayList<Integer>();
			
			for(int i=0;i<numberOfBlocks;i++){
				//initialize ArrayDeque in each ArrayList index
				listOfBlocks.add(i,new ArrayDeque<Integer>());
				//push value into ArrayDeque,0 for index 0, 1 for index 1...etc.
				listOfBlocks.get(i).push(i);
				//initialize each block's initial position
				listOfPositions.add(i,i);
			}
			
			do{//loop that obtains input and performs corresponding action				
				//read 1st action
				moveOrPile=in.next();
				//check if quit is entered
				if(moveOrPile.equals("quit")){
					//print all values
					for(int i=0;i<numberOfBlocks;i++){
						//print each stack and a colon
						out.print(i+":");
						
						//d1 is original stack, d2 is temp stack to prevent reversing the order of the stack
						Deque<Integer> d1=((ArrayDeque<Integer>)(listOfBlocks.get(i))).clone();
						Deque<Integer> d2=new ArrayDeque<Integer>();
						
						//copy d1's value into d2
						while(d1.size()!=0){
							d2.push(d1.pop());
						}
						
						//if there are no values in the stack, print a new line
						if(d2.size()==0){
							out.println();
							continue;
						}
						else{//if there are values, append a space after the colon
							out.print(" ");
						}
						
						//print all values with a space, except for the last element
						while(d2.size()!=1){
							out.print(d2.pop());
							out.print(" ");
						}
						
						//print last element
						out.print(d2.pop());
						
						//print new line
						out.println();
					}
					break;
				}
				//read targeting block
				target=in.nextInt();
				//read 2nd action
				ontoOrOver=in.next();
				//read destination block
				destination=in.nextInt();
				
				if(moveOrPile.equals("move") && ontoOrOver.equals("onto")){
					moveOnto(target,destination);
				}
				else if(moveOrPile.equals("move") && ontoOrOver.equals("over")){
					moveOver(target,destination);
				}
				else if(moveOrPile.equals("pile") && ontoOrOver.equals("onto")){
					pileOnto(target,destination);
				}
				else if(moveOrPile.equals("pile") && ontoOrOver.equals("over")){
					pileOver(target,destination);
				}
				
				//if target block is as same as destination block, continue.
				if(target==destination)continue;
				
			}while(true);
		}
		finally{//close streams to prevent resource leak
			if(in!=null)in.close();
			if(out!=null)out.close();
		}
	}
}
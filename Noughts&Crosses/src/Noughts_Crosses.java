import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Noughts_Crosses {

	static ArrayList<Integer> playerSelection = new ArrayList <>();
	static ArrayList<Integer> computerSelection = new ArrayList <>();
	static boolean playAgain = true;
	static boolean alreadyWin = false;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char [][] gameBoard = { {' ', '|', ' ','|', ' '},
								{'-', '+', '-','+', '-'},
								{' ', '|', ' ','|', ' '},
								{'-', '+', '-','+', '-'},			
								{' ', '|', ' ','|', ' '},};
		
		print(gameBoard);	
				
		while(playAgain) {
				
			int playerSelect = getPlayerSelection();			
			
			while(checkPositionPlayer(playerSelect)) {
				System.out.println("position taken!, please select another place");
				Scanner scan = new Scanner(System.in);
				playerSelect = scan.nextInt();
			}
			
			
			place(gameBoard, playerSelect, "player");
			
			print(gameBoard);	
			
			String result = checkStatus();
			
			if(result.length()>0)
				end(result,gameBoard);
			
			if(!alreadyWin){
				
				int compSelect = getComputerSelection();
				
				if(!fullBoard()) {
					
					while(checkPositionComputer(compSelect)) {
						Random rand = new Random();
						compSelect = rand.nextInt(9)+1;		
					}
				
					place(gameBoard, compSelect, "computer");
				
					print(gameBoard);	
					
					result = checkStatus();
					if(result.length()>0)
						end(result,gameBoard);			
					}
			}
		}
		System.out.println("Bye Bye");
	}
	
	public static void print(char [][] gameBoard)
	{
		System.out.println();	
		for(int i =0; i<gameBoard.length; i++) {
			for(int j=0; j<gameBoard[0].length; j++) {
					System.out.print(gameBoard[i][j]);
			}
			System.out.println();	
		}
	}
	
	public static void place(char[][] gameBoard, int select, String user) {
		
		char c = ' ';
		
		if(user.equals("player")) {
			c = 'X';
			playerSelection.add(select);
		}
		else if(user.equals("computer")) {
			c = '0';
			computerSelection.add(select);
		}
			
			
		switch(select) {
		case 1: gameBoard[0][0] = c; break;
		case 2: gameBoard[0][2] = c; break;
		case 3: gameBoard[0][4] = c; break;
		case 4: gameBoard[2][0] = c; break;
		case 5: gameBoard[2][2] = c; break;
		case 6: gameBoard[2][4] = c; break;
		case 7: gameBoard[4][0] = c; break;
		case 8: gameBoard[4][2] = c; break;
		case 9: gameBoard[4][4] = c; break;
		default: break;
		}	
	}
	
	public static String checkStatus() {
		
		List<Integer> topRow = Arrays.asList(1,2,3); 
		List<Integer> midRow = Arrays.asList(4,5,6); 
		List<Integer> botRow = Arrays.asList(7,8,9);
		List<Integer> leftCol = Arrays.asList(1,4,7);
		List<Integer> midCol = Arrays.asList(2,5,8);
		List<Integer> rightCol = Arrays.asList(3,6,9);
		List<Integer> cross1 = Arrays.asList(1,5,9);
		List<Integer> cross2 = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<>();
		
	 	winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for (List l : winning) {
			if(playerSelection.containsAll(l)) {
				return "Well done! you won ! :)";
		  	}
			else if(computerSelection.containsAll(l)) {
				return "Computer wins, don't give up!";
			}
			else if(fullBoard()) {
				return "Dead heat!";
			}
		}
		
		return"";
	}

	public static boolean fullBoard() {	
		return(playerSelection.size() + computerSelection.size()==9);
	}
	
	public static void clean(char [][] gameBoard) {

		
		
	gameBoard[0][0] = ' '; 
	gameBoard[0][2] = ' '; 
	gameBoard[0][4] = ' '; 
	gameBoard[2][0] = ' '; 
	gameBoard[2][2] = ' '; 
	gameBoard[2][4] = ' '; 
	gameBoard[4][0] = ' '; 
	gameBoard[4][2] = ' '; 
	gameBoard[4][4] = ' '; 
 
	playerSelection.clear();
	computerSelection.clear();
	alreadyWin = false;
	}
	
	//function that verify that the position is free
	public static boolean checkPositionPlayer(int playerSelect) {
		return(playerSelection.contains(playerSelect) || computerSelection.contains(playerSelect));
			
	}

	public static boolean checkPositionComputer(int compSelect){
		return(playerSelection.contains(compSelect) || computerSelection.contains(compSelect));
	}
	
	public static void end(String result, char[][] gameBoard) {
		
		
		System.out.println(result);
		System.out.println("For playin again enter Y, to end please press E");	
		Scanner s = new Scanner(System.in);
		String ans = s.next();
		if(ans.equals("Y")) {
			playAgain=true;
			alreadyWin=true;
			clean(gameBoard);
			print(gameBoard);
			
		}
		else if(ans.equals("E")) {
			playAgain=false;
			alreadyWin=true;
		}
	}
	
	public static int getPlayerSelection(){
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your selection(1-9):" );	
		return scan.nextInt();	
	}
	
	public static int getComputerSelection(){	
		Random rand = new Random();
		return rand.nextInt(9)+1;
	}
	
	
}

import java.util.ArrayList;
import java.util.Scanner;

public class lightsout {

	static int[] inputArray;
	static String[] inputArrayString;
	static int[][] onPos;
	static int[][] switchBoard;
	static Switch[] pressedSwitches;
	static ArrayList<Switch> listOfInvertedSwitches = new ArrayList<Switch>();
	static int x = 0;
	static int y = 0;
	static int ON_SWITCH = 1;
	static int OFF_SWITCH = 0;
	static int PRESSED_SWITCH = 2;
	

	public static void main(String[] args) {

//		 System.out.println("Enter checker positions: ");
//		 Scanner in = new Scanner(System.in);
//		 String input = in.nextLine();
//		 in.close();
		//decodeInput(input);
		decodeInput(args);
		setUpEmptyBoard();
		setupOnSwitches();
		setupPressedSwitches();
		processFirstSetOfAdjacentSwitches();
		countOnSwitches();
		// playCheckers();
		// Loop to print board

	}

	public static void printBoard(int[][] printableBoard) {
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				System.out.print(" " + printableBoard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void printSwitches(Switch[] pressedSwitches) {
		for (int i = 0; i < pressedSwitches.length; i++) {
			if(pressedSwitches[i] != null) System.out.println(pressedSwitches[i]);
		}
	}

	public static void decodeInput(String[] inputString) {
		//inputArrayString = inputString.split(" ");
		inputArrayString = inputString;
		inputArray = new int[inputArrayString.length];

		for (int i = 0; i < inputArrayString.length; i++) {
			inputArray[i] = Integer.parseInt(inputArrayString[i]);
		}

		System.out.println("inputArray: ");
		for (int i = 0; i < inputArray.length; i++) {
			System.out.println(inputArray[i]);
		}
	}

	public static void setUpEmptyBoard() {
		switchBoard = new int[8][8];

		// Loop to populate board with 0s
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				switchBoard[i][j] = 0;
			}
		}
	}

	public static void setupOnSwitches() {
		int numberOfOnSwitchRows = inputArray[0]; // 2
		int onSwitchRow = 0;
		int onSwitchColumnsLength = 0;
		for (int i = 1; i <= numberOfOnSwitchRows; i++) {
			onSwitchRow = Integer.parseInt(inputArrayString[i].substring(0, 1)); // 4
			onSwitchColumnsLength = inputArrayString[i].length() - 1; // 2
			for (int j = 1; j <= onSwitchColumnsLength; j++) {
				switchBoard[onSwitchRow - 1]
								[Integer.parseInt(inputArrayString[i].substring(j, j + 1)) - 1] 
								= ON_SWITCH;
			}
		}

		System.out.println("readyToPlayBoard: ");
		printBoard(switchBoard);
	}
	
	public static void setupPressedSwitches() {
		int pressedSwitchRow = 0;
		int pressedSwitchColumn = 0;
		pressedSwitches = new Switch[5];
		int pressedSwitchCounter = 0;
		for (int i = inputArray[0] + 2; i < inputArrayString.length; i++) {
			pressedSwitchRow = Integer.parseInt(inputArrayString[i].substring(0, 1)) - 1; // 4
			pressedSwitchColumn = Integer.parseInt(inputArrayString[i].substring(1, 2)) - 1; // 3
			pressedSwitches[pressedSwitchCounter++] = new Switch(pressedSwitchRow, pressedSwitchColumn);
			// This is the one that was pressed,so special handling for this one
			toggleSwitch(new Switch(pressedSwitchRow,pressedSwitchColumn));
		}

		System.out.println("switchPressedBoard: ");
		printBoard(switchBoard);
		
		printSwitches(pressedSwitches);
	}
	
	public static void processFirstSetOfAdjacentSwitches() {
		for (int i = 0; i < pressedSwitches.length; i++) {
			if(pressedSwitches[i] != null) toggleAdjacentSwitches(pressedSwitches[i]);
		}
		System.out.println("processedBoard: ");
		printBoard(switchBoard);
	}
	
	public static void toggleAdjacentSwitches(Switch aSwitch) {
		int i = aSwitch.x;
		int j = aSwitch.y;
		toggleSwitch(new Switch(i - 1, j)); // Up
		toggleSwitch(new Switch(i, j - 1)); // Left
		toggleSwitch(new Switch(i + 1, j)); // Down
		toggleSwitch(new Switch(i, j + 1)); // Right
	}
	
	public static void toggleSwitch(Switch aSwitch) {
		int i = aSwitch.x;
		int j = aSwitch.y;

		try {
			if (!listOfInvertedSwitches.contains(aSwitch)) {
				if (switchBoard[i][j] == ON_SWITCH)
					switchBoard[i][j] = OFF_SWITCH;
				else
					switchBoard[i][j] = ON_SWITCH;
				
				listOfInvertedSwitches.add(aSwitch);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	public static void countOnSwitches() {
		int onSwitchCount = 0;
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				if(switchBoard[i][j] == ON_SWITCH) {
					onSwitchCount += 1;
				}
			}
		}
		System.out.println("onSwitchCount: " + onSwitchCount);
	}
	
}

class Switch {
	int x, y;
	Switch(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return("Switch: " + "x: " + x + ",y: " + y);
	}
}

//public static void toggleDiagonalSwitches() {
//	processedBoard = switchPressedBoard;
//	for (int i = 0; i <= 7; i++) {
//		for (int j = 0; j <= 7; j++) {
//			if(processedBoard[i][j] == PRESSED_SWITCH) {
//				toggleSwitch(i-1, j-1); //LeftUp
//				toggleSwitch(i+1, j-1); //LeftDown
//				toggleSwitch(i-1, j+1); //RightUp
//				toggleSwitch(i+1, j+1); //RightDown
//			}
//		}
//	}
//	System.out.println("Diagonal processedBoard: ");
//	printBoard(processedBoard);
//}
//
//public static void toggleVerticalSwitches() {
//	for (int i = 0; i <= 7; i++) {
//		for (int j = 0; j <= 7; j++) {
//			if(processedBoard[i][j] == PRESSED_SWITCH) {
//				toggleSwitch(i-1, j); //Up1
//				toggleSwitch(i-2, j); //Up2
//				toggleSwitch(i+1, j); //Down1
//				toggleSwitch(i+2, j); //Down2
//			}
//		}
//	}
//	System.out.println("Vertical processedBoard: ");
//	printBoard(processedBoard);
//}
//
//public static void toggleHorizontalSwitches() {
//	for (int i = 0; i <= 7; i++) {
//		for (int j = 0; j <= 7; j++) {
//			if(processedBoard[i][j] == PRESSED_SWITCH) {
//				toggleSwitch(i, j-1); //Left1
//				toggleSwitch(i, j-2); //Left2
//				toggleSwitch(i, j+1); //Right1
//				toggleSwitch(i, j+2); //Right2
//			}
//		}
//	}
//	System.out.println("Horizontal processedBoard: ");
//	printBoard(processedBoard);
//}

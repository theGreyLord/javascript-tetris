import java.util.ArrayList;
import java.util.Scanner;

public class LightsOut {

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

	public static void main(String[] args) {

		 System.out.println("Enter Switch positions: ");
		 Scanner in = new Scanner(System.in);
		 String input = in.nextLine().trim();
		 in.close();
		decodeInput(input);
//		decodeInput(args);
		setUpEmptyBoard();
		setupOnSwitches();
		processPressedSwitches();
	}

	public static void printBoard(int[][] printableBoard) {
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j <= 7; j++) {
				System.out.print(" " + printableBoard[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static void decodeInput(String inputString) {
		inputArrayString = inputString.split(" ");
//		inputArrayString = inputString;
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
	
	public static void processPressedSwitches() {
		int pressedSwitchRow = 0;
		int pressedSwitchColumn = 0;
		for (int i = inputArray[0] + 2; i < inputArrayString.length; i++) {
			pressedSwitchRow = Integer.parseInt(inputArrayString[i].substring(0, 1)) - 1; // 4
			pressedSwitchColumn = Integer.parseInt(inputArrayString[i].substring(1, 2)) - 1; // 3
			Switch pressedSwitch = new Switch(pressedSwitchRow,pressedSwitchColumn);
			toggleSwitch(pressedSwitch);
			listOfInvertedSwitches.add(pressedSwitch);
			processAdjacentSwitches(pressedSwitch);
			listOfInvertedSwitches.clear();
		}
	}
	
	public static void processAdjacentSwitches(Switch aPressedSwitch) {
		ArrayList<Switch> listOfAdjacentSwitches = 
				toggleAdjacentSwitches(aPressedSwitch);
		System.out.println("firstProcessedBoard: ");
		printBoard(switchBoard);
		
		processSecondSetOfAdjacentSwitches(listOfAdjacentSwitches);
		System.out.println("secondProcessedBoard: ");
		printBoard(switchBoard);

		countOnSwitches();
	}
	
	public static ArrayList<Switch> toggleAdjacentSwitches(Switch aSwitch) {
		int i = aSwitch.x;
		int j = aSwitch.y;
		Switch leftSwitch = new Switch(i - 1, j);
		Switch bottomSwitch = new Switch(i, j-1);
		Switch rightSwitch = new Switch(i + 1, j);
		Switch topSwitch = new Switch(i, j + 1);
		
		toggleSwitch(leftSwitch);
		toggleSwitch(bottomSwitch);
		toggleSwitch(rightSwitch);
		toggleSwitch(topSwitch);
		
		ArrayList<Switch> listOfAdjacentSwitches = new ArrayList<Switch>();
		listOfAdjacentSwitches.add(leftSwitch);
		listOfAdjacentSwitches.add(bottomSwitch);
		listOfAdjacentSwitches.add(rightSwitch);
		listOfAdjacentSwitches.add(topSwitch);
		return listOfAdjacentSwitches;
	}
	
	public static void processSecondSetOfAdjacentSwitches(ArrayList<Switch> listOfSwitches) {
		System.out.println("processSecondSetOfAdjacentSwitches: " + listOfSwitches.size());

		for(Switch aSwitch:listOfSwitches) {
			int i = aSwitch.x;
			int j = aSwitch.y;
			toggleSwitch(new Switch(i - 1, j)); // Up
			toggleSwitch(new Switch(i, j - 1)); // Left
			toggleSwitch(new Switch(i + 1, j)); // Down
			toggleSwitch(new Switch(i, j + 1)); // Right
		}
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
			;
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
	
	@Override public boolean equals(Object inObject) {
		Switch inSwitch = (Switch) inObject;
		if( this.x==inSwitch.x && this.y == inSwitch.y) return true;
		else return false;
	}
}

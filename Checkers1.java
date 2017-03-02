import java.util.*;

public class Checkers1 {
	static int[] checkerPositions;
	static int[][] readyToPlayBoard;
	static int x = 0;
	static int y = 0;
	
	public static void main(String[] args) {

		System.out.println("Enter checker positions: ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		in.close();
		checkerPositions = decodeInput(input);
		readyToPlayBoard = setupBoard();
		playCheckers();
	}

	public static int[] decodeInput(String input) {

		String[] arrIn = input.split(",");

		checkerPositions = new int[arrIn.length];
		int num = 0;
		for (int i = 0; i < arrIn.length; i++) {
			num = Integer.parseInt(arrIn[i]);
			checkerPositions[i] = num - 1;
		}

		return checkerPositions;

	}

	public static int[][] setupBoard() {
		readyToPlayBoard = new int[8][8];

		// Loop to populate board with 0s
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				readyToPlayBoard[i][j] = 0;
			}
		}

		// Loop to populate opponent positions
		for (int h = 4; h < checkerPositions.length; h += 2) {
			readyToPlayBoard[checkerPositions[h - 1]][checkerPositions[h]] = 1;
			readyToPlayBoard[checkerPositions[0]][checkerPositions[1]] = 2;

		}

		return readyToPlayBoard;
	}

	public static boolean canCheckerMoveRight() {
		boolean returnValue = false;
		try {
			if ((readyToPlayBoard[checkerPositions[0] + 1][checkerPositions[1] + 1] == 1)
					&& (readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] + 2] == 0))
				returnValue = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("I am a doofus because my array counter went out of bounds with this number: " + e.getMessage());
			System.out.println("checkerPositions[0]= " + checkerPositions[0]);
			System.out.println("checkerPositions[1]= " + checkerPositions[1]);
		}
		return returnValue;
	}

	public static boolean canCheckerMoveLeft() {
		boolean returnValue = false;
		try {
			if ((readyToPlayBoard[checkerPositions[0] + 1][checkerPositions[1] - 1] == 1)
					&& (readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] - 2] == 0)) 
				returnValue = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("I am a doofus because my array counter went out of bounds with this number: " + e.getMessage());
			System.out.println("checkerPositions[0]= " + checkerPositions[0]);
			System.out.println("checkerPositions[1]= " + checkerPositions[1]);
		}
		
		return returnValue;
	}

	public static void playCheckers() {
		// Conditions
		int count = 0;
		boolean foundKing = false;
		while (true) {
			if ((checkerPositions[0] == 7)) {
				foundKing = true;
				break;
			} else if (canCheckerMoveRight()) {
				count += 1;
				readyToPlayBoard[checkerPositions[0]][checkerPositions[1]] = 0;
				readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] + 2] = 2;
				checkerPositions[0] += 2;
				checkerPositions[1] += 2;
			} else if (canCheckerMoveLeft()) {
				count += 1;
				readyToPlayBoard[checkerPositions[0]][checkerPositions[1]] = 0;
				readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] - 2] = 2;
				checkerPositions[0] += 2;
				checkerPositions[1] -= 2;
			} else if ((checkerPositions[0] <= 0 || checkerPositions[1] <= 0)
					|| (checkerPositions[0] <= 7 || checkerPositions[1] <= 7)) {
				break;
			}
		}

		if (foundKing)
			System.out.println(count + ", KING");
		else
			System.out.println(count);

		// Loop to print board
		for (int a = 0; a <= 7; a++) {
			for (int b = 0; b <= 7; b++) {
				System.out.print(" " + readyToPlayBoard[a][b] + " ");
			}
			System.out.println();
		}
	}
}

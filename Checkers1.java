import java.util.*;

public class Checkers1 {

	public static void main(String[] args) {

		System.out.println("Enter checker positions: ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		in.close();
		int[] checkerPositions = decodeInput(input);
		int[][] readyToPlayBoard = setupBoard(checkerPositions);
		playCheckers(readyToPlayBoard, checkerPositions);
	}

	public static int[] decodeInput(String input) {

		String[] arrIn = input.split(",");

		int[] arrInt = new int[arrIn.length];
		int num = 0;
		for (int i = 0; i < arrIn.length; i++) {
			num = Integer.parseInt(arrIn[i]);
			arrInt[i] = num - 1;
		}

		return arrInt;

	}

	public static int[][] setupBoard(int[] position) {
		int[][] board = new int[8][8];

		// Loop to populate board
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				board[i][j] = 0;
			}
		}

		// Loop to populate opponent positions
		for (int h = 4; h < position.length; h += 2) {
			board[position[h - 1]][position[h]] = 1;
			board[position[0]][position[1]] = 2;

		}

		return board;
	}

	public static void playCheckers(int[][] readyToPlayBoard, int[] checkerPositions) {
		// Conditions
		int count = 0;
		boolean foundKing = false;
		while (true) {
			if ((checkerPositions[0] == 7)) {
				foundKing = true;
				break;
			} else if ((readyToPlayBoard[checkerPositions[0] + 1][checkerPositions[1] + 1] == 1)
					&& (readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] + 2] == 0)) {
				count += 1;
				readyToPlayBoard[checkerPositions[0]][checkerPositions[1]] = 0;
				readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] + 2] = 2;
				checkerPositions[0] += 2;
				checkerPositions[1] += 2;
			} else if ((checkerPositions[0] <= 1 || checkerPositions[1] > 0) && (readyToPlayBoard[checkerPositions[0] + 1][checkerPositions[1] - 1] == 1)
					&& (readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] - 2] == 0)) {
				count += 1;
				readyToPlayBoard[checkerPositions[0]][checkerPositions[1]] = 0;
				readyToPlayBoard[checkerPositions[0] + 2][checkerPositions[1] - 2] = 2;
				checkerPositions[0] += 2;
				checkerPositions[1] -= 2;
			} else if ((checkerPositions[0] < 0 || checkerPositions[1] < 0) || (checkerPositions[0] > 7 || checkerPositions[1] > 7)) {
				break;
			}
		}
		
		if(foundKing) System.out.println(count + ", KING");
		else System.out.println(count);

		// Loop to print board
		for (int a = 0; a <= 7; a++) {
			for (int b = 0; b <= 7; b++) {
				System.out.print(" " + readyToPlayBoard[a][b] + " ");
			}
			System.out.println();
		}
	}
}

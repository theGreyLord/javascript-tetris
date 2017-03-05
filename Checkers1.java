import java.util.*;

public class lightsout {

	static String[] offPosArr;
	static int[][] onPos;
 	static int[][] readyToPlayBoard;
	static int x = 0;
	static int y = 0;
	
	public static void main(String[] args) {

		System.out.println("Enter checker positions: ");
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		in.close();
		onPos = decodeInput(input);
		readyToPlayBoard = setupBoard();
		//playCheckers();
		// Loop to print board
		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				System.out.print(" " + readyToPlayBoard[i][j] + " ");
			}
			System.out.println();
		}
	}

			public static int[][] decodeInput(String input) {
				offPosArr = input.split(" ");
				int firstNum = Integer.parseInt(offPosArr[0]);
				for(int firstSet = 1; firstSet <= firstNum; firstSet++) {
					int len = offPosArr[firstSet].length();
					onPos = new int[offPosArr.length][len+1];
					for(int fillArr = 0;fillArr<len;fillArr++) {
						onPos[firstSet][fillArr] = Integer.parseInt(offPosArr[firstSet].substring(fillArr,fillArr+1));
						System.out.print(" " + onPos[firstSet][fillArr] + " ");
						//System.out.println(offPosArr[firstSet].substring(fillArr,fillArr+1));
					}
					System.out.println();
					//System.out.println(offPosArr[firstSet].length());
				}
				//System.out.println("on pos "+onPos[1][0]);
				int secondNum = Integer.parseInt(offPosArr[0])+1;
				//System.out.println("secondNum"+secondNum);
				for(int secondSet = secondNum +1; secondSet < offPosArr.length; secondSet++) {
					int len2 = offPosArr[secondSet].length();
					onPos = new int[offPosArr.length][len2+1];
					for(int fillArr = 0;fillArr<len2;fillArr++) {
						onPos[secondSet][fillArr] = Integer.parseInt(offPosArr[secondSet].substring(fillArr,fillArr+1));
						System.out.print(" " + onPos[secondSet][fillArr] + " ");
						//System.out.println(offPosArr[secondSet].substring(fillArr,fillArr+1));
					}
					System.out.println();
					//System.out.println(offPosArr[secondSet].length());
				}
				
				return onPos;
			}
			

			
			public static int[][] setupBoard() {
				readyToPlayBoard = new int[8][8];

				// Loop to populate board with 0s
				for (int i = 0; i <= 7; i++) {
					for (int j = 0; j <= 7; j++) {
						readyToPlayBoard[i][j] = 0;
					}
				}

				//Loop to populate on positions
				int firstNum = Integer.parseInt(offPosArr[0]);
				for(int firstSet = 1; firstSet <= firstNum; firstSet++) {
					int len = offPosArr[firstSet].length();
					for(int fillArr = 0;fillArr<len;fillArr++) {
						readyToPlayBoard[onPos[firstSet][fillArr]][0] = 1;
						System.out.println("1,0: "+onPos[1][0]);
					}
					//System.out.println(offPosArr[firstSet].length());
				}
				int secondNum = Integer.parseInt(offPosArr[0])+1;
				for(int secondSet = secondNum +1; secondSet < offPosArr.length; secondSet++) {
					int len2 = offPosArr[secondSet].length();
					onPos = new int[offPosArr.length][len2+1];
					for(int fillArr = 0;fillArr<len2;fillArr++) {
						onPos[secondSet][fillArr] = Integer.parseInt(offPosArr[secondSet].substring(fillArr,fillArr+1));
						//System.out.println(onPos[secondSet][fillArr]);
						//System.out.println(offPosArr[secondSet].substring(fillArr,fillArr+1));
					}
					//System.out.println(offPosArr[secondSet].length());
				}
				return readyToPlayBoard;
			}
}
			/*
			public static boolean canCheckerMoveRight() {
				boolean returnValue = false;
				try {
					if ((readyToPlayBoard[onPos[0] + 1][onPos[1] + 1] == 1)
							&& (readyToPlayBoard[onPos[0] + 2][onPos[1] + 2] == 0))
						returnValue = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("I am a doofus because my array counter went out of bounds with this number: " + e.getMessage());
					System.out.println("checkerPositions[0]= " + onPos[0]);
					System.out.println("checkerPositions[1]= " + onPos[1]);
				}
				return returnValue;
			}

			public static boolean canCheckerMoveLeft() {
				boolean returnValue = false;
				try {
					if ((readyToPlayBoard[onPos[0] + 1][onPos[1] - 1] == 1)
							&& (readyToPlayBoard[onPos[0] + 2][onPos[1] - 2] == 0)) 
						returnValue = true;
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("I am a doofus because my array counter went out of bounds with this number: " + e.getMessage());
					System.out.println("checkerPositions[0]= " + onPos[0]);
					System.out.println("checkerPositions[1]= " + onPos[1]);
				}
				
				return returnValue;
			}

			public static void playCheckers() {
				// Conditions
				int count = 0;
				boolean foundKing = false;
				while (true) {
					if ((onPos[0] == 7)) {
						foundKing = true;
						break;
					} else if (canCheckerMoveRight()) {
						count += 1;
						readyToPlayBoard[onPos[0]][onPos[1]] = 0;
						readyToPlayBoard[onPos[0] + 2][onPos[1] + 2] = 2;
						onPos[0] += 2;
						onPos[1] += 2;
					} else if (canCheckerMoveLeft()) {
						count += 1;
						readyToPlayBoard[onPos[0]][onPos[1]] = 0;
						readyToPlayBoard[onPos[0] + 2][onPos[1] - 2] = 2;
						onPos[0] += 2;
						onPos[1] -= 2;
					} else if ((onPos[0] <= 0 || onPos[1] <= 0)
							|| (onPos[0] <= 7 || onPos[1] <= 7)) {
*/

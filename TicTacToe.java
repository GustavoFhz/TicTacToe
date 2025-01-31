import java.util.Scanner;

public class TicTacToe {

  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {

    System.out.println("\nLet's play tic tac toe");

    char[][] board = {
        { '_', '_', '_' },
        { '_', '_', '_' },
        { '_', '_', '_' }
    };

    printBoard(board);

    for (int i = 0; i < 9; i++) {
      System.out.println();
      char currentPlayer = (i % 2 == 0) ? 'X' : 'O';
      System.out.println("Turn " + currentPlayer);
      
      int[] spot = askUser(board);
      board[spot[0]][spot[1]] = currentPlayer;
      
      printBoard(board);

      int result = checkWin(board);
      if (result == 3) {
        System.out.println("X wins!");
        return;
      } else if (result == -3) {
        System.out.println("O wins!");
        return;
      }
    }

    System.out.println("It's a tie!");
  }

  public static void printBoard(char[][] board) {
    System.out.print("\n");
    for (char[] row : board) {
      System.out.print("\t");
      for (char cell : row) {
        System.out.print(cell + " ");
      }
      System.out.print("\n\n");
    }
  }

  public static int[] askUser(char[][] board) {
    int row, col;
    while (true) {
      System.out.print("Pick a row and column number: ");
      row = scan.nextInt();
      col = scan.nextInt();
      
      if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '_') {
        break;
      }
      System.out.println("Invalid move! Try again.");
    }
    return new int[] { row, col };
  }

  public static int checkWin(char[][] board) {
    int rows = checkRows(board);
    if (Math.abs(rows) == 3) return rows;

    int cols = checkColumns(board);
    if (Math.abs(cols) == 3) return cols;

    int leftDiag = checkLeft(board);
    if (Math.abs(leftDiag) == 3) return leftDiag;

    int rightDiag = checkRight(board);
    if (Math.abs(rightDiag) == 3) return rightDiag;

    return 0;
  }

  public static int checkRows(char[][] board) {
    for (char[] row : board) {
      int count = 0;
      for (char cell : row) {
        if (cell == 'X') count++;
        else if (cell == 'O') count--;
      }
      if (Math.abs(count) == 3) return count;
    }
    return 0;
  }

  public static int checkColumns(char[][] board) {
    for (int col = 0; col < 3; col++) {
      int count = 0;
      for (int row = 0; row < 3; row++) {
        if (board[row][col] == 'X') count++;
        else if (board[row][col] == 'O') count--;
      }
      if (Math.abs(count) == 3) return count;
    }
    return 0;
  }

  public static int checkLeft(char[][] board) {
    int count = 0;
    for (int i = 0; i < 3; i++) {
      if (board[i][i] == 'X') count++;
      else if (board[i][i] == 'O') count--;
    }
    return count;
  }

  public static int checkRight(char[][] board) {
    int count = 0;
    for (int i = 0; i < 3; i++) {
      if (board[i][2 - i] == 'X') count++;
      else if (board[i][2 - i] == 'O') count--;
    }
    return count;
  }
}

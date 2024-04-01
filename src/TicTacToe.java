import java.util.Scanner;
import java.util.Random;
public class TicTacToe {
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];
    private static String currentPlayer = "X"; // X always moves first
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean playAgain;
        do {
            board = new String[ROW][COL];
            currentPlayer = "X";
            clearBoard();
            playGame();
            System.out.println();
            playAgain = SafeInput.getYNConfirm(in, "Do you want to play another game? (Y/N)");
        } while (playAgain);

        in.close();
    }
    private static void playGame() {
        // TicTacToe loop
        while (true) {
            display();
            int row, col;
            do {
                row = SafeInput.getRangedInt(in, "Enter row (1-3)", 1, 3) - 1;
                col = SafeInput.getRangedInt(in, "Enter column (1-3)", 1, 3) - 1;
            } while (!isValidMove(row, col));

            board[row][col] = currentPlayer;

            if (isWin(currentPlayer)) {
                display();
                System.out.println();
                System.out.println("Player " + currentPlayer + " wins!");
                break;
            } else if (isTie()) {
                display();
                System.out.println();
                System.out.println("It's a tie!");
                break;
            }
            currentPlayer = (currentPlayer.equals("X")) ? "O" : "X";
        }
    }
    // Helper methods
    private static void clearBoard() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                board[i][j] = " ";
            }
        }
    }
    private static void display() {
        System.out.println("  1 2 3");
        for (int i = 0; i < ROW; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++) {
                System.out.print(board[i][j]);
                if (j < COL - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("  ------");
            }
        }
    }
    private static boolean isValidMove(int row, int col) {
        if (row < 0 || row >= ROW || col < 0 || col >= COL) {
            System.out.println("Invalid move! Please choose a row and column between 1 and 3.");
            return false;
        }
        if (!board[row][col].equals(" ")) {
            System.out.println("Invalid move! That cell is already occupied, please choose a different move.");
            return false;
        }
        return true;
    }
    private static boolean isWin(String player) {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }
    private static boolean isRowWin(String player) {
        for (int i = 0; i < ROW; i++) {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player) {
        for (int i = 0; i < COL; i++) {
            if (board[0][i].equals(player) && board[1][i].equals(player) && board[2][i].equals(player)) {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player) {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    private static boolean isTie() {
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (board[i][j].equals(" ")) {
                    return false; // If there's an empty space, game is not tied
                }
            }
        }
        return true; // All spaces filled, it's a tie
    }
    // SafeInput methods
    private static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        return SafeInput.getRangedInt(pipe, prompt, low, high);
    }
    private static boolean getYNConfirm(Scanner pipe, String prompt) {
        return SafeInput.getYNConfirm(pipe, prompt);
    }
}

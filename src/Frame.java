
import java.util.Scanner;

public class Frame {

    private static final int height = 8; //constants for the dimensions of the array
    private static final int width = 8;

    public static void main(String[] args) {
        Scanner inp = new Scanner (System.in);
        System.out.print ("Fox, enter player name: ");
        String playerFox = inp.nextLine ();
        System.out.print ("Hounds, enter player name: ");
        String playerHound = inp.nextLine (); //The names would be used at the end to say the name of the winner

        char[][] table = new char[height][width];
        table = fillBoard (table); //Fills the matrix/board with empty spaces and dots (instead of black and white squares)
        int xFoxCurr = 7, yFoxCurr = 0; //Current position of Fox

        while ( true ) {
            displayBoard (table);
            int xf, yf; //Coordinates to move the fox
            do {
                System.out.print ("Fox: ");
                xf = inp.nextInt ();
                yf = inp.nextInt ();
            } while ( validFoxMove (table, xf, yf) == false ); //loop until a valid move is entered for the fox
            table = makeMove (table, xf, yf, xFoxCurr, yFoxCurr); //This method should delete the fox from its old position and place it in the new one
            xFoxCurr = xf;
            yFoxCurr = yf;
            displayBoard (table); //display the table after the fox move  

            if ( isFoxWinner (table) == true ) { //Check if the fox has won. Just check if the position of the fox is on row 0
                System.out.println (playerFox + " wins!");
                break;
            }
//			else {
//				System.out.println(playerHound + " wins!");
//			}

//			This block is repeated twice - lines 32 - 36 above
//			if (isEnd(table, xFoxCurr, yFoxCurr) == true) { //Check if the game has finished
//				displayBoard(table);
//				System.out.println(playerHound + " is winner!");
//				break;
//			}
            int xhCurr, yhCurr;
            do {
                System.out.print ("Choose hound: ");
                xhCurr = inp.nextInt ();
                yhCurr = inp.nextInt ();
            } while ( validHound (table, xhCurr, yhCurr) == false ); //Check if there is a hound at this position

            int xh, yh;
            do {
                System.out.print ("Choose location: ");
                xh = inp.nextInt ();
                yh = inp.nextInt ();
            } while ( validHoundMove (table, xh, yh) == false ); //Check if it possible to move the hound to this position
            table = makeMove (table, xh, yh, xhCurr, yhCurr); //Make the move

            if ( isEnd (table, xFoxCurr, yFoxCurr) == true ) { //Check if the game has finished
                displayBoard (table);
                System.out.println (playerHound + " is winner!");
                break;
            }
        }

    }

    private static boolean isFoxWinner(char[][] table) { // Check only for x = 0; 
        if ( table[0][1] == 'F' ) {
            return true;
        }
        else if ( table[0][3] == 'F' ) {
            return true;
        }
        else if ( table[0][5] == 'F' ) {
            return true;
        }
        else if ( table[0][7] == 'F' ) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean validHoundMove(char[][] table, int xh, int yh) { // Gergana
        boolean isValid = false;
        int currentRow = 0;
        int firstHoundCurrCol = 1;
        int secondHoundCurrCol = 3;
        int thirdHoundCurrCol = 5;
        int fourthHoundCurrCol = 7;

        if ( (xh >= 0 && xh < 8) && (yh >= 0 && yh < 8) ) {
            if ( xh == (currentRow + 1) && (yh == firstHoundCurrCol + 1) && table[xh][yh] == '-' ) {
                firstHoundCurrCol ++;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == firstHoundCurrCol - 1) && table[xh][yh] == '-' ) {
                firstHoundCurrCol --;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == secondHoundCurrCol + 1) && table[xh][yh] == '-' ) {
                secondHoundCurrCol ++;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == secondHoundCurrCol - 1) && table[xh][yh] == '-' ) {
                secondHoundCurrCol --;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == thirdHoundCurrCol + 1) && table[xh][yh] == '-' ) {
                thirdHoundCurrCol ++;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == thirdHoundCurrCol - 1) && table[xh][yh] == '-' ) {
                thirdHoundCurrCol --;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == fourthHoundCurrCol + 1) && table[xh][yh] == '-' ) {
                fourthHoundCurrCol ++;
                currentRow ++;
                isValid = true;
            }
            else if ( xh == (currentRow + 1) && (yh == fourthHoundCurrCol - 1) && table[xh][yh] == '-' ) {
                fourthHoundCurrCol --;
                currentRow ++;
                isValid = true;
            }
        }
        else {
            isValid = false;
        }

        return isValid;

    }

    private static boolean validHound(char[][] table, int xhCurr, int yhCurr) {
        if ( table[xhCurr][yhCurr] == 'H' ) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean isEnd(char[][] table, int xFoxCurr, int yFoxCurr) {
        if ( xFoxCurr == 0 ) { //Check if the fox is on the left side of the board 
            if ( yFoxCurr == 7 ) {
                if ( table[xFoxCurr + 1][yFoxCurr - 1] == '-' ) {
                    return false;
                }
                return true;
            }
            else {
                if ( table[xFoxCurr + 1][yFoxCurr + 1] == '-' || table[xFoxCurr + 1][yFoxCurr - 1] == '-' ) {
                    return false;
                }
            }
            return true;
        }
        else if ( xFoxCurr == 7 ) { //Check if the fox is on the right side of the board
            if ( yFoxCurr == 0 ) {
                if ( table[xFoxCurr - 1][yFoxCurr + 1] == '-' ) {
                    return false;
                }
                return true;
            }
            else {
                if ( table[xFoxCurr - 1][yFoxCurr + 1] == '-' || table[xFoxCurr - 1][yFoxCurr - 1] == '-' ) {
                    return false;
                }
            }
            return true;
        }
        else if ( yFoxCurr == 0 && xFoxCurr != 7 ) { //Check if the fox is on the upper side of the board
            if ( table[xFoxCurr + 1][yFoxCurr + 1] == '-' || table[xFoxCurr - 1][yFoxCurr + 1] == '-' ) {
                return false;
            }
            return true;
        }
        else if ( yFoxCurr == 7 && xFoxCurr != 0 ) { // Check if the fox is on the lower side of the board
            if ( table[xFoxCurr + 1][yFoxCurr - 1] == '-' || table[xFoxCurr - 1][yFoxCurr - 1] == '-' ) {
                return false;
            }
            return true;
        }
        else { // Checks if it is the end of the game when fox is in the center of the board - not border case
            if ( table[xFoxCurr + 1][yFoxCurr + 1] == '-' || table[xFoxCurr + 1][yFoxCurr - 1] == '-' || table[xFoxCurr - 1][yFoxCurr + 1] == '-' || table[xFoxCurr - 1][yFoxCurr - 1] == '-' ) {
                return false;
            }
            else {
                return true;
            }
        }
    }

    private static char[][] makeMove(char[][] table, int x, int y, int xCurr, int yCurr) { // Milena
        if ( table[xCurr][yCurr] == 'F' ) {
            table[x][y] = 'F';
        }
        else {
            table[x][y] = 'H';
        }
        table[xCurr][yCurr] = '-';
        return table;
    }

    private static boolean validFoxMove(char[][] table, int xf, int yf) { // Gergana
        boolean isValid = false;
        int currentRow = 7;
        int currentCol = 0;
        if ( (yf < 8 && yf >= 0) && (xf >= 0 && xf < 8) ) {
            if ( (xf == currentRow + 1) && (yf == currentCol + 1) && table[xf][yf] == '-' ) {

                currentRow ++;
                currentCol ++;
                isValid = true;
            }
            else if ( (xf == currentRow + 1) && (yf == currentCol - 1) && table[xf][yf] == '-' ) {
                currentRow ++;
                currentCol --;
                isValid = true;

            }
            else if ( (xf == currentRow - 1) && (yf == currentCol + 1) && (table[xf][yf] == '-') ) {
                currentRow --;
                currentCol ++;
                isValid = true;
            }
            else if ( (xf == currentRow - 1) && (yf == currentCol - 1) && (table[xf][yf] == '-') ) {
                currentRow --;
                currentCol --;
                isValid = true;
            }
        }
        else {
            isValid = false;
        }
        return isValid;

    }

    private static void displayBoard(char[][] table) {
        //System.out.println("  0 1 2 3 4 5 6 7");
        for ( int i = 0; i < width; i ++ ) {
            if ( i == 0 ) {
                System.out.print (" ");
                System.out.print (" ");
            }
            System.out.print (i + " ");
        }
        System.out.println ();

        for ( int i = 0; i < height; i ++ ) {
            for ( int j = 0; j < width; j ++ ) {
                if ( j == 0 ) {
                    System.out.print (i + "|");
                }
                System.out.print (table[i][j] + "|");
            }
            System.out.println ();
        }
    }

    private static char[][] fillBoard(char[][] table) {
        for ( int i = 0; i < height; i ++ ) {
            for ( int j = 0; j < width; j ++ ) {
                if ( i % 2 == 0 && j % 2 == 0 ) {
                    table[i][j] = ' ';
                }
                else if ( i % 2 != 0 && j % 2 != 0 ) {
                    table[i][j] = ' ';
                }
                else {
                    table[i][j] = '-';
                }
            }
        }

        //Place hounds on board
        for ( int i = 1; i < width; i += 2 ) {
            table[0][i] = 'H';
        }

        //place fox
        table[7][0] = 'F';

        return table;
    }

}
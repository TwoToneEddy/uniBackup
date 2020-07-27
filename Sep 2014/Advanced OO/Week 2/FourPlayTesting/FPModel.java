
package fourplay;
import java.util.Observable;

/**
 *
 * @author Lee Hudson 09092543
 */
public class FPModel extends Observable {
    
    private /*@ spec_public @*/ final int noOfColomns = 7;
    private /*@ spec_public @*/ final int noOfRows = 6;
    private /*@ spec_public @*/ int[][] boardStatus;
    private /*@ spec_public @*/ int player1,player2;
    private /*@ spec_public @*/ boolean boardEmpty = true;
    
    
    public FPModel(){
        player1 = 0;
        player2 = 0;
	boardStatus = new int[noOfRows][noOfColomns];
        clearBoard();
    }
    
    /**
     * @public invariant boardStatus.length == noOfRows;
     * @public invariant (\forall int i; 0 <= i && i < noOfRows;
     *					  boardStatus[i].length == noOfColomns;)
     * @ensures (\forall int j,k; 0 <= j && j < noOfRows && 
     *							  0 <= k && k < noOfCololmns; 
     *							  boardStatus[j][k] == 0;
     * @ensures boardEmpty == true;
     */
    public void clearBoard(){
        
        for(int i = 0; i < noOfRows; i++){
            for(int j = 0; j < noOfColomns; j++){
                boardStatus[i][j] = 0;
            }
        }
        boardEmpty = true;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @ ensures \result == noOfColomns
     */
    public int getNoOfColomns() {
        return noOfColomns;
    }

    /**
     * @ ensures \result == noOfRows
     */
    public int getNoOfRows() {
        return noOfRows;
    }

    /**
     * @ ensures \result == boardStatus
     */
    public int[][] getChipStatus() {
        return boardStatus;
    }
    
    /**
     * @ requires player == 1 || player == 2;
     * @ ensures player == 1 ==> (\result == player1);
     * @ ensures player == 2 ==> (\result == player2);
     */
    public int getScore(int player){
        switch (player){
            case 1: return player1;
            case 2: return player2;
            default: return 0;
        }
    }
	
    /**
     * @ requires player == 1 || player == 2;
     * @ ensures player == 1 ==> (player1 == score);
     * @ ensures player == 2 ==> (player2 == score);
     */
    public void setScore(int player, int score){
        if(player == 1)
            player1=score;
        if(player == 2)
            player2=score;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @ requires row < noOfRows && row >= 0;
     * @ requires colomn < noOfColomns && colomn >=0;
     * @ ensures boardStatus[row][colomn] = value;
     * @ ensures boardEmpty == true ==> (boardEmpty == false);
     */
    public void setPiece(int row, int colomn, int value){
        if(boardEmpty==true)
            boardEmpty=false;
        boardStatus[row][colomn]=value;
        setChanged();
        notifyObservers();
    }
    
    /**
     * @ ensures \result == boardEmpty;;
     */
    public boolean boardIsEmpty(){
        return boardEmpty;
    }
    
    /**
     * Checks if a given player has a winning line
     */
    public boolean winningLine(int player){
        if(verticalWin(player) ||
           horizontalWin(player) ||
           upwardDiagonalWin(player) ||
           downwardDiagonalWin(player))
            return true;
        else
            return false;
    }
    
   
    private boolean verticalWin(int player){
        int counter = 0;
        for(int i = 0; i < noOfColomns; i++){
            counter=0;
            for(int j = 0; j < noOfRows; j++ ){
                if(boardStatus[j][i]==player){
                    counter++;
                    if(counter==4){
                        return true;
                    }
                }else{
                    counter=0;
                }
            }
        }
        return false; 
    }
    private boolean horizontalWin(int player){
        int counter = 0;
        for(int i = 0; i < noOfRows; i++){
            counter=0;
            for(int j = 0; j < noOfColomns; j++ ){
                if(boardStatus[i][j]==player){
                    counter++;
                    if(counter==4){
                        return true;
                    }
                }else{
                    counter=0;
                }
            }
        }
        return false; 
    }
    
    private boolean upwardDiagonalWin(int player){
        int counter=0;
        for(int i = 0; i < noOfColomns-3; i++){
            for(int j = 0; j < noOfRows-3; j++ ){
                counter = 0;
                for(int k = 0; k < 4; k++){
                    if(boardStatus[j+k][i+k]==player){
                       counter++;
                       if(counter == 4)
                           return true;
                    }
                }
            }
        }
        return false;
    }
    
    private boolean downwardDiagonalWin(int player){
        int counter=0;
        for(int i = 0; i < noOfColomns-3; i++){
            for(int j = 3; j < noOfRows; j++ ){
                counter = 0;
                for(int k = 0; k < 4; k++){
                    if(boardStatus[j-k][i+k]==player){
                       counter++;
                       if(counter == 4)
                           return true;
                    }
                }
            }
        }
        return false;
    }
    
}

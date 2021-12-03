/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdhbrack.tickytackytoey;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 *
 * @author vdh24
 */
public class Board {

    private SquareType[][] board;
    private SquareType toMove = SquareType.EX;
    private final int SIZE;
    private final Move[] moveList;

    /**
     * creates an empty board
     */
    public Board() {
        SIZE=3;
        board = new SquareType[3][3];
        for(int r=0;r<SIZE;r++){
            for(int c=0;c<SIZE;c++){
            board[r][c]=SquareType.NADDA;
            }
        }
        moveList=new Move[9];
    }
    public SquareType getToMove(){
        return toMove;
    }
    /**
     * 
     * @param b board to be copied
     * creates a new board that is an exact copy of the one passed into the parameter
     * as such all fields are also copied from the argument board 
     * 
     * 
     */
    private Board(Board b){
        SIZE=b.SIZE;
        board=new SquareType[SIZE][];
        for(int r=0;r<SIZE;r++){
            board[r]=Arrays.copyOf( b.board[r], SIZE);
        }
        moveList=Arrays.copyOf(b.moveList, b.moveList.length);
        toMove=b.toMove;
    }
    public static Board copyOf(Board b){
        return new Board(b);
    }
    

    /**
     *
     * @param row row of the square between index 0 and 2
     * @param column column of the square between index 0 and 2
     * @return the squaretype of the square denoting what side if any populates
     * it
     * @throws ArrayIndexOutOfBoundsException if row or column is greater than 2
     * or less than 0
     */
    public SquareType getSquare(int row, int column) {
        if (row >= SIZE) {
            throw new ArrayIndexOutOfBoundsException(row + "is outside of the board");
        }
        if (column >= SIZE) {
            throw new ArrayIndexOutOfBoundsException(column + "is outside of the board");
        }
        return board[row][column];
    }
    

    private boolean setSquare(int row, int column, SquareType type) {
        if (row >= SIZE) {
            throw new ArrayIndexOutOfBoundsException(row + "is outside of the board");
        }
        if (column >= SIZE) {
            throw new ArrayIndexOutOfBoundsException(column + "is outside of the board");
        }
        if(type==SquareType.NADDA){
            return false;
        }
        board[row][column] = type;
        return true;
    }
    /**
     * 
     * @param row 
     * @param column
     * @return a boolean indicating whether or not the move was made
     * in addition it performs the move for whatever side is set to move next 
     * will not make a move if the given row and column are not empty
     * adds made move to the movelist
     * @throws ArrayIndexOutOfBoundsException if the move is not in the range of the board
     */
    public boolean move(int row, int column) {
        if(board[row][column]!=SquareType.NADDA){
            return false;
        }
        toMove=SquareType.complimentOf(toMove);
        for(int i=moveList.length-1;i>=0;i--){
            if(i==0||moveList[i-1]!=null){
                moveList[i]=new Move(row,column);
            }
                
        }
        return setSquare(row, column, SquareType.complimentOf(toMove));
    }
    public boolean move(Move m){
        return move(m.getRow(),m.getColumn());
    }
    /**
     * 
     * @return whether or not the game is finished
     */
    public boolean winnerExists(){
        return getWinner()!=WinnerType.UNDETERMINED;
    }
    public Move getMove(int moveNum){
        if(moveNum<1||moveNum>moveList.length){
            throw new ArrayIndexOutOfBoundsException(moveNum+" is out of the range of possible moves");
        }
        if(moveList[moveNum-1]==null){
            throw new NoSuchElementException("Move number "+moveNum+" has not been played");
        }
        return moveList[moveNum-1];
    }
    public int getSize(){
        return SIZE;
    }
    public int getMoveNumber(){
        
        for(int i=moveList.length-1;i>=0;i--){
            if(i==0||moveList[i-1]!=null){
               return i+2;
            }
                
        }
        return 1;
    }

    public WinnerType getWinner() {
       for(int r=0;r<SIZE;r++){
           int x=0;
           int o=0;
           for(int c=0;c<SIZE;c++){
              
               switch(getSquare(r, c)){
                   case EX:x++;break;
                   case OH:o++;break;    
                   default:break;
                   }
               }
           if(x>=SIZE){
               
               return WinnerType.X_WINS;
           }else if(o>=SIZE){
               
               return WinnerType.O_WINS;
           }
           
       }
       for(int c=0;c<SIZE;c++){
           int x=0;
           int o=0;
           for(int r=0;r<SIZE;r++){
               switch(getSquare(r, c)){
                   case EX:x++;break;
                   case OH:o++;break;    
                   default:break;
                   }
               }
           if(x>=SIZE){
               
               
               return WinnerType.X_WINS;
           }else if(o>=SIZE){
               
                
               return WinnerType.O_WINS;
           }
           
       }
       int xFront=0;
       int oFront=0;
       int xBack=0;
       int oBack=0;
       for(int i=0;i<SIZE;i++){
           if(board[i][i].equals(SquareType.EX)){
               xFront++;
           }else if(board[i][i].equals(SquareType.OH)){
               oFront++;
           }
           if(board[i][SIZE-(1+i)].equals(SquareType.EX)){
               xBack++;
           }else if(board[i][SIZE-(1+i)].equals(SquareType.OH)){
               oBack++;
           }
       }
       if(xFront>=SIZE||xBack>=SIZE){
            
           return WinnerType.X_WINS;
       }
       if(oFront>=SIZE||oBack>=SIZE){
           
           return WinnerType.O_WINS;
       }

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (getSquare(r, c).equals(SquareType.NADDA)) {
                    return WinnerType.UNDETERMINED;
                }
            }
        }
        return WinnerType.CATS_GAME;
    }
    @Override
    public String toString(){
        StringBuilder str=new StringBuilder();
        for(int r=0; r< SIZE;r++){
            for(int c=0;c<SIZE;c++){
                str.append(board[r][c].toChar());
            }
            str.append("\n");
        }
        str.append("winner is").append(getWinner());
        return str.toString();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdhbrack.tickytackytoey;

import java.util.concurrent.Callable;

/**
 *
 * @author vdh24
 */
public class GameRunner implements Callable {
    
    private final GamePlayer exPlayer; 
    private final GamePlayer ohPlayer;
    private final Board board;
   
    public GameRunner(GamePlayer xPlayer,GamePlayer oPlayer){
        exPlayer=xPlayer;
        ohPlayer=oPlayer;
        board=new Board();
    }
    

    @Override
    public Object call() throws Exception {
       while(!board.winnerExists()){
           
          if(board.getToMove().equals(SquareType.EX)){
              board.move(exPlayer.promptMove(Board.copyOf(board)));
          }
          else{
               board.move(ohPlayer.promptMove(Board.copyOf(board)));
          }
       }
       return board;
    }
    
}

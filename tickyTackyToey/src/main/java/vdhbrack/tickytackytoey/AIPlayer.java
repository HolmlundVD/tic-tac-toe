/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdhbrack.tickytackytoey;

import static java.lang.Double.compare;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vdh24
 */
public class AIPlayer implements GamePlayer {

    @Override
    public Move promptMove(Board b) {
       if(b.getSquare(1, 1).equals(SquareType.NADDA)){
           return new Move(1,1);
       }
        Map<Move,WinnerType> map=new HashMap<>();
        for (int r = 0; r < b.getSize(); r++) {
            for (int c = 0; c < b.getSize(); c++) {
                if (b.getSquare(r, c).equals(SquareType.NADDA)) {
                    Board temp = Board.copyOf(b);
                    Move m=new Move(r,c);
                    temp.move(m);
                    if(!makeMove(temp).equals(WinnerType.UNDETERMINED)){
                    map.put(m,makeMove(temp) );
                    }
                }
            }  
        }
        if(b.getToMove().equals(SquareType.EX)){
        return map.entrySet()
                .stream()
                .min((i,j)->compare(i.getValue().valueOf(),j.getValue().valueOf()))
                .get()
                .getKey();
        }else{
            return map.entrySet()
                .stream()
                .min((i,j)->(-1)*compare(i.getValue().valueOf(),j.getValue().valueOf()))
                .get()
                .getKey();
            
        }
                
    }

    private WinnerType makeMove(Board b) {
        if (b.winnerExists()) {
            return b.getWinner();
        }
        EnumSet<WinnerType> other_set = EnumSet.noneOf(WinnerType.class);
        for (int r = 0; r < b.getSize(); r++) {
            for (int c = 0; c < b.getSize(); c++) {
                if (b.getSquare(r, c).equals(SquareType.NADDA)) {
                    Board temp = Board.copyOf(b);
                    temp.move(new Move(r, c));
                    WinnerType tempType = makeMove(temp);
                    if (tempType != WinnerType.UNDETERMINED) {
                        other_set.add(tempType);
                    }
                }
            }
        }
        if(b.getToMove().equals(SquareType.EX)){
            return other_set.stream().min((i,j)->compare(i.valueOf(),j.valueOf())).get();
        }else{
             return other_set.stream().min((i,j)->(-1)*compare(i.valueOf(),j.valueOf())).get();
        }
    }

}

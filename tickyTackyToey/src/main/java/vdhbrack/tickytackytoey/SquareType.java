/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdhbrack.tickytackytoey;

/**
 *
 * @author vdh24
 */
public enum SquareType {
    EX('X'),
    OH('O'),
    NADDA('-');
    private final char chare;
    SquareType(char c){chare=c;}
    public char toChar(){
        return chare;
    }
    public static SquareType complimentOf(SquareType type){
        switch(type){
            case EX:return OH;
            case OH:return EX;
            case NADDA:return NADDA;
            default:return NADDA;
        }
    }
}

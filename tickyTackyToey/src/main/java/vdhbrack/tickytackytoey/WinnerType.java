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
public enum WinnerType {
    UNDETERMINED(0),
    X_WINS(1),
    CATS_GAME(2),
    O_WINS(3);
    private final int value;

    private WinnerType(int v){
        value=v;
    }
    public int  valueOf(){return value;}
}

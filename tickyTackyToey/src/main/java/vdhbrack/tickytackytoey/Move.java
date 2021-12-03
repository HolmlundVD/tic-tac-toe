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
public class Move {
    private int r;
    private int c;
    public Move(int row,int column){
        r=row;
        c=column;
    }
    public int getRow(){
     return r;   
    }
    public int getColumn(){
     return c;   
    }
}

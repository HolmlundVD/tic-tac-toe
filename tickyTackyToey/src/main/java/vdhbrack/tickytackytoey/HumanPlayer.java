/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vdhbrack.tickytackytoey;

import java.util.Scanner;

/**
 *
 * @author vdh24
 */
public class HumanPlayer implements GamePlayer {
    Scanner s;
    public HumanPlayer(){
         s=new Scanner(System.in);
    }
    @Override
    public Move promptMove(Board b) {
        System.out.println(b);
        System.out.println("what row");
        int r= s.nextInt();
        System.out.println("what column");
        int c=s.nextInt();
        return new Move(r,c);
    }

   
    
}

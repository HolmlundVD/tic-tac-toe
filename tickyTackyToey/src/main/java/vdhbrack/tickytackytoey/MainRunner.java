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
public class MainRunner {
    public static void main(String[] args) throws Exception{
        GameRunner g=new GameRunner(new HumanPlayer(),new AIPlayer());
        System.out.println(g.call());
        GameRunner n=new GameRunner(new AIPlayer(),new HumanPlayer());
        System.out.println(n.call());
    }
}

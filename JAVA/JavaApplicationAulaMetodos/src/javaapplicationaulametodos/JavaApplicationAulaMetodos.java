/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplicationaulametodos;

import java.util.Scanner;

import javaapplicationaulametodos.Operacoes;

/**
 *
 * @author antonio
 */
public class JavaApplicationAulaMetodos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int num1, num2;
                
        Scanner s = new Scanner(System.in);
        
        System.out.println("Informe o primeiro numero: ");
        num1 = s.nextInt();
        System.out.println("Informe o segunda numero: ");
        num2 = s.nextInt();
        
        Operacoes op = new Operacoes();
        op.soma(num1, num2);
        System.out.println("A subtração é: "+op.subtracao(num1, num2));

    }
    
}

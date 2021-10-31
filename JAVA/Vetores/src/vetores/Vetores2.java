
package vetores;

import java.util.Scanner;


public class Vetores2 {
    public static void main(String[] args) {
        int num[] = new int[4];
        Scanner s = new Scanner(System.in);
        
        
        int i = 0;
        
        while(i<4){
            System.out.println("Informe o "+(i+1)+" valore: ");
            num[i] = s.nextInt();
            i++;
        }
        
        for(i=0;i<4;i++){
            System.out.println("o valor de "+(i+1)+" valor é " + num[i]);
        }
        
    }
}

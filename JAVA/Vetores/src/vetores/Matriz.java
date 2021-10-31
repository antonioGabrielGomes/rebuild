
package vetores;

import java.util.Scanner;

public class Matriz {
    public static void main(String[] args) {
        
        float mat[][] = new float[2][2];
            
        Scanner s = new Scanner(System.in);
        
        System.out.println("Informe 4 valores do tipo real!");
        
        for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 2; i++) {
                mat[k][i] = s.nextFloat();
            }
        }
        
         for (int k = 0; k < 2; k++) {
            for (int i = 0; i < 2; i++) {
                System.out.println("Os valores informados são " + mat[k][i]);
            }
        }
        
    }    
}

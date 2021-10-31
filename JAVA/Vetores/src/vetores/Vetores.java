package vetores;

import java.util.Scanner;

public class Vetores {

    public static void main(String[] args) {
        
        int num[] = new int[4];
        
        Scanner s = new Scanner(System.in);
        
        System.out.println(">>>> ");
        num[0] = s.nextInt();
        System.out.println(">>>> ");
        num[1] = s.nextInt();
        System.out.println(">>>> ");
        num[2] = s.nextInt();
        System.out.println(">>>> ");
        num[3] = s.nextInt();
        
        System.out.println(num[0]);
        System.out.println(num[1]);
        System.out.println(num[2]);
        System.out.println(num[3]);
        
        
    }
    
}

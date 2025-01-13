package view;

import java.util.Scanner;
import java.util.Set;

public class PageService {
    public static int getChoice(Set<Integer> choices) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int c = sc.nextInt();
            if (choices.contains(c)) {
                return c;
            }
            System.out.println("Invalid Choice!");
        }
    }
}

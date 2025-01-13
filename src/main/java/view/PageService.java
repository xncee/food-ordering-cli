package view;

import java.util.Scanner;
import java.util.Set;

public class PageService {
    public static int getChoice(Set<Integer> choices) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(">> ");
            try {
                int c = sc.nextInt();
                sc.nextLine();
                if (choices.contains(c)) {
                    return c;
                }
            }
            catch (Exception e) {
                sc.nextLine();
            }
            System.out.println("Invalid Choice!");
            return getChoice(choices);
        }
    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

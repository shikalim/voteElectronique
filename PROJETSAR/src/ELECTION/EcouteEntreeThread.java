package ELECTION;

import java.util.Scanner;

public class EcouteEntreeThread {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        System.out.println(string);
        scanner.close();
    }
}
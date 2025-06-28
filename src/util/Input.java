package util;

import java.util.Scanner;

public class Input {

    private static Scanner data =  new Scanner(System.in);

    public static String getInputString(String prompt) {
        data.reset();
        System.out.print("> " + prompt + " : ");
        return data.nextLine();
    }

    public static int getInputInt(String prompt) {
        data.reset();
        System.out.print("> " + prompt + " : ");
        return data.nextInt();
    }

}

package com.keshaun.sse.util;

import java.util.Scanner;

public class Input {
    private static Scanner in = new Scanner(System.in);

    public static int getInt(int min, int max) {
        while (true) {
            int i = in.nextInt();

            if (i >= min && i <= max)
                return i;
            else
                System.out.println("Please enter a valid input.");
        }
    }

    public static String getString() {
        return in.nextLine();
    }
}

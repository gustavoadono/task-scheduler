package dev.adonogtx;

import dev.adonogtx.ui.ConsoleApplication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleApplication application = new ConsoleApplication(scanner);
        application.runApplication();

    }
}

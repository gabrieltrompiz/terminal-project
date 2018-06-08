package com.gatc.terminal;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        int choice;

        do {
            System.out.println("1. Windows Syntax");
            System.out.println("2. Unix Syntax");
            System.out.println("0. Salir");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    WindowsSyntax winSyn = new WindowsSyntax();
                    winSyn.runWin();
                    break;

                case 2:
                    UnixSyntax uniSyn = new UnixSyntax();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.print("Select a valid option.\n");
                    break;
            }
        }while(choice != 0);
    }
}
package com.gatc.terminal;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        int choice = 0;

        do {
            System.out.println("1. Windows Syntax");
            System.out.println("2. Unix Syntax");
            System.out.println("0. Exit");
            Scanner sc = new Scanner(System.in);
            try {
                choice = sc.nextInt();
            }
            catch (Exception e){
                System.out.println("Characters not allowed. Please restart program.\n");
            }

            switch (choice) {
                case 1:
                    WindowsSyntax winSyn = new WindowsSyntax();
                    winSyn.runWin();
                    break;

                case 2:
                    UnixSyntax unixSyn = new UnixSyntax();
                    unixSyn.runUnix();
                    break;

                case 0:
                    System.exit(0);

                default:
                    System.out.print("Select a valid option.\n\n");
                    break;
            }
        }while(true);
    }
}
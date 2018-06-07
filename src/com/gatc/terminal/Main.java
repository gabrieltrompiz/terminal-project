package com.gatc.terminal;

import java.util.Scanner;

public class Main {

    public static void main(String args[]){

        System.out.println("1. Windows Syntax");
        System.out.println("2. Unix Syntax");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice){
            case 1:
                WindowsSyntax winSyn = new WindowsSyntax();
                winSyn.runWin();
                break;

            case 2:
                UnixSyntax uniSyn = new UnixSyntax();
                break;

            default:
                break;
        }
    }
}
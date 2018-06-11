package com.gatc.terminal;

import java.util.Scanner;

@SuppressWarnings("WeakerAccess")

public class UnixSyntax extends Commands {


    public void runUnix() {
        Scanner sc = new Scanner(System.in);

        do {
            StringBuilder unixPath = new StringBuilder(currentPath.replaceAll("\\\\", "/")).replace(0,2, "~");
            System.out.println(unixPath + " >"); //Prints current path after every command
            command = sc.nextLine();
            command = command.trim().replaceAll(" +", " "); //Replace multiple spaces to one space
            cmdParts = command.split(" "); //Separates cmd into parts. [0] is command, [1] source, [2] destination

            switch (cmdParts[0]) {
                case "cp":
                    if (cmdParts.length == 3) {
                        copyFile(cmdParts[1], cmdParts[2]);
                    }
                    else{
                        System.out.println("'cp' commands requires one source and one destination path.");
                    }
                    break;

                case "mkdir":
                    if (cmdParts.length == 2) {
                        createDirectory(cmdParts[1]);
                    }
                    else {
                        System.out.println("'mkdir' command requires one destination path.");
                    }
                    break;

                case "rm":
                    if (cmdParts.length == 2 && !cmdParts[0].contains("-rf")) {
                        deleteFile(cmdParts[1]);
                    }
                    else if (cmdParts.length == 3 && cmdParts[1].equals("-rf")){
                        deleteFolder(cmdParts[2]);
                    }
                    else {
                        System.out.println("'rm' command requires one destination path.");
                    }
                    break;

                case "ls":
                    if (cmdParts.length == 1) {
                        System.out.println("Directory of " + unixPath + ":");
                        listDirectories();
                    }
                    else {
                        System.out.println("'ls' command doesn't take arguments.");
                    }
                    break;

                case "cd":
                    if (cmdParts.length == 2) {
                        changeDirectory(cmdParts[1]);
                    }
                    else {
                        System.out.println("'cd' command requires one destination path.");
                    }
                    break;

                case "mv":
                    if (cmdParts.length == 3) {
                        moveFile(cmdParts[1], cmdParts[2]);
                    }
                    else {
                        System.out.println("'mv' command requires one source and one destination paths");
                    }
                    break;

                case "more":
                case "cat":
                    if (cmdParts.length == 2) {
                        readText(cmdParts[1]);
                    }
                    else {
                        System.out.println("'more' command requires one destination path.");
                    }
                    break;

                case "echo":
                    createTxt(cmdParts);
                    break;

                case "exit":
                    break;

                default:
                    System.out.println("'" + cmdParts[0] + "' is not recognized as a command. \n");
            }

        } while (!cmdParts[0].equals("exit"));
        System.exit(0);
    }
}

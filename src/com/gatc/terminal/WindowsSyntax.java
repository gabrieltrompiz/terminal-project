package com.gatc.terminal;

import java.util.Scanner;

@SuppressWarnings("WeakerAccess")

public class WindowsSyntax extends Commands {

    public void runWin() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(currentPath + " >");
            command = sc.nextLine();
            command = command.trim().replaceAll(" +", " "); //Replace multiple spaces to one space
            cmdParts = command.split(" "); //Separates cmd into parts. [0] is command, [1] source, [2] destination, generally

            switch (cmdParts[0]) {
                case "copy":
                    if (cmdParts.length == 3) {
                        copyFile(cmdParts[1], cmdParts[2]);
                    }
                    else{
                        System.out.println("'copy' command requires one source and one destination path.");
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

                case "del":
                    if (cmdParts.length == 2) {
                        deleteFile(cmdParts[1]);
                    }
                    else {
                        System.out.println("'del' command requires one destination path.");
                    }
                    break;

                case "dir":
                    if (cmdParts.length == 1) {
                        System.out.println("Directory of " + currentPath + ":");
                        listDirectories();
                    }
                    else {
                        System.out.println("'dir' command doesn't take arguments.");
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

                case "move":
                    if (cmdParts.length == 3) {
                        moveFile(cmdParts[1], cmdParts[2]);
                    }
                    else {
                        System.out.println("'move' command requires one source and one destination paths");
                    }
                    break;

                case "rmdir":
                    if (cmdParts.length == 2) {
                        deleteFolder(cmdParts[1]);
                    }
                    else {
                        System.out.println("'rmdir' command requires one destination path.");
                    }
                    break;

                case "more":
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
                    break;
            }

        } while (!cmdParts[0].equals("exit"));
        System.exit(0);
    }
}

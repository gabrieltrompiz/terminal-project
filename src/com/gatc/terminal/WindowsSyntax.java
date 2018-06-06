package com.gatc.terminal;

import java.lang.*;
import java.util.Scanner;

public class WindowsSyntax implements CopyFile, CreateDir, DeleteFile, ListDir, MoveDir, MoveFile, WriteTxt{

    public void runWin() {
        String currentPath = "D:\\Dev";
        String command;
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(currentPath);
            command = sc.nextLine();

            switch (command.trim()) {
                case "copy":
                    copyFile();
                    break;

                case "mkdir":
                    createDirectory();
                    break;

                case ""
            }

        } while (command != "exit");
    }
    public void copyFile()
    {

    }

    public void createDirectory()
    {

    }

    public void deleteFile()
    {

    }

    public void listDirectories()
    {

    }

    public void moveDirectory()
    {

    }

    public void moveFile()
    {

    }

    public void writeText()
    {

    }
}

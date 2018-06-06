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
            command = command.trim();

            if (command.startsWith("copy"))
                copyFile(command);

            if (command.startsWith("mkdir"))
                createDirectory(command);

            if (command.startsWith("del"))
                deleteFile(command);

            if (command.startsWith("dir"))
                listDirectories(command);

            if (command.startsWith("cd"))
                moveDirectory(command);

            if (command.startsWith("move"))
                moveFile(command);

            if (command.startsWith("echo") && command.contains(">>"))
                writeText(command);

        } while (command != "exit");
        System.exit(0);
    }

    public void copyFile(String cmd)
    {

    }

    public void createDirectory(String cmd)
    {

    }

    public void deleteFile(String cmd)
    {

    }

    public void listDirectories(String cmd)
    {

    }

    public void moveDirectory(String cmd)
    {

    }

    public void moveFile(String cmd)
    {

    }

    public void writeText(String cmd)
    {

    }
}

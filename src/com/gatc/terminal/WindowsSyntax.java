package com.gatc.terminal;

import java.lang.*;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;

public class WindowsSyntax implements CopyFile, CreateDir, DeleteFile, ListDir, MoveDir, MoveFile, WriteTxt{

    String currentPath = "D:\\Dev";
    String command, sourcePath, destinationPath;
    int index;

    public void runWin() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(currentPath);
            command = sc.nextLine();
            command = command.trim();

            if (command.startsWith("copy"))
                copyFile(command);

            else if (command.startsWith("mkdir"))
                createDirectory(command);

            else if (command.startsWith("del"))
                deleteFile(command);

            else if (command.startsWith("dir"))
                listDirectories(command);

            else if (command.startsWith("cd"))
                moveDirectory(command);

            else if (command.startsWith("move"))
                moveFile(command);

            else if (command.startsWith("echo") && command.contains(">>"))
                writeText(command);

            else
                System.out.print("'" + command + "' is not recognized as a command.\n");

        } while (!command.equals("exit"));
        System.exit(0);
    }

    public void copyFile(String cmd)
    {
        cmd = cmd.trim().replaceAll(" +", " "); //Replace multiple spaces to one space
        String[] cmdParts = cmd.split(" "); //Separates cmd into parts. [0] is command, [1] source, [2] destination

        if (cmdParts.length != 3){ //Must have 3 parts.
            System.out.println("Copy command must have 2 arguments.");
            return;
        }
        if (cmdParts[1].contains("\\")){
            sourcePath = cmdParts[1];
            index = sourcePath.lastIndexOf("\\");
            if (!cmdParts[2].contains(":\\"))
               destinationPath = currentPath + "\\" + cmdParts[2] + "\\" + cmdParts[1].substring(index);
            else
                destinationPath = cmdParts[2] + "\\" + cmdParts[1].substring(index);
        }
        else{
            sourcePath = (currentPath + "\\" + cmdParts[1]);
            if (cmdParts[2].contains(":\\"))
                 destinationPath = (cmdParts[2] + "\\" + cmdParts[1].substring(index));
            else
                destinationPath = currentPath + "\\" + cmdParts[2] + "\\" + cmdParts[1].substring(index);
        }

        Path sourceFile = Paths.get(sourcePath);
        Path destinationFile = Paths.get(destinationPath);

        try {
            Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        }
        catch (IOException e) {
            System.out.println("Not valid path."); //Exception when path (source or dest) can't be found.
        }
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

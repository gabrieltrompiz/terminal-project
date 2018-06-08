package com.gatc.terminal;

import java.lang.*;
import java.nio.file.*;
import java.util.Scanner;
import java.io.*;

public class WindowsSyntax implements CopyFile, CreateDir, DeleteFile, ListDir, MoveDir, MoveFile, WriteTxt{

    private String currentPath = "D:\\Dev";
    private String command;
    private String[] processedPaths;
    private int index;

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

        PathManager path = new PathManager();
        processedPaths = path.pathManager(currentPath, cmdParts[1], cmdParts[2]);

        index = processedPaths[0].lastIndexOf("\\");

        processedPaths[1] = processedPaths[1] + "\\" + processedPaths[0].substring(index);

        Path sourceFile = Paths.get(processedPaths[0]);
        Path destinationFile = Paths.get(processedPaths[1]);

        try {
            Files.copy(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
        }
        catch (IOException e) {
            System.out.println("Invalid path."); //Exception when path (source or dest) can't be found.
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

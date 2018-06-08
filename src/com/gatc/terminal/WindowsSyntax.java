package com.gatc.terminal;

import sun.java2d.pipe.SpanShapeRenderer;

import java.lang.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.*;

@SuppressWarnings("WeakerAccess")

public class WindowsSyntax implements CopyFile, CreateDir, DeleteFile, ListDir, ChangeDir, MoveFile, WriteTxt{

    private String currentPath = "D:\\Dev";
    private String command;
    private String[] processedPaths, cmdParts;
    private int index;

    public void runWin() {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println(currentPath + " >");
            command = sc.nextLine();
            command = command.trim().replaceAll(" +", " "); //Replace multiple spaces to one space
            cmdParts = command.split(" "); //Separates cmd into parts. [0] is command, [1] source, [2] destination

            switch (cmdParts[0]) {
                case "copy":
                    if (cmdParts.length == 3) {
                        copyFile(cmdParts[1], cmdParts[2]);
                    }
                    else{
                        System.out.println("'copy' commands requires one source and one destination paths.");
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
                      listDirectories();
                  }
                  else if (cmdParts.length == 2) {
                      listDirectories(cmdParts[1]);
                  }
                  else {
                      System.out.println("'mkdir' command only accepts one destination");
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

                case "echo":
                    if (cmdParts.length == 4) {
                        writeText(cmdParts[1], cmdParts[2], cmdParts[3]);
                    }
                    else {
                        System.out.println("aaaa");
                    }
                    break;

                case "exit":
                    break;

                default:
                    System.out.println("'" + cmdParts[0] + "' is not recognized as a command. \n");
            }

        } while (!cmdParts[0].equals("exit"));
        System.exit(0);
    }

    public void copyFile(String source, String destination)
    {
        PathManager path = new PathManager();
        processedPaths = path.pathManager(currentPath, source, destination);

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

    public void createDirectory(String path)
    {

    }

    public void deleteFile(String path)
    {

    }

    public void listDirectories()
    {
        File toPath = new File(currentPath);
        String[] directories = toPath.list();
        for (String directory : directories){
            String pathname = currentPath + "\\" + directory;
            File files = new File(pathname);
            String format = "%-21s%-8s%s%n";
            SimpleDateFormat lastMod = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            if (files.isDirectory()) {
                System.out.printf(format, lastMod.format(files.lastModified()), "<DIR>", directory);
            }
            else {
                System.out.printf(lastMod.format(files.lastModified()), "<FILE>", directory);
            }
        }
    }

    public void listDirectories(String destination){

    }

    public void changeDirectory(String path)
    {
        PathManager toPath = new PathManager();
        processedPaths = toPath.pathManager(currentPath, path);
        Path existentPath = Paths.get(processedPaths[0]);

        if (Files.exists(existentPath)) {
            currentPath = processedPaths[0];
            if (currentPath.contains(":") && (currentPath.length() == 2)) {
                currentPath += "\\";
            }
        }
        else {
            System.out.print("Invalid path.\n");
        }
    }

    public void moveFile(String source, String destination)
    {
        PathManager path = new PathManager();
        processedPaths = path.pathManager(currentPath, source, destination);

        index = processedPaths[0].lastIndexOf("\\");

        processedPaths[1] = processedPaths[1] + "\\" + processedPaths[0].substring(index);

        Path sourceFile = Paths.get(processedPaths[0]);
        Path destinationFile = Paths.get(processedPaths[1]);

        try {
            Files.move(sourceFile, destinationFile, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            System.out.println("Invalid path."); //Exception when path (source or dest) can't be found.
        }
    }

    public void writeText(String text, String operand, String destination)
    {

    }
}

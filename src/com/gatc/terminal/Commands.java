package com.gatc.terminal;

import java.lang.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.io.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Commands{

    String currentPath = System.getProperty("user.home");
    String command;
    String[] cmdParts;
    private String[] processedPaths;
    private int index;
    private PathManager path = new PathManager();

    void copyFile(String source, String destination)
    {
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

    void createDirectory(String paths)
    {
        processedPaths = path.pathManager(currentPath, paths);
        File files = new File(processedPaths[0]);

        if (files.exists()) {
            for (int i = 1; i < 100; i++) {
                files = new File(processedPaths[0] + "(" + Integer.toString(i) + ")");
                if (!files.exists()) {
                    System.out.println("Directory already exists. Want to create a directory named '" +
                    files.getName() + "'? (y/n)");

                    Scanner sc = new Scanner(System.in);
                    char option = sc.next().charAt(0);

                    if (option == 'y') {
                        new File(processedPaths[0].substring(0, processedPaths[0].lastIndexOf("\\"))
                        + "\\" + files.getName()).mkdirs();
                        break;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        else {
            new File(files.getName());
        }
    }

    void deleteFile(String paths)
    {
        processedPaths = path.pathManager(currentPath, paths);

        File files = new File(processedPaths[0]);
        Path pathname = Paths.get(processedPaths[0]);

        if (!files.isDirectory()) {
            try {
                Files.delete(pathname);
            } catch (Exception e) {
                System.out.println("Invalid path."); //Exception when path (source or dest) can't be found.
            }
        }
        else {
            System.out.println("Cannot delete directories with 'del' command.");
        }
    }

    void listDirectories()
    {
        File toPath = new File(currentPath);
        String[] directories = toPath.list();

        for (String directory : directories){
            String pathname = currentPath + "\\" + directory;
            File files = new File(pathname);
            String format = "%-8s%-22s%s%n";

            SimpleDateFormat sdf = new SimpleDateFormat("dd\\MM\\yyyy HH:mm:ss");
            File file = new File(pathname);

            if (files.isDirectory() && !files.isHidden()) {
                System.out.printf(format, "<DIR>", sdf.format(file.lastModified()), directory);
            }
            else if (!files.isHidden()) {
                System.out.printf(format, "<FILE>", sdf.format(file.lastModified()), directory);
            }
        }
    }

    void changeDirectory(String paths)
    {
        processedPaths = path.pathManager(currentPath, paths);
        Path existentPath = Paths.get(processedPaths[0]);

        if (Files.exists(existentPath)) {
            currentPath = processedPaths[0];
            if (currentPath.contains(":") && (currentPath.length() == 2)) {
                currentPath += "\\";
            }
        }
        else {
            System.out.print("'" + processedPaths[0] + "' path doesn't exists.\n");
        }
    }

    void moveFile(String source, String destination)
    {
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

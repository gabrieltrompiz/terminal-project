package com.gatc.terminal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings({"Duplicates", "WeakerAccess"})

public class PathManager {

    protected String sourcePath;
    protected String destinationPath;
    protected String[] cmdArguments = new String[2];
    protected int index;

    public String[] pathManager(String currentPath, String source, String destination){
        if (source.contains("\\")){
            if (source.contains(":\\")){
                sourcePath = source;
            }
            else{
                sourcePath = currentPath + "\\" + source;
            }
        }
        else{
            sourcePath = currentPath + "\\" + source;
        }

        index = sourcePath.lastIndexOf("\\");

        if (destination.contains(":\\")){
            destinationPath = destination;
        }
        else{
            destinationPath = currentPath + "\\" + destination;
        }

        if (sourcePath.contains("..")) {
            List<String>  sourceList = new LinkedList<>(Arrays.asList(sourcePath.split("\\\\")));

            while (sourceList.contains("..")){
                index = sourceList.indexOf("..");
                sourceList.remove(index);
                sourceList.remove(index - 1);
            }

            String[] sourceArray = new String[sourceList.size()];
            sourceArray = sourceList.toArray(sourceArray);
            StringBuilder sbSource = new StringBuilder();

            for (String i : sourceArray){
                sbSource.append(i).append("\\");
            }
            sbSource.deleteCharAt(sbSource.length() - 1);
            sourcePath = sbSource.toString();
        }

        if (destinationPath.contains("..")){
            List<String> destinationList = new LinkedList<>(Arrays.asList(destinationPath.split("\\\\")));

            while (destinationList.contains("..")){
                index = destinationList.indexOf("..");
                destinationList.remove(index);
                destinationList.remove(index - 1);
            }

            String[] destinationArray = new String[destinationList.size()];
            destinationArray = destinationList.toArray(destinationArray);

            StringBuilder sbDestination = new StringBuilder();

            for (String i : destinationArray){
                sbDestination.append(i).append("\\");
            }
            sbDestination.deleteCharAt(sbDestination.length() - 1);
            destinationPath = sbDestination.toString();
        }

        cmdArguments[0] = sourcePath;
        cmdArguments[1] = destinationPath;

        return cmdArguments;
    }

    public String[] pathManager(String currentPath, String uniquePath) {
        if (uniquePath.contains(":\\")){
            destinationPath = uniquePath;
        }
        else{
            destinationPath = currentPath + "\\" + uniquePath;
        }

        if (destinationPath.contains("..")){
            List<String> destinationList = new LinkedList<>(Arrays.asList(destinationPath.split("\\\\")));

            while (destinationList.contains("..")){
                index = destinationList.indexOf("..");
                destinationList.remove(index);
                destinationList.remove(index - 1);
            }

            String[] destinationArray = new String[destinationList.size()];
            destinationArray = destinationList.toArray(destinationArray);

            StringBuilder sbDestination = new StringBuilder();

            for (String i : destinationArray){
                sbDestination.append(i).append("\\");
            }
            sbDestination.deleteCharAt(sbDestination.length() - 1);
            destinationPath = sbDestination.toString();
        }

        if (destinationPath.contains("\\\\")) {
            destinationPath = destinationPath.replaceAll("\\\\+", "\\\\");
        }

        cmdArguments[0] = destinationPath;
        return cmdArguments;
    }
}
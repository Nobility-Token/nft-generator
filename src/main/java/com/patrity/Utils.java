package com.patrity;

import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.File;
import java.util.Random;

public class Utils {

    public static Random random = new Random();
    public static ObjectWriter mapper = new ObjectMapper().writerWithDefaultPrettyPrinter();

    public static void listFiles() {
        File folder = new File("./images/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println();
                System.out.println("*** Directory: " + listOfFiles[i].getName()+ " ***");
                File[] listOfSubfiles = listOfFiles[i].listFiles();
                System.out.println(listOfSubfiles.length + " files in dir");
                for (int x = 0; x < listOfSubfiles.length; x++) {
                    System.out.println("File " + listOfSubfiles[x].getName());
                }
            }
        }
    }
}

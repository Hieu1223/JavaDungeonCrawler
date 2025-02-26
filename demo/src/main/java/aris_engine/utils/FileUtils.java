package aris_engine.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtils {
    public static String readFile(String path) {
        String res = "";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                res += myReader.nextLine();
                res+= "\n";
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when reading " + path);
            e.printStackTrace();
        }
        return res;
    }
}

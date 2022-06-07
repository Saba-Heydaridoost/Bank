package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Files {
    public static File createCSVFile(String name) {
        File myCsv = new File(".\\"+name+".csv");
        try {
            if (myCsv.createNewFile()) {
//                System.out.printf("File %s Created Successfully", myCsv.getName());
            }
//            else {
//                System.out.println("File exits.....");
//            }
        } catch (IOException e) {
            e.getMessage();
        }
        return myCsv;
    }

    public static void appendToFile(File myFile, String Content) {
        try {
            FileWriter fileWriter = new FileWriter(myFile,false);
            fileWriter.write(Content);
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile(String name, String[] Content) throws IOException {
        FileWriter fileWriter = new FileWriter(name+".txt" , false);
        fileWriter.write("identity_number:"+Content[0]+"\r\npassword:"+Content[1]+"\r\nfirstName:"+Content[2]
                +"\r\nfamilyName:"+Content[3]+"\r\ndateOfBirth:"+Content[4]+"\r\ngender:"+Content[5]);
        fileWriter.close();
    }

    public static File writeCSV(String path, ArrayList<String[]> Content) {
        File myCSVFile = new File(path);

        if (!myCSVFile.exists()) {
            try {
                myCSVFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String data = "";
        for (String[] Row : Content) {
            for (String Cell : Row) {
                data += Cell + ',';
            }
            data += "\n";
        }

        appendToFile(myCSVFile, data);

        return myCSVFile;

    }


}

package org.example;

import org.example.workWithFileService.WorkWithFileServiceImpl;

import java.io.*;

public class UpdateLineFromFile {

    public static final long RECORD_LENGTH = 100;
    String starPrefix = "Rakhimov";
    String replacedString = "Petrov";




    
//
//        RandomAccessFile file = null;
//        try {
//            file = new RandomAccessFile(new File(WorkWithFileServiceImpl.FILE_NAME), "rw");
//            String line = "";
//            while ((line = file.readLine()) != null) {
//                if (line.startsWith(starPrefix)) {
//                    file.seek(replacedString.length());
//                    file.writeBytes(replacedString);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}


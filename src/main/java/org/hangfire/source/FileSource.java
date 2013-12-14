package org.hangfire.source;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileSource implements Source {

    private static final String path = "levels/level_%s.txt";

    @Override
    public String retrieveData(final int level) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(String.format(path, String.valueOf(level)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String returnData = null;
        try {
            returnData = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnData;
    }

    @Override
    public String retrieveData() {
        return null;
    }
}

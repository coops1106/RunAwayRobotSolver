package org.hangfire.source;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class HttpSource implements Source {

    private static final String USER_NAME = "hangfire";
    private static final String PASSWORD = "empire12";
    private static final String LEVEL = "&gotolevel=";
    private static final String BASE_URL = "http://www.hacker.org/runaway/?name=" + USER_NAME + "&password=" + PASSWORD + LEVEL;

    @Override
    public String retrieveData(int level) {
        BufferedReader bufferedReader = null;
        StringBuilder sb = null;
        try {
            URL url = new URL(BASE_URL + level);
            URLConnection urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            sb = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(sb);
        return sb.toString();
    }
}

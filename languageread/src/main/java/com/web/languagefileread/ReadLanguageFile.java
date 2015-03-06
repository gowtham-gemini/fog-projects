package com.web.languagefileread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author gowtham
 */
public class ReadLanguageFile {

    public static void main(String[] args) {

        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("/etc/fogpanel/messages_en.properties");

            File file = new File("/etc/fogpanel/language.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            // load a properties file
            prop.load(input);
            for (String key : prop.stringPropertyNames()) {
                String value = prop.getProperty(key);
                bw.append(value + " = ");
                bw.newLine();
                System.out.println(key + " => " + value);
            }
            bw.close();
            System.out.println("Done");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

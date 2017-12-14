package log;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nickw on 13-12-2017.
 */
public class Logging {

    private static PrintWriter printWriter;

    private static PrintWriter getPrintWriter() {
        return printWriter;
    }

    private static void setPrintWriter(PrintWriter pw) {
        printWriter = pw;
    }

    private static String getCurrentTime(String format) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(format);//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static void createLoggingFile() {

        try {
            String filename = getCurrentTime("dd-MM-yyyy_HHmmss") + ".txt";

            // create directories if they dont exist already
            new File ("src"+File.separator+"log"+File.separator+getCurrentTime("dd-MM-yyyy")).mkdirs();


            // create the file
            File file = new File("src"+File.separator+"log"+File.separator+getCurrentTime("dd-MM-yyyy") +File.separator+ filename);
            setPrintWriter(new PrintWriter(file));

            // write a few lines
            printWriter.println("Log created on: " + getCurrentTime("dd-MM-yyyy HH:mm:ss"));
            printWriter.println();
            printWriter.flush(); // use printWriter.flush() instead of printWriter.close()
        } catch (Exception e) {
            System.out.println("Couldn't create file");
        }

    }

    // Write a new line to the log file
    public static void writeLine(String line) {

        try {
            printWriter.println(getCurrentTime("{HH:mm:ss}") + ": " + line);
            printWriter.flush();
        } catch (Exception e) {
            System.out.println("Couldn't write line");
        }

    }

}

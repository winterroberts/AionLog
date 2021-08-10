package net.aionstudios.aionlog;

import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.File;

public class Logger
{
    private static File outFile;
    private static FileOutputStream fos;
    private static PrintStream stream;
    private static int logCountToday;
    private static boolean setup;
    
    static {
        Logger.logCountToday = 1;
        Logger.setup = false;
    }
    
    public static void setup() {
        if (!Logger.setup) {
            Logger.outFile = new File("./logs/" + AnsiOut.getStreamDate() + "-" + Logger.logCountToday + ".log");
            correctFileNaming();
            File logDir = Logger.outFile.getParentFile();
            if (!logDir.exists()) {
            	logDir.mkdirs();
            }
            try {
                Logger.fos = new FileOutputStream(Logger.outFile);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Logger.stream = new PrintStream(Logger.fos);
            Logger.setup = true;
        }
    }
    
    private static void correctFileNaming() {
        if (hasFile()) {
            ++Logger.logCountToday;
            Logger.outFile = new File("./logs/" + AnsiOut.getStreamDate() + "-" + Logger.logCountToday + ".log");
            correctFileNaming();
        }
    }
    
    public static PrintStream getStream() {
        return Logger.stream;
    }
    
    public static boolean hasFile() {
        return Logger.outFile.exists();
    }
}
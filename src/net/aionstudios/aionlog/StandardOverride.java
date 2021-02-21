package net.aionstudios.aionlog;

import java.io.PrintStream;

public class StandardOverride
{
    private static AionConsoleStream serverStream;
    private static AionConsoleErrStream serverErrStream;
    
    public static void enableOverride() {
        StandardOverride.serverStream = new AionConsoleStream();
        StandardOverride.serverErrStream = new AionConsoleErrStream();
        System.setOut(StandardOverride.serverStream);
        System.setErr(StandardOverride.serverErrStream);
    }
}
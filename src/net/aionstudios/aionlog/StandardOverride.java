package net.aionstudios.aionlog;


public class StandardOverride {
	
    private static AionConsoleStream serverStream;
    private static AionConsoleErrStream serverErrStream;
    
    public static void enableOverride() {
        serverStream = new AionConsoleStream();
        serverErrStream = new AionConsoleErrStream();
        System.setOut(serverStream);
        System.setErr(serverErrStream);
    }
    
}
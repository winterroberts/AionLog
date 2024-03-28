package net.winrob.aionlog;

public class StandardOverride {
	
	private static boolean enabled = false;
	
    private static AionConsoleStream outputStream;
    private static AionConsoleErrStream errStream;
    
    public static void enableOverride() {
    	if (enabled) return;
        outputStream = new AionConsoleStream();
        errStream = new AionConsoleErrStream();
        System.setOut(outputStream);
        System.setErr(errStream);
        enabled = true;
    }
    
    public static boolean isEnabled() {
    	return enabled;
    }
    
    public static AionConsoleStream getOutputStream() {
    	return outputStream;
    }
    
    public static AionConsoleErrStream getErrorStream() {
    	return errStream;
    }
    
}
package net.winrob.aionlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.fusesource.jansi.AnsiConsole;

public class AnsiOut {
	
    private static String streamPrefix;
    private static boolean installed;
    private static String r;
    private static String n;
    
    private static SubConsolePrefix scp = null;
    
    static {
        streamPrefix = "";
        installed = false;
        r = "\u001b[0m\u001b[37m";
        n = System.getProperty("line.separator");
    }
    
    public static void initialize() {
        if (!installed) {
            AnsiConsole.systemInstall();
            installed = true;
        }
    }
    
    public static final void println(String ansi) {
        if (installed) {
            if (streamPrefix != "") {
            	String pref2 = "";
            	if(scp!=null) {
            		String pr = scp.makeSubConsolePrefix();
            		if(!pr.equals("")) {
            			pref2 = "["+scp.makeSubConsolePrefix()+"] ";
            		} else {
            			pref2 = "";
            		}
            	}
                ansi = r + "[" + getStreamTime() + " INFO]: [" + streamPrefix + r + "] " + pref2 + ansi;
            }
            else {
                ansi = r + "[" + getStreamTime() + " INFO]: " + ansi;
            }
            AnsiConsole.out().print("\u001b[0m");
            AnsiConsole.out().println(ansi);
            if (Logger.isSetup()) Logger.getStream().println(ansi.replaceAll("\u001b\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", n));
        }
    }
    
    public static final void print(final String ansi) {
        if (installed) {
            AnsiConsole.out().print(ansi);
            if (Logger.isSetup()) Logger.getStream().print(ansi.replaceAll("\u001b\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", n));
        }
    }
    
    public static final void errpl(String ansi) {
        if (installed) {
            ansi = "[" + getStreamTime() + " ERROR]: " + ansi;
            AnsiConsole.err().println(ansi);
            if (Logger.isSetup()) Logger.getStream().println(ansi.replaceAll("\\n", n));
        }
    }
    
    public static final void errp(final String ansi) {
        if (installed) {
            AnsiConsole.err().print(ansi);
            if (Logger.isSetup()) Logger.getStream().print(ansi.replaceAll("\\n", n));
        }
    }
    
    public static void deintialize() {
        if (installed) {
            AnsiConsole.systemUninstall();
            installed = false;
        }
    }
    
    public static boolean isInstalled() {
        return installed;
    }
    
    public static String getStreamTime() {
        final Date dNow = new Date();
        final SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        return ft.format(dNow);
    }
    
    public static String getStreamDate() {
        final Date dNow = new Date();
        final SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(dNow);
    }
    
    public static String getStreamPrefix() {
        return streamPrefix;
    }
    
    public static void setStreamPrefix(final String streamPrefix1) {
        streamPrefix = streamPrefix1;
    }
    
    public static void clearStreamPrefix() {
        streamPrefix = "";
    }
    
    public static void oneTimeSetSCP(SubConsolePrefix scp1) {
    	scp = scp1;
    }
    
}

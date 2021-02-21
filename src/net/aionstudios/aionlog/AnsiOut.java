package net.aionstudios.aionlog;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.fusesource.jansi.AnsiConsole;

public class AnsiOut
{
    private static String streamPrefix;
    private static boolean installed;
    private static Logger logger;
    private static String r;
    private static String n;
    
    private static SubConsolePrefix scp = null;
    
    static {
        AnsiOut.streamPrefix = "";
        AnsiOut.installed = false;
        AnsiOut.r = "\u001b[0m\u001b[37m";
        AnsiOut.n = System.getProperty("line.separator");
    }
    
    public static void initialize() {
        if (!AnsiOut.installed) {
            AnsiConsole.systemInstall();
            setInstalled(true);
        }
    }
    
    public static final void println(String ansi) {
        if (AnsiOut.installed) {
            if (AnsiOut.streamPrefix != "") {
            	String pref2 = "";
            	if(scp!=null) {
            		String pr = scp.makeSubConsolePrefix();
            		if(!pr.equals("")) {
            			pref2 = "["+scp.makeSubConsolePrefix()+"] ";
            		} else {
            			pref2 = "";
            		}
            	}
                ansi = String.valueOf(AnsiOut.r) + "[" + getStreamTime() + " INFO]: [" + AnsiOut.streamPrefix + AnsiOut.r + "] " + pref2 + ansi;
            }
            else {
                ansi = String.valueOf(AnsiOut.r) + "[" + getStreamTime() + " INFO]: " + ansi;
            }
            AnsiConsole.out.print("\u001b[0m");
            AnsiConsole.out.println(ansi);
            Logger.getStream().println(ansi.replaceAll("\u001b\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", AnsiOut.n));
        }
    }
    
    public static final void print(final String ansi) {
        if (AnsiOut.installed) {
            AnsiConsole.out.print(ansi);
            Logger.getStream().print(ansi.replaceAll("\u001b\\[[;\\d]*[ -/]*[@-~]", "").replaceAll("\\n", AnsiOut.n));
        }
    }
    
    public static final void errpl(String ansi) {
        if (AnsiOut.installed) {
            ansi = "[" + getStreamTime() + " ERROR]: " + ansi;
            AnsiConsole.err.println(ansi);
            Logger.getStream().println(ansi.replaceAll("\\n", AnsiOut.n));
        }
    }
    
    public static final void errp(final String ansi) {
        if (AnsiOut.installed) {
            AnsiConsole.err.print(ansi);
            Logger.getStream().print(ansi.replaceAll("\\n", AnsiOut.n));
        }
    }
    
    public static void deintialize() {
        if (AnsiOut.installed) {
            AnsiConsole.systemUninstall();
            setInstalled(false);
        }
    }
    
    public static boolean isInstalled() {
        return AnsiOut.installed;
    }
    
    public static void setInstalled(final boolean installed1) {
        AnsiOut.installed = installed1;
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
        return AnsiOut.streamPrefix;
    }
    
    public static void setStreamPrefix(final String streamPrefix1) {
        AnsiOut.streamPrefix = streamPrefix1;
    }
    
    public static void clearStreamPrefix() {
        AnsiOut.streamPrefix = "";
    }
    
    public static Logger getLogger() {
        return AnsiOut.logger;
    }
    
    public static void setLogger(final Logger logger) {
        AnsiOut.logger = logger;
    }
    
    public static void oneTimeSetSCP(SubConsolePrefix scp1) {
    	scp = scp1;
    }
    
}
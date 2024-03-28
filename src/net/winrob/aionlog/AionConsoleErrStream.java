package net.winrob.aionlog;

import org.fusesource.jansi.AnsiConsole;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class AionConsoleErrStream extends PrintStream {
	
	private Set<StreamListener> listeners;
	private String end;
	
    public AionConsoleErrStream() {
        super(AnsiConsole.err(), true);
        listeners = new HashSet<>();
        end = System.getProperty("line.separator");
    }
    
    public void addStreamListener(StreamListener listener) {
    	listeners.add(listener);
    }
    
    public void removeStreamListener(StreamListener listener) {
    	listeners.remove(listener);
    }
    
    @Override
    public final void print(final String s) {
        AnsiOut.errp(s);
        listeners.parallelStream().forEach((listener) -> listener.onErr(s));
    }
    
    @Override
    public final void println(final String s) {
        AnsiOut.errpl(s);
        listeners.parallelStream().forEach((listener) -> listener.onErr(s + end));
    }
    
    @Override
    public final PrintStream printf(final String s, Object... args) {
    	print(String.format(s, args));
    	return this;
    }
    
    @Override
    public final PrintStream format(final String s, Object... args) {
    	return printf(s, args);
    }
    
}

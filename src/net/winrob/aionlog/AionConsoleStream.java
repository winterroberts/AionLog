package net.winrob.aionlog;

import org.fusesource.jansi.AnsiConsole;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class AionConsoleStream extends PrintStream {
	
	private Set<StreamListener> listeners;
	private String end;
	
    public AionConsoleStream() {
        super(AnsiConsole.out(), true);
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
        AnsiOut.print(s);
        listeners.parallelStream().forEach((listener) -> listener.onOutput(s));
    }
    
    @Override
    public final void println(final String s) {
        AnsiOut.println(s);
        listeners.parallelStream().forEach((listener) -> listener.onOutput(s + end));
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

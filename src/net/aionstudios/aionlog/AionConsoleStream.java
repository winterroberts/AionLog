package net.aionstudios.aionlog;

import java.io.OutputStream;
import org.fusesource.jansi.AnsiConsole;
import java.io.PrintStream;

public class AionConsoleStream extends PrintStream
{
    public AionConsoleStream() {
        super(AnsiConsole.out, true);
    }
    
    @Override
    public final void print(final String s) {
        AnsiOut.print(s);
    }
    
    @Override
    public final void println(final String s) {
        AnsiOut.println(s);
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
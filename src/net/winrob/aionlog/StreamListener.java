package net.winrob.aionlog;

public interface StreamListener {
	
	public void onOutput(String output);
	
	public void onErr(String err);

}

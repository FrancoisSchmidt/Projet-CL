package servPattern;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public interface IProtocole {

	public void execute( IContext aContext , InputStream anInputStream , OutputStream anOutputStream );

	public void executebis(IContext aContext , BufferedReader is , PrintStream os);
	//désolé

	public String getName();
	
}

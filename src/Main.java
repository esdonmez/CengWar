
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import enigma.console.TextAttributes;
import enigma.core.Enigma;

public class Main 
{
	public static enigma.console.Console cn = Enigma.getConsole("CENG-a-WAR",
			120,40,false);
	
	public static void main(String[] args) throws IOException, InterruptedException 
	{
		Game game = new Game();
	}
}

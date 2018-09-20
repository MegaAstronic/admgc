package net.moonj.admgc.util;

import java.io.IOException;
import java.io.PrintStream;

public class CompilerUtils {
	public static void mvnPackage() throws IOException{
		ProcessBuilder pb = new ProcessBuilder("cmd.exe");//打开控制台
		Process proc = pb.start();
		PrintStream procOut = new PrintStream(proc.getOutputStream());
		procOut.println("mvn package");
		procOut.println("exit");
		procOut.flush();
	}
}

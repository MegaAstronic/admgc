package net.moonj.admgc.util;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompilerUtils {
	
	

	private static final Logger logger = LoggerFactory.getLogger(CompilerUtils.class);

	
	public static void mvnPackage() throws IOException{
		
		String osName = System.getProperty("os.name");
		String[] commands = null;
        if (osName.contains("Mac")) {
        	//TODO
        } else if (osName.contains("Windows")) {
        	commands = new String[]{"cmd","/c","mvn package -Dmaven.test.skip=true","exit"};
        } else {
        	commands = new String[]{"/bin/sh","-c","mvn package -Dmaven.test.skip=true","exit"};
        }
		ProcessBuilder pb = new ProcessBuilder(commands);//打开控制台
		Process proc = pb.start();
		handleProcessOutput(proc, logger::debug);//可能接受不完整
//		PrintStream procOut = new PrintStream(proc.getOutputStream());
//		procOut.println("mvn package");
//		procOut.println("exit");
//		procOut.flush();
	}
	
	private static void handleProcessOutput(Process proc, Consumer<String> handle) {
		Runnable run = () -> {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(proc.getInputStream());
			while (proc.isAlive()) {
				while (input.hasNext()) {
					String line = input.nextLine();
					// System.out.println("debug:"+line);
					handle.accept(line);
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(run);
		thread.setDaemon(true);
		thread.start();
	}
}

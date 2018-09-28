package net.moonj.admgc.util;

public class PathUtils {
/**
 * 
 * @param suffixPath 不需要前导/
 * @return
 */
	public static String srcMainResource(String suffixPath){
		String projectPath = System.getProperty("user.dir");
		String srcPath = projectPath+"/src/main/resources/"+suffixPath;
		return srcPath;
	}
}

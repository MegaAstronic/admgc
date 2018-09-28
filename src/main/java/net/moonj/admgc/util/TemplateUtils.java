package net.moonj.admgc.util;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.NullCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;

/**
 * Created by Ay on 2016/7/27.
 */
public class TemplateUtils {

    private TemplateUtils(){}
    private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_22);

    static{
        //这里比较重要，用来指定加载模板所在的路径
        CONFIGURATION.setTemplateLoader(new ClassTemplateLoader(TemplateUtils.class, "/templates"));
        CONFIGURATION.setDefaultEncoding("UTF-8");
        CONFIGURATION.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIGURATION.setCacheStorage(NullCacheStorage.INSTANCE);
    }

    public static Template getTemplate(String templateName) throws IOException {
        try {
            return CONFIGURATION.getTemplate(templateName);
        } catch (IOException e) {
            throw e;
        }
    }
    /**
     * 本方法用于使用ftl同时更新classpath下已有的文件
     * @param templatePath
     * @param model
     * @param targetClasspathpath
     * @throws IOException
     * @throws TemplateException
     */
    public static void classpathSyncProcessUpdate(String templatePath,Map<?, ?> model,String targetClasspathpath) throws IOException, TemplateException{
		File targetPathFile = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + targetClasspathpath);
		Template temp = TemplateUtils.getTemplate(templatePath);
		PrintWriter srcPW = new PrintWriter(new File(PathUtils.srcMainResource(targetClasspathpath)));
		PrintWriter targetPW = new PrintWriter(targetPathFile);
		temp.process(model, srcPW); //源代码
		temp.process(model, targetPW); //target代码
		srcPW.close();
		targetPW.close();
    }
    /**
     * 本方法用于使用ftl创建源码文件，生成后请自行调用mvn package生成target文件
     * @param templatePath
     * @param model
     * @param targetClasspathpath
     * @return 返回生成文件的路径
     * @throws IOException
     * @throws TemplateException
     */
    public static String SrcMainResourceProcess(String templatePath,Map<?, ?> model,String targetClasspathpath) throws IOException, TemplateException{
		Template temp = TemplateUtils.getTemplate(templatePath);
		String srcFilePath = PathUtils.srcMainResource(targetClasspathpath);
		File srcFile = new File(srcFilePath);
		new File(srcFile.getParent()).mkdirs();
		PrintWriter srcPW = new PrintWriter(srcFile);
		temp.process(model, srcPW); //源代码
		srcPW.close();
		return srcFilePath;
    }
    
    public static void clearCache() {
        CONFIGURATION.clearTemplateCache();
    }
}

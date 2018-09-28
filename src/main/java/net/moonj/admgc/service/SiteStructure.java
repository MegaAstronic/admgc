package net.moonj.admgc.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import freemarker.template.TemplateException;
import net.moonj.admgc.util.TemplateUtils;
import net.moonj.admgc.vo.DirNode;
import net.moonj.admgc.vo.LinkNode;

@Component
public class SiteStructure implements InitializingBean {

	private String logo;
	private DirNode sidebarRoot = new DirNode();

	private DirNode navRoot = new DirNode();;

	@Value("${site.config.path}")
	private String urlInClasspath;

	@Value("${site.config.templatePath}")
	private String templatePath ;
	
	private static final Logger logger = LoggerFactory.getLogger(SiteStructure.class);

	private void parseElements(DirNode curDir, List<?> elements) {
		for (Object e : elements) {
			Element x = (Element) e;
			if ("link".equals(x.getName())) {
				LinkNode linkNode = new LinkNode();
				linkNode.setHref(x.attributeValue("href"));
				linkNode.setName(x.attributeValue("name"));
				String icon = x.attributeValue("icon");
				if (icon != null) {
					linkNode.setIconClass(icon);
				}
				curDir.getNodes().add(linkNode);
			}
			if ("dir".equals(x.getName())) {
				DirNode dirNode = new DirNode();
				dirNode.setName(x.attributeValue("name"));
				String icon = x.attributeValue("icon");
				if (icon != null) {
					dirNode.setIconClass(icon);
				}
				String levelStr = x.attributeValue("level");
				if (levelStr != null) {
					dirNode.setLevel(Integer.valueOf(levelStr));
				}
				parseElements(dirNode, x.elements());
				curDir.getNodes().add(dirNode);
			}
		}
	}
/**
 * 将该对象写入xml文件
 * @throws TemplateException
 * @throws IOException
 */
	public void persistence() throws TemplateException, IOException {
		Map<String, Object> model = new HashMap<>();
		TemplateUtils.classpathSyncProcessUpdate(templatePath, modelPut(model), urlInClasspath);
	}





	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public DirNode getSidebarRoot() {
		return sidebarRoot;
	}

	public void setSidebarRoot(DirNode sidebarRoot) {
		this.sidebarRoot = sidebarRoot;
	}

	public DirNode getNavRoot() {
		return navRoot;
	}

	public void setNavRoot(DirNode navRoot) {
		this.navRoot = navRoot;
	}

	public String getUrlInClasspath() {
		return urlInClasspath;
	}

	public void setUrlInClasspath(String urlInClasspath) {
		this.urlInClasspath = urlInClasspath;
	}

	private void parse() throws FileNotFoundException, DocumentException {
		File file = null;
		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + urlInClasspath);
		} catch (FileNotFoundException e) {
			logger.error("sidebarStructure.xml file not found or missing sidebar.path properties in application.yml");
			throw e;
		}
		InputStream is = new FileInputStream(file);
		Document document = null;
		SAXReader saxReader = new SAXReader();
		document = saxReader.read(is); // 读取XML文件,获得document对象
		Element siteE = document.getRootElement();
		Element topnavE = siteE.element("topnav");
		Element sidebarE = siteE.element("sidebar");
		parseElements(sidebarRoot, sidebarE.elements());
		parseElements(navRoot, topnavE.elements());
		logo = document.getRootElement().attributeValue("logo");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		parse();
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String,T> modelPut(Map<String,T> model){
		model.put("topnavDir", (T) this.getNavRoot());
		model.put("sidebarDir", (T) this.getSidebarRoot());
		model.put("logo", (T) this.getLogo());
		return (Map<String, T>) model;
	}
}

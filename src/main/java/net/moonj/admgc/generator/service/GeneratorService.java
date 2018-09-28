package net.moonj.admgc.generator.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import net.moonj.admgc.generator.GeneConfig;
import net.moonj.admgc.service.SiteStructure;
import net.moonj.admgc.util.GeneratedFileManager;
import net.moonj.admgc.util.TemplateUtils;
import net.moonj.admgc.vo.DirNode;
import net.moonj.admgc.vo.LinkNode;

@Service
public class GeneratorService {

	
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;
	@Value("${spring.datasource.url}")
	private String url;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;

	@Resource
	private GeneratedFileManager generatedFileManager;
	
	@Resource
	private SiteStructure siteStructure;
	
	private String parentPackage = "net.moonj.admgc.genecode";
	private String viewTargetPath = "templates/genecode/";
	
	public void generate(GeneConfig config) throws Exception{
		List<String> generatedFile = new ArrayList<>();
		generatedFile.addAll(customGenerate(config));
		generatedFile.addAll(mybatisplusGenerate(config));
		generatedFileManager.addGeneratedFile(config.getEntityName(), generatedFile);
		
		if(config.getAddToSidebar()){
			LinkNode link = new LinkNode();
			link.setName("查询"+config.getEntityName());
			link.setHref("/pages/"+config.getTableName()+"/query");
			siteStructure.getSidebarRoot().getNodes().add(link);
			siteStructure.save();
		}
		
	}
	private List<String> customGenerate(GeneConfig config) throws Exception{
		
		List<String> generatedFile = new ArrayList<>();
		
		Map<String,Object> model = new LinkedHashMap<>();
		model.put("config", config);
		config.setEntityName(underlineToCamel(config.getTableName()));
		config.setQueryColumnsNamingMap(config.getColumnMsg().keySet().stream().collect(Collectors.toMap(k->k, this::underlineToCamel)));
		//TODO
        //query
		generatedFile.add(TemplateUtils.SrcMainResourceProcess("/geneMod/temp/query.ftl.ftl", model, viewTargetPath+config.getTableName()+"/query.ftl"));
		//insert
		generatedFile.add(TemplateUtils.SrcMainResourceProcess("/geneMod/temp/insert.ftl.ftl", model, viewTargetPath+config.getTableName()+"/insert.ftl"));
        //update
		generatedFile.add(TemplateUtils.SrcMainResourceProcess("/geneMod/temp/update.ftl.ftl", model, viewTargetPath+config.getTableName()+"/update.ftl"));

		return generatedFile;
    }
	
	private String underlineToCamel(String e){
		String[] part = e.split("_");
		StringBuffer sb = new StringBuffer(part[0]);
		for(int i = 1;i<part.length;i++){
			sb.append(upperCap(part[i]));
		}
		return sb.toString();
	}
	
	private String upperCap(String str){
		return str.substring(0, 1).toUpperCase()+str.substring(1);
	}
	
	private List<String> mybatisplusGenerate(GeneConfig config) throws Exception{
		// 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("admgc");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(driverClassName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(config.getTableName());
        pc.setParent(parentPackage);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mapper/genecode/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        
        TemplateConfig tempConfig  = new TemplateConfig().setXml(null).setController("/templates/geneMod/temp/Controller.java");
        
        mpg.setTemplate(tempConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
        strategy.setEntityLombokModel(false);
        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
        strategy.setInclude(config.getTableName());
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        GeneratorMybatisPlusEngine engine = new GeneratorMybatisPlusEngine();
        mpg.setTemplateEngine(engine);
        mpg.execute();
        return engine.getGeneratedFile();
	}
}

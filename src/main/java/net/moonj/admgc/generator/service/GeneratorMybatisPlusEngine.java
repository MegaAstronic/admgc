package net.moonj.admgc.generator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class GeneratorMybatisPlusEngine extends FreemarkerTemplateEngine{
	
	private List<String> generatedFile = new ArrayList<>();
	
	

	private static final Logger logger = LoggerFactory.getLogger(GeneratorMybatisPlusEngine.class);

	
	@Override
	public void writer(Map<String, Object> objectMap, String templatePath, String outputFile) throws Exception {
		super.writer(objectMap, templatePath, outputFile);
		generatedFile.add(outputFile);
	}
	
	public List<String> getGeneratedFile() {
		return generatedFile;
	}
	
}

package net.moonj.admgc;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.moonj.admgc.generator.GeneConfig;
import net.moonj.admgc.service.GeneratorService;
import net.moonj.admgc.util.PathUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdmgcApplicationTests {

	@Test
	public void contextLoads() {
		
	}
	@Resource
	private GeneratorService generatorService;
	@Test
	public void generateFile() throws Exception {
		ObjectMapper map =  new ObjectMapper();
		GeneConfig config = map.readValue(new File(PathUtils.srcMainResource("test/geneConfig.json")), 	GeneConfig.class);
		generatorService.generate(config);
	}

}

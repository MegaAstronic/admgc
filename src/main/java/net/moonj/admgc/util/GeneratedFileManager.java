package net.moonj.admgc.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GeneratedFileManager {

	private Map<String, List<String>> moduleFilesMap = new LinkedHashMap<>();
	private ObjectMapper jsonMap = new ObjectMapper();
	private final static String jsonpath = "generatedFile.json";
	private File jsonFile = new File(PathUtils.srcMainResource(jsonpath));
	public GeneratedFileManager() throws JsonParseException, JsonMappingException, IOException {
		jsonMap.readValue(jsonFile, new TypeReference<Map<String, List<String>>>() {
		});
	}

	public void addGeneratedFile(String moduleName, List<String> files) throws Exception {
		moduleFilesMap.put(moduleName, files);
		save();
	}
	
	public boolean removeModule(String moduleName) throws Exception{
		List<String> paths = moduleFilesMap.get(moduleName);
		if(paths!=null){
			for(String path : paths){
				new File(path).delete();
			}
			List<String> removedFilePath = moduleFilesMap.remove(moduleName);
			try{
				save();
				return true;
			}catch(Exception e){
				moduleFilesMap.put(moduleName, removedFilePath);
				throw e;
			}
		}
		return false;
	}
	
	private void save() throws Exception{
		jsonMap.writeValue(jsonFile,moduleFilesMap);
	}
}

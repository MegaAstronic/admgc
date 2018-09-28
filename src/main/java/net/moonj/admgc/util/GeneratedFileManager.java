package net.moonj.admgc.util;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GeneratedFileManager {

	
	

	private static final Logger logger = LoggerFactory.getLogger(GeneratedFileManager.class);

	
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
			File file = null;
			Set<String> folderSet = new LinkedHashSet<>();
			for(String path : paths){
				file = new File(path);
				file.delete();
				if(file!=null){
					File temp = null;
					temp = file;
					while(!(temp = temp.getParentFile()).getName().equals(moduleName.toLowerCase())){
						if(temp.isDirectory()){
							folderSet.add(temp.getAbsolutePath());
						}
					}
					if(temp.getName().equals(moduleName.toLowerCase())){
						if(temp.isDirectory()){
							folderSet.add(temp.getAbsolutePath());
						}
					}
				}
			
			}
			folderSet.stream().sorted((x,y)->{
				return Integer.compare(y.length(), x.length());
			}).forEach(folderPath->{
				new File(folderPath).delete();
				logger.info("delete folder : "+folderPath);
			});
			

			List<String> removedFilePath = moduleFilesMap.remove(moduleName);
			try{
				save();
				logger.info("remove generated module : "+moduleName);
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
	
	public Map<String, List<String>> getModuleFilesMap() {
		return moduleFilesMap;
	}
	
}

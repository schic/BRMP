package com.wondersgroup.base.login.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ConfigManager {

	private static final String configFileName = "config.json";
	private JSONObject jsonConfig = null;
	private static ConfigManager configManager;

	private ConfigManager() throws FileNotFoundException, IOException {
		String configContent = this.readFile(configFileName);

		try {
			JSONObject jsonConfig = JSONObject.fromObject(configContent);
			this.jsonConfig = jsonConfig;
		} catch (Exception e) {
			e.printStackTrace();
			this.jsonConfig = null;
		}
	}

	public static ConfigManager getInstance() {
		try {
			if (configManager == null) {
				configManager = new ConfigManager();
			}
			return configManager;
		} catch (Exception e) {
			return null;
		}

	}

	public JSONObject getAllConfig() {

		return this.jsonConfig;

	}

	public Map<String, Object> getConfig(String key) {

		Map<String, Object> conf = new HashMap<String, Object>();
		String rootPath = this.jsonConfig.getString("ROOT_PATH");
		String savePath = null;

		if (this.jsonConfig.containsKey(key)) {
			JSONObject key_config = this.jsonConfig.getJSONObject(key);
			conf.put("maxSize", key_config.getLong("fileMaxSize"));
			conf.put("allowFiles", getArray(key_config.getJSONArray("fileAllowFiles")));
			savePath = key_config.getString("filePathFormat");
		}

		conf.put("savePath", savePath);
		conf.put("rootPath", rootPath);

		return conf;

	}

	private String[] getArray(JSONArray jsonArray) {

		String[] result = new String[jsonArray.size()];

		for (int i = 0, len = jsonArray.size(); i < len; i++) {
			result[i] = jsonArray.getString(i);
		}

		return result;

	}

	private String readFile(String path) throws IOException {
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		StringBuilder builder = new StringBuilder();

		try {
			Resource resource = resourceLoader.getResource(path);
			InputStreamReader reader = new InputStreamReader(resource.getInputStream(), "UTF-8");
			BufferedReader bfReader = new BufferedReader(reader);

			String tmpContent = null;

			while ((tmpContent = bfReader.readLine()) != null) {
				builder.append(tmpContent);
			}

			bfReader.close();

		} catch (Exception e) {
			e.printStackTrace();
			// 忽略
		}
		return this.filter(builder.toString());

	}

	// 过滤输入字符串, 剔除多行注释以及替换掉反斜杠
	private String filter(String input) {

		return input.replaceAll("/\\*[\\s\\S]*?\\*/", "");

	}
}

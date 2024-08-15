package mudkipboy7.alien;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.toml.TomlParser;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AMFileMethods {
	public static JsonObject getJson(String filename) {
		InputStream inputStream = AlienMod.class.getClassLoader().getResourceAsStream(filename);
		BufferedReader jsonReader = null;
		try {
			jsonReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		JsonObject jsonObject = (JsonObject) JsonParser.parseReader(jsonReader);
		System.out.println(jsonObject);
		return jsonObject;
	}

	public static CommentedConfig getTOML(String filename) {
		TomlParser tomlParser = new TomlParser();
		InputStream inputStream = AlienMod.class.getClassLoader().getResourceAsStream(filename);
		return tomlParser.parse(inputStream);
	}
	/*
	 * public static HashMap<String, Object> getYamlAsJson(String filename) {
	 * InputStream inputStream =
	 * AlienMod.class.getClassLoader().getResourceAsStream(filename);
	 * HashMap<String, Object> map = new Yaml().load(inputStream); return map; }
	 */
}

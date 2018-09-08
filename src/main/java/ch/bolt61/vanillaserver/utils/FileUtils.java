package ch.bolt61.vanillaserver.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtils {

	public static void copyFromResources(Class<?> remote, String path, File file, boolean override) throws IOException {
		file.getParentFile().mkdirs();
		if(file.exists()) {
			if(override) {
				file.delete();
			}
		} else {
			file.createNewFile();
		}
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		InputStream input = remote.getResourceAsStream(path);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line;
		while((line = br.readLine()) != null) {
			writer.write(line);
			writer.newLine();
		}
		br.close();
		writer.close();
	}
}

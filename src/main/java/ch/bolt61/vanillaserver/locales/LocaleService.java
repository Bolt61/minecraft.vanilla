package ch.bolt61.vanillaserver.locales;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ch.bolt61.vanillaserver.utils.FileUtils;
import net.md_5.bungee.api.ChatColor;

public class LocaleService {
	
	private static final String FOLDER = "locales";
	private static final String FILE_NAME = "{0}.yml";
	private static final String RESOURCE_PATH = "/" + FOLDER + "/" + FILE_NAME;
	private static final String ERROR = "§cError in locale-system, please contact administrator";
	private static final boolean OVERRIDE = true;
	
	private final JavaPlugin plugin;
	private final Map<LanguageEnum, File> languageFiles;
	
	public LocaleService(JavaPlugin plugin) {
		this.languageFiles = new HashMap<LanguageEnum, File>();
		this.plugin = plugin;
	}

	public void loadLanguageFiles() {
		for(LanguageEnum l : LanguageEnum.values()) {
			try {
				File file = new File(plugin.getDataFolder() + File.separator + FOLDER, MessageFormat.format(FILE_NAME, l.getCode()));
				FileUtils.copyFromResources(MessageFormat.format(RESOURCE_PATH, l.getCode()), file, OVERRIDE);
				languageFiles.put(l, file);
				plugin.getLogger().log(Level.INFO, MessageFormat.format("Created locale-file " + FILE_NAME, l.getCode()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getMessage(String path, LanguageEnum e) {
	  File file = languageFiles.get(e);
		if(file != null) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
			String msg = cfg.getString(path);
			if(msg != null) {
				msg = msg.replace("ae", "ä");
				msg = msg.replace("oe", "ö");
				msg = msg.replace("ue", "ü");
				msg = msg.replace("Ae", "Ä");
				msg = msg.replace("Oe", "Ö");
				msg = msg.replace("Ue", "ü");
				return ChatColor.translateAlternateColorCodes('&', msg);
			} else {
			  plugin.getLogger().log(Level.WARNING, "Message for path:'" + path + "' of language " + e.getLabel() + " not found");
			}
		} else {
		  plugin.getLogger().log(Level.SEVERE, "No Language file found for language " + e.getLabel());
		}
		return ERROR;
	}
}

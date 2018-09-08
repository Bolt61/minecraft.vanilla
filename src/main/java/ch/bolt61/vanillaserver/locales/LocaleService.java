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
	private static final String LOG_LOCALE_GENERATED = "Created locale-file " + FILE_NAME;
	private static final boolean OVERRIDE = true;
	
	private final Map<LanguageEnum, File> languageFiles;

	public LocaleService() {
		this.languageFiles = new HashMap<LanguageEnum, File>();
	}

	public void loadLanguageFiles(JavaPlugin plugin) {
		for(LanguageEnum l : LanguageEnum.values()) {
			try {
				File file = new File(plugin.getDataFolder() + File.separator + FOLDER, MessageFormat.format(FILE_NAME, l.getCode()));
				FileUtils.copyFromResources(getClass(), MessageFormat.format(RESOURCE_PATH, l.getCode()), file, OVERRIDE);
				languageFiles.put(l, file);
				plugin.getLogger().log(Level.INFO, MessageFormat.format(LOG_LOCALE_GENERATED, l.getCode()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getMessage(String path, LanguageEnum e) {
		if(!languageFiles.isEmpty()) {
			YamlConfiguration cfg = YamlConfiguration.loadConfiguration(languageFiles.get(e));
			String msg = cfg.getString(path);
			if(msg != null) {
				msg = msg.replace("ae", "ä");
				msg = msg.replace("oe", "ö");
				msg = msg.replace("ue", "ü");
				return ChatColor.translateAlternateColorCodes('&', msg);
			}
		}
		//TODO log error
		return ERROR;
	}
}

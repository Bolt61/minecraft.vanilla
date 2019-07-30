package ch.bolt61.vanillaserver;

import java.util.List;
import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.bolt61.vanillaserver.events.PlayerJoin;
import ch.bolt61.vanillaserver.locales.LocaleService;
import ch.bolt61.vanillaserver.location.SetCommand;
import ch.bolt61.vanillaserver.location.LocationService;
import ch.bolt61.vanillaserver.utils.CommandsHelper;

public class Main extends JavaPlugin {
	
	private static final String DELETED_COMMANDS_PATH = "deleted_commands";
	private static final String DELETED_PERMISSIONS_PATH = "deleted_permissions";
	private static final String NOT_DELETED_COMMANDS_PATH = "not_deleted_commands";
	
	private LocaleService localeService;
	private LocationService locationService;
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		getLogger().log(Level.INFO, "Loading locale files..");
		localeService = new LocaleService(this);
		localeService.loadLanguageFiles();
		getLogger().log(Level.INFO, "Loaded all locale files");
		
		locationService = new LocationService(this);
		getCommand("set").setExecutor(new SetCommand(locationService));
		
		getLogger().log(Level.INFO, "Loading configuration file..");
		loadConfig();
		getLogger().log(Level.INFO, "Loaded configuration file");
		
		getLogger().log(Level.INFO, "Registering event listeners..");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(localeService), this);
		getLogger().log(Level.INFO, "Registered all event listeners");
		
		getLogger().log(Level.INFO, "Deleting all unnecessary commands");
		List<String> deletedCommands = getConfig().getStringList(DELETED_COMMANDS_PATH);
		List<String> deletedPermissions = getConfig().getStringList(DELETED_PERMISSIONS_PATH);
		List<String> notDeletedCommands = getConfig().getStringList(NOT_DELETED_COMMANDS_PATH);
		CommandsHelper.clearCommands(this, deletedCommands, deletedPermissions, notDeletedCommands);
		
		getLogger().log(Level.INFO, "Plugin enabled");
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		getLogger().log(Level.INFO, "Plugin disabled");
	}
	
	private void loadConfig() {
		saveDefaultConfig();
	}
}

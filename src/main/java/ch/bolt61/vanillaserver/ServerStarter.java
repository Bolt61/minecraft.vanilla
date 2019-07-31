package ch.bolt61.vanillaserver;

import java.util.logging.Level;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import ch.bolt61.vanillaserver.events.PlayerJoin;
import ch.bolt61.vanillaserver.events.PlayerQuit;
import ch.bolt61.vanillaserver.locales.LocaleService;
import ch.bolt61.vanillaserver.location.LocationService;
import ch.bolt61.vanillaserver.location.PositionsCommand;
import ch.bolt61.vanillaserver.location.RemoveCommand;
import ch.bolt61.vanillaserver.location.SetCommand;
import ch.bolt61.vanillaserver.permissions.PermissionService;
import ch.bolt61.vanillaserver.statistics.StatisticsCommand;
import ch.bolt61.vanillaserver.statistics.StatisticsService;
import ch.bolt61.vanillaserver.utils.CommandsHelper;

public class ServerStarter extends JavaPlugin {
	
	private LocaleService localeService;
	private LocationService locationService;
	private PermissionService permissionService;
	private StatisticsService statisticsService;
	
	@Override
	public void onLoad() {
	   getLogger().log(Level.INFO, "Deleting all unnecessary commands");
	   CommandsHelper.removeCommands(this);
	}
	
	@Override
	public void onEnable() {
		getLogger().log(Level.INFO, "Loading locale files..");
		localeService = new LocaleService(this);
		localeService.loadLanguageFiles();
		getLogger().log(Level.INFO, "Loaded all locale files");
		
    permissionService = new PermissionService(this);
    
    statisticsService = new StatisticsService(this);
		
		locationService = new LocationService(this);
		getCommand("set").setExecutor(new SetCommand(locationService));
    getCommand("positions").setExecutor(new PositionsCommand(locationService));
    getCommand("remove").setExecutor(new RemoveCommand(locationService));
    getCommand("statistics").setExecutor(new StatisticsCommand(statisticsService));
		
		getLogger().log(Level.INFO, "Loading configuration file..");
		loadConfig();
		getLogger().log(Level.INFO, "Loaded configuration file");
		
		getLogger().log(Level.INFO, "Registering event listeners..");
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerJoin(permissionService, localeService, statisticsService), this);
    pm.registerEvents(new PlayerQuit(permissionService, localeService, statisticsService), this);
		getLogger().log(Level.INFO, "Registered all event listeners");
		
		getLogger().log(Level.INFO, "Plugin enabled");
	}
	
	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "Plugin disabled");
	}
	
	private void loadConfig() {
		saveDefaultConfig();
	}
}

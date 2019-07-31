package ch.bolt61.vanillaserver.statistics;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class StatisticsService {
  
  private static final String FOLDER = "statistics";
  private static final String FILE_NAME = "{0}.yml";
  private static final String SECTION = "statistics";
  
  private final JavaPlugin plugin;

  private Map<UUID, PlayerStatistics> playerStatistics = new HashMap<>();
  
  public StatisticsService(JavaPlugin plugin) {
    this.plugin = plugin;
  }
  
  public void loadStatistics(Player player) {
    final UUID uuid = player.getUniqueId();
    File file = new File(plugin.getDataFolder() + File.separator + FOLDER, MessageFormat.format(FILE_NAME, uuid.toString()));
    if(!file.exists()) {
      try {
        file.createNewFile();
        plugin.getLogger().log(Level.INFO, "Created new statistics file for player " + player.getDisplayName());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
    if(!config.isConfigurationSection(SECTION)) {
      config.createSection(SECTION);
    }
    final String path = SECTION + ".";
    for(StatisticsType type : StatisticsType.values()) {
      config.addDefault(path + type.getName(), type.getDefaultValue());
    }
    try {
      config.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
    plugin.getLogger().log(Level.INFO, "Loaded statistics for player " + player.getDisplayName());
    playerStatistics.put(uuid, new PlayerStatistics(file, path).load());
  }
  
  public void saveAll() {
    for(UUID uuid : playerStatistics.keySet()) {
      PlayerStatistics statistics = playerStatistics.get(uuid);
      statistics.save();
    }
  }
  
  public PlayerStatistics getStatistics(Player player) {
    return playerStatistics.get(player.getUniqueId());
  }

  public void saveStatistics(Player p) {
    PlayerStatistics statistics = playerStatistics.get(p.getUniqueId());
    if(statistics != null) {
      statistics.save();
    } else {
      plugin.getLogger().log(Level.SEVERE, "Failed to save statistics of player " + p.getDisplayName());
    }
  }
}

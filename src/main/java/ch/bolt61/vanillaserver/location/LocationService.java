package ch.bolt61.vanillaserver.location;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LocationService {
  
  private static final String FILE_NAME = "locs.yml";
  
  private final File file;
  private final YamlConfiguration configuration;
  
  public LocationService(JavaPlugin plugin) {
    this.file = new File(plugin.getDataFolder(), FILE_NAME);
    if(!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    this.configuration = YamlConfiguration.loadConfiguration(file);
  }

  public void setLocation(String name, VanillaLocation loc) {
    configuration.set(name, loc.toString());
    saveFile();
  }
  
  public boolean exists(String name) {
    return configuration.contains(name);
  }
  
  public boolean delete(String name) {
    if(configuration.contains(name)) {
      configuration.set(name, null);
      saveFile();
      return true;
    }
    return false;
  }
  
  public Set<String> getAllLocationNames() {
    ConfigurationSection section = configuration.getConfigurationSection("");
    return section.getValues(false).keySet();
  }
  
  public VanillaLocation getLocation(String name) {
    if(configuration.contains(name)) {
      return VanillaLocation.fromString(configuration.getString(name));
    }
    return null;
  }
  
  private void saveFile() {
    try {
      configuration.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
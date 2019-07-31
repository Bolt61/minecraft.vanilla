package ch.bolt61.vanillaserver.statistics;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.bukkit.configuration.file.YamlConfiguration;

public class PlayerStatistics {
  
  private File file;
  private YamlConfiguration config;
  private String path;

  private long logins;
  private long blocksBuilt;
  private long deaths;
  private Date lastLogin;

  public PlayerStatistics(File file, String path) {
    this.file = file;
    this.path = path;
    config = YamlConfiguration.loadConfiguration(file);
  }
  
  public PlayerStatistics load() {
    logins = config.getLong(path + StatisticsType.LOGINS);
    blocksBuilt = config.getLong(path + StatisticsType.BLOCKS_BUILT);
    deaths = config.getLong(path + StatisticsType.DEATHS);
    lastLogin = new Date(config.getLong(path + StatisticsType.LAST_LOGIN));
    return this;
  }
  
  public void save() {
    config.set(path + StatisticsType.LOGINS, logins);
    config.set(path + StatisticsType.BLOCKS_BUILT, blocksBuilt);
    config.set(path + StatisticsType.DEATHS, deaths);
    config.set(path + StatisticsType.LAST_LOGIN, lastLogin.getTime());
    try {
      config.save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public long getLogins() {
    return logins;
  }
  
  public void addLogin() {
    logins++;
  }
  
  public long getBlocksBuilt() {
    return blocksBuilt;
  }
  
  public void addBlockBuilt() {
    blocksBuilt++;
  }
  
  public long getDeaths() {
    return deaths;
  }
  
  public void addDeath() {
    deaths++;
  }
  
  public Date getLastLogin() {
    return lastLogin;
  }
  
  public void setLastLogin(Date lastLogin) {
    this.lastLogin = lastLogin;
  }
}

package ch.bolt61.vanillaserver.statistics;

public enum StatisticsType {
  
  LOGINS("logins", 0),
  BLOCKS_BUILT("buildblocks", 0),
  DEATHS("deaths", 0),
  LAST_LOGIN("lastlogin", 0);
  
  private String name;
  private Object defaultValue;
  
  private StatisticsType(String name, Object defaultValue) {
    this.name = name;
    this.defaultValue = defaultValue;
  }
  
  public String getName() {
    return name;
  }
  
  public Object getDefaultValue() {
    return defaultValue;
  }
}

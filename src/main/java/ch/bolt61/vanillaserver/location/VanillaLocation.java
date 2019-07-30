package ch.bolt61.vanillaserver.location;

import org.bukkit.Location;

public class VanillaLocation {
  
  private static final String SPLIT_REGEX = "|";
  
  private String world;
  private int x;
  private int y;
  private int z;
  
  public VanillaLocation(String world, int x, int y, int z) {
    this.world = world;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public String getWorld() {
    return world;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
  
  public int getZ() {
    return z;
  }
  
  @Override
  public String toString() {
    return world + SPLIT_REGEX + x + SPLIT_REGEX + y + SPLIT_REGEX + z;
  }
  
  public static VanillaLocation fromLocation(Location loc) {
    return new VanillaLocation(loc.getWorld().getName(), loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
  }
  
  public static VanillaLocation fromString(String location) {
    String[] split = location.split(SPLIT_REGEX);
    if(split.length == 4) {
      String world = split[0];
      int x = Integer.parseInt(split[1]);
      int y = Integer.parseInt(split[2]);
      int z = Integer.parseInt(split[3]);
      return new VanillaLocation(world, x, y, z);
    }
    return null;
  }
}

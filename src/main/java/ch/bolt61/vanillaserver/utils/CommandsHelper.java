package ch.bolt61.vanillaserver.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandsHelper {
  
  /**
   * Removes all known commands from the server.
   * 
   * @param plugin
   */
  public static void removeCommands(JavaPlugin plugin) {
    final SimplePluginManager manager = (SimplePluginManager) plugin.getServer().getPluginManager();
    try {
      /* class level */
      final Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
      commandMapField.setAccessible(true);
      /* object level */
      CommandMap map = (CommandMap) commandMapField.get(manager);
      
      
      final Field knownCommandsField = SimpleCommandMap.class.getDeclaredField("knownCommands");
      knownCommandsField.setAccessible(true);
      
      @SuppressWarnings("unchecked")
      final Map<String, Command> knownCommands = (Map<String, Command>) knownCommandsField.get(map);
      for (final Map.Entry<String, Command> entry : knownCommands.entrySet()) {
        plugin.getLogger().log(Level.INFO, "Remove command: " + entry.getKey());
      }
      knownCommandsField.set(map, new HashMap<>());
    } catch (IllegalArgumentException | NoSuchFieldException | IllegalAccessException | SecurityException e) {
      e.printStackTrace();
    }
  }
}

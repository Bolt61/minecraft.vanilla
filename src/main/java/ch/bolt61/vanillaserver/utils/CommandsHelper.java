package ch.bolt61.vanillaserver.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CommandsHelper {

	public static void clearCommands(final JavaPlugin plugin, final List<String> deletedCommands, List<String> deletedPermissions, List<String> notDeletedCommands){
		try {
			Field f = getCommandMap().getClass().getDeclaredField("knownCommands");
			f.setAccessible(true);
			final Object list = f.get(getCommandMap());
			new BukkitRunnable() {
				@SuppressWarnings("unchecked")
				public void run() {
					Map<String, Command> commands = (Map<String, Command>) list;
					List<Command> deleted = new ArrayList<Command>();
					for(Command cmd : commands.values()) {
						if(deletedCommands.contains(cmd.getName().toLowerCase())) {
							deleted.add(cmd);
						} else {
							deletedPermissions.forEach(perm -> {
								if(cmd.getPermission().startsWith(perm)) {
									deleted.add(cmd);
								}
							});
						}
					}
					for(Command c : deleted) {
						if(!notDeletedCommands.contains(c.getName().toLowerCase())) {
							commands.values().remove(c);
						}
					}
				}
			}.runTaskLater(plugin, 1);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	private static CommandMap getCommandMap(){
		try {
			Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
			f.setAccessible(true);
			CommandMap map = (CommandMap) f.get(Bukkit.getServer());
			return map;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}

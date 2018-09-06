package ch.bolt61.vanillaserver;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		super.onEnable();
		System.out.println("Plugin successfully enabled");
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
		System.out.println("Plugin successfully disabled");
	}
}

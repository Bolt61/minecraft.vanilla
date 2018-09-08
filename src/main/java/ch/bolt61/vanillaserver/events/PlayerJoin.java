package ch.bolt61.vanillaserver.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.bolt61.vanillaserver.Main;
import ch.bolt61.vanillaserver.locales.LanguageEnum;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		
		Player p = e.getPlayer();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(Main.getInstance().getLocaleService().getMessage("events.join", LanguageEnum.GERMAN)
					.replace("<player>", p.getDisplayName()));
		}
	}
}

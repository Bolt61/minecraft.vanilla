package ch.bolt61.vanillaserver.events;

import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import ch.bolt61.vanillaserver.locales.LanguageEnum;
import ch.bolt61.vanillaserver.locales.LocaleService;
import ch.bolt61.vanillaserver.permissions.PermissionService;
import ch.bolt61.vanillaserver.statistics.PlayerStatistics;
import ch.bolt61.vanillaserver.statistics.StatisticsService;

public class PlayerJoin implements Listener {
  
  private PermissionService permissionService;
  private LocaleService localeService;
  private StatisticsService statisticsService;
  
  public PlayerJoin(PermissionService permissionService, LocaleService localeService, StatisticsService statisticsService) {
    this.permissionService = permissionService;
    this.localeService = localeService;
    this.statisticsService = statisticsService;
  }

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		
		Player p = e.getPlayer();
		
		for(Player all : Bukkit.getOnlinePlayers()) {
			all.sendMessage(localeService.getMessage("events.join", LanguageEnum.GERMAN).replace("<player>", p.getDisplayName()));
		}
		
		permissionService.addPlayer(p);
		
		PlayerStatistics playerStatistics = statisticsService.getStatistics(p);
		playerStatistics.setLastLogin(new Date(System.currentTimeMillis()));
		playerStatistics.addLogin();
	}
}

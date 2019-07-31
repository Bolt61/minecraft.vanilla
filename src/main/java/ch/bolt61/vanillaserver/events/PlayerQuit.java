package ch.bolt61.vanillaserver.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ch.bolt61.vanillaserver.locales.LanguageEnum;
import ch.bolt61.vanillaserver.locales.LocaleService;
import ch.bolt61.vanillaserver.permissions.PermissionService;
import ch.bolt61.vanillaserver.statistics.StatisticsService;

public class PlayerQuit implements Listener {

  private PermissionService permissionService;
  private LocaleService localeService;
  private StatisticsService statisticsService;
  
  public PlayerQuit(PermissionService permissionService, LocaleService localeService, StatisticsService statisticsService) {
    this.permissionService = permissionService;
    this.localeService = localeService;
    this.statisticsService = statisticsService;
  }

  @EventHandler
  public void onQuit(PlayerQuitEvent e) {
    e.setQuitMessage(null);
    
    Player p = e.getPlayer();
    
    for(Player all : Bukkit.getOnlinePlayers()) {
      all.sendMessage(localeService.getMessage("events.quit", LanguageEnum.GERMAN).replace("<player>", p.getDisplayName()));
    }
    
    permissionService.removePlayer(p);
    
    statisticsService.saveStatistics(p);
  }
}

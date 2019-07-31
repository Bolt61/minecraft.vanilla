package ch.bolt61.vanillaserver.events;

import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.bolt61.vanillaserver.locales.LanguageEnum;
import ch.bolt61.vanillaserver.locales.LocaleService;
import ch.bolt61.vanillaserver.statistics.PlayerStatistics;
import ch.bolt61.vanillaserver.statistics.StatisticsService;

public class PlayerDeath implements EventListener {
  
  private LocaleService localeService;
  private StatisticsService statisticsService;
  
  public PlayerDeath(LocaleService localeService, StatisticsService statisticsService) {
    this.localeService = localeService;
    this.statisticsService = statisticsService;
  }

  @EventHandler
  public void onDeath(PlayerDeathEvent e) {
    Player p = e.getEntity();
    Location loc = p.getLocation();
    
    for(Player all : Bukkit.getOnlinePlayers()) {
      all.sendMessage(localeService.getMessage("events.death", LanguageEnum.GERMAN)
          .replace("<player>", p.getDisplayName()
          .replace("<world>", loc.getWorld().getName())
          .replace("<x>", String.valueOf(loc.getBlockX()))
          .replace("<y>", String.valueOf(loc.getBlockY()))
          .replace("<z>", String.valueOf(loc.getBlockZ()))));
    }
    
    PlayerStatistics statistics = statisticsService.getStatistics(p);
    statistics.addDeath();
  }
}

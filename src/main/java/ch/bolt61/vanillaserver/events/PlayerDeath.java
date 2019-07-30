package ch.bolt61.vanillaserver.events;

import java.util.EventListener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import ch.bolt61.vanillaserver.locales.LanguageEnum;
import ch.bolt61.vanillaserver.locales.LocaleService;

public class PlayerDeath implements EventListener {
  
  private LocaleService localeService;
  
  public PlayerDeath(LocaleService localeService) {
    this.localeService = localeService;
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
  }
}

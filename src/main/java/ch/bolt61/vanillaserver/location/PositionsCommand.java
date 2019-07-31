package ch.bolt61.vanillaserver.location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PositionsCommand implements CommandExecutor {
  
  private LocationService locationService;
  
  public PositionsCommand(LocationService locationService) {
    this.locationService = locationService;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    sender.sendMessage("§7======== §ePositionen §7========");
    for(String name : locationService.getAllLocationNames()) {
      sender.sendMessage(" §7-§e" + name);
    }
    return true;
  }
}

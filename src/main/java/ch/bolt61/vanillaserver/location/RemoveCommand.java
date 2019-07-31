package ch.bolt61.vanillaserver.location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RemoveCommand implements CommandExecutor {

  private LocationService locationService;
  
  public RemoveCommand(LocationService locationService) {
    this.locationService = locationService;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    if(args.length == 1) {
      String name = args[1];
      if(locationService.exists(name)) {
        locationService.delete(name);
        sender.sendMessage("§aPosition §e" + name + " §awurde erfolgreich gelöscht");
      } else {
        sender.sendMessage("§cDiese Position existiert nicht");
      }
    } else {
      sender.sendMessage(" §7Benutze: §e/remove <position>");
    }
    
    return true;
  }
}

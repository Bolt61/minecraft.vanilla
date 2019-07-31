package ch.bolt61.vanillaserver.location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {
  
  private LocationService locationService;
  
  public SetCommand(LocationService locationService) {
    this.locationService = locationService;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    if(args.length == 1) {
      if(sender instanceof Player) {
        String name = args[1];
        if(locationService.exists(name)) {
          sender.sendMessage("§cDiese Position existiert bereits");
        } else {
          Player p = (Player) sender;
          locationService.setLocation(name, VanillaLocation.fromLocation(p.getLocation()));
          sender.sendMessage("§aPosition §e" + name + " §awurde gespeichert");
        }
      } else {
        sender.sendMessage("This command is not for console use");
      }
    } else {
      sender.sendMessage(" §7Benutze: §e/set <position>");
    }
    
    return true;
  }
}

package ch.bolt61.vanillaserver.statistics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ch.bolt61.vanillaserver.utils.DateUtils;

public class StatisticsCommand implements CommandExecutor {
  
  private StatisticsService statisitcsService;
  
  public StatisticsCommand(StatisticsService statisticsService) {
    this.statisitcsService = statisticsService;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if(sender instanceof Player) {
      Player player = (Player) sender;
      PlayerStatistics statistics = statisitcsService.getStatistics(player);
      sender.sendMessage("§7======== §eStatistik für " + player.getDisplayName() + " §7========");
      sender.sendMessage(" §7-§eLogins: §a" + statistics.getLogins());
      sender.sendMessage(" §7-§eBlöcke gebaut: §a" + statistics.getBlocksBuilt());
      sender.sendMessage(" §7-§eTode: §a" + statistics.getDeaths());
      sender.sendMessage(" §7-§eLetzer Login: §a" + DateUtils.getDateTimeLabel(statistics.getLastLogin()));
    } else {
      sender.sendMessage("Command can only be used as a player");
    }
    return true;
  }
}

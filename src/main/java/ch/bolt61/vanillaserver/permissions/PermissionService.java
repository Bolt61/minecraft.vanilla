package ch.bolt61.vanillaserver.permissions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class PermissionService {

  private JavaPlugin plugin;
  private Map<UUID, PermissionAttachment> permissions = new HashMap<>();
  private final List<String> blockedPermissions = new ArrayList<>();
  
  public PermissionService(JavaPlugin plugin) {
    this.plugin = plugin;
    
    blockedPermissions.add("minecraft.command.me");
    blockedPermissions.add("minecraft.command.msg");
    blockedPermissions.add("minecraft.command.help");
  }
  
  public void addPlayer(Player player) {
    PermissionAttachment att = player.addAttachment(plugin);
    processBlockedPermissions(permission ->  att.setPermission("-" + permission, true));
    permissions.put(player.getUniqueId(), att);
  }
  
  public void removePlayer(Player player) {
    PermissionAttachment att = permissions.get(player.getUniqueId());
    if(att != null) {
      processBlockedPermissions(permission -> att.unsetPermission(permission));
    }
  }
  
  private void processBlockedPermissions(Consumer<String> permission) {
    for(String blocked : blockedPermissions) {
      permission.accept(blocked);
    }
  }
}

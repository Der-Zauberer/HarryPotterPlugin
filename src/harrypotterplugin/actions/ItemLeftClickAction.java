package harrypotterplugin.actions;

import org.bukkit.event.player.PlayerInteractEvent;

public interface ItemLeftClickAction {
	
	public abstract void onAction(PlayerInteractEvent event);

}

package me.pseudoknight.chpaper.abstraction.paper;

import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PaperListeners implements Listener {

	private static PaperListeners listener;

	public static void register() {
		if(listener == null){
			listener = new PaperListeners();
		}
		CommandHelperPlugin.self.registerEvents(listener);
	}

	public static void unregister() {
		BeaconEffectEvent.getHandlerList().unregister(listener);
		PlayerJumpEvent.getHandlerList().unregister(listener);
	}

	@EventHandler(priority= EventPriority.LOWEST)
	public void onBeaconEffect(BeaconEffectEvent event) {
		PaperEvents.PaperBeaconEffectEvent be = new PaperEvents.PaperBeaconEffectEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "beacon_effect", be);
	}

	@EventHandler(priority= EventPriority.LOWEST)
	public void onPlayerJump(PlayerJumpEvent event) {
		PaperEvents.PaperPlayerJumpEvent pj = new PaperEvents.PaperPlayerJumpEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "player_jump", pj);
	}
}

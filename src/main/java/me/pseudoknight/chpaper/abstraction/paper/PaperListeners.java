package me.pseudoknight.chpaper.abstraction.paper;

import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
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
		PlayerArmorChangeEvent.getHandlerList().unregister(listener);
		PlayerElytraBoostEvent.getHandlerList().unregister(listener);
		EntityRemoveFromWorldEvent.getHandlerList().unregister(listener);
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

	@EventHandler(priority= EventPriority.LOWEST)
	public void onPlayerArmorChange(PlayerArmorChangeEvent event) {
		PaperEvents.PaperPlayerArmorChangeEvent ac = new PaperEvents.PaperPlayerArmorChangeEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "player_armor_change", ac);
	}

	@EventHandler(priority= EventPriority.LOWEST)
	public void onPlayerBoosts(PlayerElytraBoostEvent event) {
		PaperEvents.PaperPlayerElytraBoostEvent peb = new PaperEvents.PaperPlayerElytraBoostEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "player_elytra_boost", peb);
	}

	@EventHandler(priority= EventPriority.LOWEST)
	public void onEntityRemoveFromWorld(EntityRemoveFromWorldEvent event) {
		PaperEvents.PaperEntityRemoveFromWorldEvent peb = new PaperEvents.PaperEntityRemoveFromWorldEvent(event);
		EventUtils.TriggerListener(Driver.EXTENSION, "entity_remove_from_world", peb);
	}
}

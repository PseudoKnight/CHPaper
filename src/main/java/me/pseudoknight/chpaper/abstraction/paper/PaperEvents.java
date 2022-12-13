package me.pseudoknight.chpaper.abstraction.paper;

import com.destroystokyo.paper.event.block.BeaconEffectEvent;
import com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent;
import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerElytraBoostEvent;
import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.bukkit.BukkitMCItemStack;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCFirework;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.abstraction.bukkit.events.BukkitPlayerEvents.BukkitMCPlayerEvent;
import com.laytonsmith.abstraction.entities.MCFirework;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCEntityType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCPotionEffectType;
import com.laytonsmith.annotations.abstraction;
import me.pseudoknight.chpaper.abstraction.*;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class PaperEvents {

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class PaperBeaconEffectEvent implements MCBeaconEffectEvent {
		BeaconEffectEvent e;

		public PaperBeaconEffectEvent(Event e) {
			this.e = (BeaconEffectEvent) e;
		}

		@Override
		public MCLivingEntity.MCEffect getEffect(){
			PotionEffect pe = e.getEffect();
			return new MCLivingEntity.MCEffect(BukkitMCPotionEffectType.valueOfConcrete(pe.getType()),
					pe.getAmplifier(), pe.getDuration(), pe.isAmbient(), pe.hasParticles());
		}

		@Override
		public void setEffect(int id, int strength, int ticks, boolean ambient, boolean particles){
			e.setEffect(new PotionEffect(PotionEffectType.getById(id), strength,
					ticks, ambient, particles));
		}

		@Override
		public boolean isPrimary(){
			return e.isPrimary();
		}

		@Override
		public MCPlayer getPlayer() {
			return new BukkitMCPlayer(e.getPlayer());
		}

		@Override
		public MCBlock getBlock(){
			return new BukkitMCBlock(e.getBlock());
		}

		@Override
		public Object _GetObject() {
			return e;
		}
	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class PaperPlayerJumpEvent extends BukkitMCPlayerEvent implements MCPlayerJumpEvent {
		PlayerJumpEvent e;

		public PaperPlayerJumpEvent(PlayerJumpEvent e) {
			super(e);
			this.e = e;
		}

		@Override
		public MCLocation getFrom() {
			return new BukkitMCLocation(e.getFrom());
		}

		@Override
		public MCLocation getTo() {
			return new BukkitMCLocation(e.getTo());
		}

		@Override
		public void setFrom(MCLocation oldloc) {
			e.setFrom(((BukkitMCLocation) oldloc)._Location());
		}

		@Override
		public Object _GetObject() {
			return e;
		}

	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class PaperPlayerArmorChangeEvent extends BukkitMCPlayerEvent implements MCPlayerArmorChangeEvent {
		PlayerArmorChangeEvent e;

		public PaperPlayerArmorChangeEvent(PlayerArmorChangeEvent e) {
			super(e);
			this.e = e;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public MCItemStack getNewItem() {
			ItemStack stack = e.getNewItem();
			if(stack == null) {
				return null;
			}
			return new BukkitMCItemStack(e.getNewItem());
		}

		@Override
		public MCItemStack getOldItem() {
			ItemStack stack = e.getOldItem();
			if(stack == null) {
				return null;
			}
			return new BukkitMCItemStack(e.getOldItem());
		}

		@Override
		public String getSlotType() {
			return e.getSlotType().name();
		}
	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class PaperPlayerElytraBoostEvent extends BukkitMCPlayerEvent implements MCPlayerElytraBoostEvent {
		PlayerElytraBoostEvent e;

		public PaperPlayerElytraBoostEvent(PlayerElytraBoostEvent e) {
			super(e);
			this.e = e;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public MCFirework getFirework() {
			return new BukkitMCFirework(e.getFirework());
		}

		@Override
		public MCItemStack getItemStack() {
			return new BukkitMCItemStack(e.getItemStack());
		}

		@Override
		public boolean willConsumeItem() {
			return e.shouldConsume();
		}

		@Override
		public void setConsumeItem(boolean consume) {
			e.setShouldConsume(consume);
		}
	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class PaperEntityRemoveFromWorldEvent implements MCEntityRemoveFromWorldEvent {
		EntityRemoveFromWorldEvent e;

		public PaperEntityRemoveFromWorldEvent(Event e) {
			this.e = (EntityRemoveFromWorldEvent) e;
		}

		@Override
		public UUID getEntityUniqueId() {
			return e.getEntity().getUniqueId();
		}

		@Override
		public MCEntityType<EntityType> getEntityType() {
			return BukkitMCEntityType.valueOfConcrete(e.getEntityType());
		}

		@Override
		public Object _GetObject() {
			return e;
		}
	}

}

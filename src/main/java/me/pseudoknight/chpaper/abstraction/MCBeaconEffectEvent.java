package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.core.events.BindableEvent;

public interface MCBeaconEffectEvent extends BindableEvent {

	public MCLivingEntity.MCEffect getEffect();
	public void setEffect(int id, int strength, int ticks, boolean ambient, boolean particles);
	public boolean isPrimary();
	public MCPlayer getPlayer();
	public MCBlock getBlock();

}

package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.entities.MCFirework;
import com.laytonsmith.abstraction.events.MCPlayerEvent;

public interface MCPlayerElytraBoostEvent extends MCPlayerEvent {
	MCFirework getFirework();
	MCItemStack getItemStack();
	boolean willConsumeItem();
	void setConsumeItem(boolean consume);
}

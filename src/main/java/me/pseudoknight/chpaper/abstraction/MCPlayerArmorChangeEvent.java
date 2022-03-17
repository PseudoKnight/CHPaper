package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.MCItemStack;
import com.laytonsmith.abstraction.events.MCPlayerEvent;

public interface MCPlayerArmorChangeEvent extends MCPlayerEvent {

	MCItemStack getNewItem();
	MCItemStack getOldItem();
	String getSlotType();

}

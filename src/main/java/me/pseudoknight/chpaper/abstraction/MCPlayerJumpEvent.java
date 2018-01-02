package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.events.MCPlayerEvent;

public interface MCPlayerJumpEvent extends MCPlayerEvent {

	MCLocation getTo();
	MCLocation getFrom();
	void setFrom(MCLocation loc);

}

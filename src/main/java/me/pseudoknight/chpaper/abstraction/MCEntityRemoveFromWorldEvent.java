package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.core.events.BindableEvent;

import java.util.UUID;

public interface MCEntityRemoveFromWorldEvent extends BindableEvent {

	UUID getEntityUniqueId();
	String getEntityTypeName();

}

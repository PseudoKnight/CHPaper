package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public interface MCEntityRemoveFromWorldEvent extends BindableEvent {

	UUID getEntityUniqueId();
	MCEntityType<EntityType> getEntityType();

}

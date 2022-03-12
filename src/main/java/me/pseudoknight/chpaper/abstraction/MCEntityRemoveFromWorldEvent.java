package me.pseudoknight.chpaper.abstraction;

import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.entity.EntityType;

public interface MCEntityRemoveFromWorldEvent extends BindableEvent {

    MCEntity getEntity();
    MCEntityType<EntityType> getEntityType();

}

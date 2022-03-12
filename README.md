# CHPaper

Gives access to API available in Paper but not Bukkit or Spigot.

## Function Documentation

### Player Management
#### boolean get_paffects_spawning([player])
Gets whether or not this player affects mob spawning.

#### void set_paffects_spawning([player], boolean)
Sets whether or not this player affects mob spawning.

The following functions allow you to set view distance per player. There's some quirks to note, however. If player view distance is greater than world view distance, entity tracking range will be limited to the world's view distance. If player view distance is less than world view distance, chunks will still be sent according to world view distance when crossing chunk boundaries.

#### void set_pview_distance([player], distance)
Sets view distance for player in chunks.

#### int pview_distance([player])
Gets view distance for player in chunks.

### World Mob Spawning Control
These functions are not Paper-specific and will likely be moved to CommandHelper core at some point under different function names. I forgot to remove them before publishing, but enjoy them for now.

#### array get_spawning(world)
Gets the entity spawn settings for this world.

#### void set_spawning(world, settingsArray)
Sets the entity spawn settings for this world.

#### UUID get_firework_shooter(fireworkUUID)
Gets the entity that spawned this firework, or null if none exists.

### Mob Management

#### void set_mob_killer(entityID, player)
Sets the killer of a mob/player to the specified player.

#### boolean set_mob_destination(entityID, destination, [speedMultiplier])
Finds and sets a path for a mob to a location or living entity.

#### array get_mob_path(entityID)
Returns an array of location arrays along the mob's current path or null.

## Event Documentation

### beacon_effect

This event is called when beacon effect is applied to a player.

#### Event Data

* **player:** The player the effect is being applied to.
* **location:** The location of the beacon.
* **effect:** The array of the potion effect being applied to the player.
* **primary:** Whether or not the effect is the primary effect from the beacon.

#### Mutable Fields

* **effect**

### player_jump

This event is called when a player jumps.

#### Prefilters

* **player:** <string match>

#### Event Data

* **player:** The player jumping.
* **from:** The location from which the player is jumping.
* **to:** The location the player is moving to.

#### Mutable Fields

* **from**

### player_elytra_boost

This event is called when a player boosts using a firework while gliding.

#### Prefilters

* **player:** <string match>

#### Event Data

* **player:** The player boosting.
* **firework:** The entity id of the firework created.
* **item:** The item array of the firework used.
* **consume:** Whether or not the firework item will be consumed.

#### Mutable Fields

* **consume**

### entity_remove_from_world

Fired any time an entity is being removed from a world for any reason

#### Prefilters

* **id**: <Macro>
* **type**: <Macro>

#### Event Data

* **id:** The entityID
* **type:** The type of entity removing.


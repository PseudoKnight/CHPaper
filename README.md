# CHPaper

Gives access to API available in Paper but not Bukkit or Spigot.

## Compatibility

CHPaper 0.4.0 requires CommandHelper 3.3.5 (Paper 1.16.5+) (NOTE: some functions require later Paper versions)  
CHPaper 0.3.5 requires CommandHelper 3.3.4 - 3.3.5 #255 (Paper 1.13.2 - 1.18.2)  
CHPaper 0.2.1 requires CommandHelper 3.3.2 (Paper 1.7.10 - 1.12.2)  

## Function Documentation

#### UUID get_firework_shooter(fireworkUUID)
Returns the UUID for the entity that spawned this firework, or null if none exists.

#### int get_world_view_distance(world)
Returns the view distance (in chunks) for a world.

#### void set_world_view_distance(world, distance)
Sets the view distance (in chunks) for a world.

#### int get_command_block_success(location)
Gets the success count for a commandblock. (1.17)

#### void set_command_block_success(location, int)
Sets the success count for a commandblock. (1.17)

#### void minimessage([recipient], message)
Sends a MiniMessage formatted message. (1.19)

#### boolean get_paffects_spawning([player])
Gets whether or not this player affects mob spawning.

#### void set_paffects_spawning([player], boolean)
Sets whether or not this player affects mob spawning.

### World Mob Spawning Control
These functions are not Paper-specific and will likely be moved to CommandHelper core at some point under different function names. I forgot to remove them before publishing, but enjoy them for now.

#### array get_spawning(world)
Gets the entity spawn settings for this world.

#### void set_spawning(world, settingsArray)
Sets the entity spawn settings for this world.

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

* **player:** String

#### Event Data

* **player:** The player jumping.
* **from:** The location from which the player is jumping.
* **to:** The location the player is moving to.

#### Mutable Fields

* **from**

### player_elytra_boost

This event is called when a player boosts using a firework while gliding.

#### Prefilters

* **player:** String

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

* **id**: String
* **type**: Macro

#### Event Data

* **id:** The entityID
* **type:** The type of entity removing.

### player_armor_change

This event is called when a player's armor slot changes by any cause, including function.

#### Prefilters

* **player:** String

#### Event Data

* **player:** The player whose armor slot changed.
* **olditem:** The item array for the old item (or null)
* **newitem:** The item array for the new item (or null)
* **slottype:** The armor slot changed (HEAD, CHEST, LEGS, FEET)

#### Mutable Fields

* **from**
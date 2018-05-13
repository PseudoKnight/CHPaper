# CHPaper

Gives access to API available in Paper but not Bukkit or Spigot. Only supports 1.12.2 since most API additions were added later.

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

### Mob Management

#### void set_mob_killer(entityID, player)
Sets the killer of a mob/player to the specified player.

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


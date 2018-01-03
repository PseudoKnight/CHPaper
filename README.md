# CHPaper

Gives access to API available in Paper but not Bukkit or Spigot.

## Function Documentation

### View Distance

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


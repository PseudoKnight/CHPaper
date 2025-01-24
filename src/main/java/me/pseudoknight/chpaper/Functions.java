package me.pseudoknight.chpaper;

import com.destroystokyo.paper.entity.Pathfinder;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCCommandSender;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.MCWorld;
import com.laytonsmith.abstraction.blocks.MCCommandBlock;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;

import java.util.List;

public class Functions {
	public static String docs() {
		return "General functions using the Paper API.";
	}

	@api
	public static class get_spawning extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			World w = (World) Static.getWorld(args[0].val(), t).getHandle();
			CArray values = CArray.GetAssociativeArray(t);
			values.set("animal-spawns", CBoolean.get(w.getAllowAnimals()), t);
			values.set("monster-spawns", CBoolean.get(w.getAllowMonsters()), t);

			// switch to getSpawnLimit(Category) after 1.18.1
			values.set("ambient-spawn-limit", new CInt(w.getAmbientSpawnLimit(), t), t);
			values.set("wateranimal-spawn-limit", new CInt(w.getWaterAnimalSpawnLimit(), t), t);
			values.set("animal-spawn-limit", new CInt(w.getAnimalSpawnLimit(), t), t);
			values.set("monster-spawn-limit", new CInt(w.getMonsterSpawnLimit(), t), t);
			values.set("water-ambient-spawn-limit", new CInt(w.getWaterAmbientSpawnLimit(), t), t);

			// switch to getTicksPerSpawns(Category) after 1.18.1
			values.set("ticks-per-animal-spawns", new CInt(w.getTicksPerAnimalSpawns(), t), t);
			values.set("ticks-per-monster-spawns", new CInt(w.getTicksPerMonsterSpawns(), t), t);
			values.set("ticks-per-ambient-spawns", new CInt(w.getTicksPerAmbientSpawns(), t), t);
			values.set("ticks-per-water-spawns", new CInt(w.getTicksPerWaterSpawns(), t), t);
			values.set("ticks-per-water-ambient-spawns", new CInt(w.getTicksPerWaterAmbientSpawns(), t), t);
			return(values);
		}

		public String getName() {
			return "get_spawning";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "array {world} Gets the entity spawn settings for this world.";
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

	}

	@api
	public static class set_spawning extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			World w = (World) Static.getWorld(args[0].val(), t).getHandle();
			if(args[1] instanceof CArray values) {
				boolean animalspawns = w.getAllowAnimals();
				boolean monsterspawns = w.getAllowMonsters();
				if(values.containsKey("animal-spawns")) {
					animalspawns = ArgumentValidation.getBooleanObject(values.get("animal-spawns", t), t);
				}
				if(values.containsKey("monster-spawns")) {
					monsterspawns = ArgumentValidation.getBooleanObject(values.get("monster-spawns", t), t);
				}
				w.setSpawnFlags(monsterspawns, animalspawns);

				// switch to setSpawnLimit(Category, int) after 1.18.1
				if(values.containsKey("ambient-spawn-limit")) {
					w.setAmbientSpawnLimit(ArgumentValidation.getInt32(values.get("ambient-spawn-limit", t), t));
				}
				if(values.containsKey("wateranimal-spawn-limit")) {
					w.setWaterAnimalSpawnLimit(ArgumentValidation.getInt32(values.get("wateranimal-spawn-limit", t), t));
				}
				if(values.containsKey("animal-spawn-limit")) {
					w.setAnimalSpawnLimit(ArgumentValidation.getInt32(values.get("animal-spawn-limit", t), t));
				}
				if(values.containsKey("monster-spawn-limit")) {
					w.setMonsterSpawnLimit(ArgumentValidation.getInt32(values.get("monster-spawn-limit", t), t));
				}
				if(values.containsKey("water-ambient-spawn-limit")) {
					w.setWaterAmbientSpawnLimit(ArgumentValidation.getInt32(values.get("water-ambient-spawn-limit", t), t));
				}

				// switch to setTicksPerSpawns(Category, int) after 1.18.1
				if(values.containsKey("ticks-per-animal-spawns")) {
					w.setTicksPerAnimalSpawns(ArgumentValidation.getInt32(values.get("ticks-per-animal-spawns", t), t));
				}
				if(values.containsKey("ticks-per-monster-spawns")) {
					w.setTicksPerMonsterSpawns(ArgumentValidation.getInt32(values.get("ticks-per-monster-spawns", t), t));
				}
				if(values.containsKey("ticks-per-ambient-spawns")) {
					w.setTicksPerAmbientSpawns(ArgumentValidation.getInt32(values.get("ticks-per-ambient-spawns", t), t));
				}
				if(values.containsKey("ticks-per-water-spawns")) {
					w.setTicksPerWaterSpawns(ArgumentValidation.getInt32(values.get("ticks-per-water-spawns", t), t));
				}
				if(values.containsKey("ticks-per-water-ambient-spawns")) {
					w.setTicksPerWaterAmbientSpawns(ArgumentValidation.getInt32(values.get("ticks-per-water-ambient-spawns", t), t));
				}

			} else {
				throw new CREFormatException("Expected an array for parameter 2.", t);
			}
			return CVoid.VOID;
		}

		public String getName() {
			return "set_spawning";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {world, settingsArray} Sets the entity spawn settings for this world.";
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

	}

	@api
	public static class set_mob_killer extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREBadEntityException.class, CREPlayerOfflineException.class, CRELengthException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCLivingEntity mob = Static.getLivingEntity(args[0], t);
			if(args[1] instanceof CNull) {
				((LivingEntity) mob.getHandle()).setKiller(null);
			} else {
				MCPlayer player = Static.GetPlayer(args[1], t);
				((LivingEntity) mob.getHandle()).setKiller((Player) player.getHandle());
			}
			return CVoid.VOID;
		}

		public String getName() {
			return "set_mob_killer";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {entity, player} Sets the killer of a mob/player to the specified player.";
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

	}

	@api
	public static class set_mob_destination extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREBadEntityException.class, CRELengthException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCLivingEntity mob = Static.getLivingEntity(args[0], t);
			if(!(mob.getHandle() instanceof Mob)) {
				throw new CREBadEntityException("That entity is not a mob.", t);
			}
			if(args[1] instanceof CNull) {
				((Mob) mob.getHandle()).getPathfinder().stopPathfinding();
				return CBoolean.TRUE;
			}
			double speedMultiplier = 1.0;
			if(args.length == 3) {
				speedMultiplier = ArgumentValidation.getDouble(args[2], t);
			}
			if(args[1].isInstanceOf(CArray.TYPE)) {
				MCLocation destination = ObjectGenerator.GetGenerator().location(args[1], null, t);
				return CBoolean.get(((Mob) mob.getHandle()).getPathfinder().moveTo((Location) destination.getHandle(), speedMultiplier));
			}
			MCLivingEntity destination = Static.getLivingEntity(args[1], t);
			return CBoolean.get(((Mob) mob.getHandle()).getPathfinder().moveTo((LivingEntity) destination.getHandle(), speedMultiplier));
		}

		public String getName() {
			return "set_mob_destination";
		}

		public Integer[] numArgs() {
			return new Integer[]{2, 3};
		}

		public String docs() {
			return "boolean {entity, destination, [speedMultiplier]} Finds and sets a path for a mob."
					+ " Accepts a location array or a living entity UUID as its destination."
					+ " An optional speed multiplier can be given, which will temporarily hasten the mob to the destination."
					+ " Returns whether it was successful or not. (will fail on the first tick after mob spawn)"
					+ " Note that a mob's AI may forget or change its destination at any time.";
		}

		public Version since() {
			return MSVersion.V3_3_4;
		}

	}

	@api
	public static class get_mob_path extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREBadEntityException.class, CRELengthException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCLivingEntity mob = Static.getLivingEntity(args[0], t);
			if(!(mob.getHandle() instanceof Mob)) {
				throw new CREBadEntityException("That entity is not a mob.", t);
			}
			Pathfinder.PathResult path = ((Mob) mob.getHandle()).getPathfinder().getCurrentPath();
			if(path == null) {
				return CNull.NULL;
			}

			List<Location> points = path.getPoints();
			int startIndex = path.getNextPointIndex();
			if(startIndex >= points.size()) {
				return CNull.NULL;
			}

			CArray ca = new CArray(t);
			for(int i = startIndex; i < points.size(); i++) {
				ca.push(ObjectGenerator.GetGenerator().location(new BukkitMCLocation(points.get(i)), false), t);
			}
			return ca;
		}

		public String getName() {
			return "get_mob_path";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "array {entity} Returns an array of location arrays along the mob's current path,"
					+ " starting with the next location and ending with the final destination."
					+ " Returns null if the mob has no current path.";
		}

		public Version since() {
			return MSVersion.V3_3_4;
		}

	}

	@api
	public static class get_world_view_distance extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREInvalidWorldException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCWorld w = Static.getWorld(args[0].val(), t);
			// Added to Spigot in 1.20.4
			int viewDistance = ((World) w.getHandle()).getViewDistance();
			return new CInt(viewDistance, t);
		}

		public String getName() {
			return "get_world_view_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "int {world} Returns the view distance (in chunks) for a world.";
		}

		public Version since() {
			return MSVersion.V3_3_4;
		}

	}

	@api
	public static class set_world_view_distance extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREInvalidWorldException.class, CRERangeException.class, CRECastException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCWorld w = Static.getWorld(args[0].val(), t);
			int viewDistance = ArgumentValidation.getInt32(args[1], t);
			// Added to Spigot in 1.20.4
			((World) w.getHandle()).setViewDistance(viewDistance);
			return CVoid.VOID;
		}

		public String getName() {
			return "set_world_view_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {world, distance} Sets the view distance (in chunks) for a world."
					+ " Cannot be set lower than simulation distance.";
		}

		public Version since() {
			return MSVersion.V3_3_4;
		}

	}

	@api
	public static class get_command_block_success extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREInvalidWorldException.class, CRERangeException.class, CRECastException.class,
					CREFormatException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCLocation loc = ObjectGenerator.GetGenerator().location(args[0], null, t);
			if(loc.getBlock().isCommandBlock()) {
				MCCommandBlock cb = loc.getBlock().getCommandBlock();
				try {
					return new CInt(((CommandBlock) cb.getHandle()).getSuccessCount(), t);
				} catch(NoSuchMethodError ex) {
					throw new CREException("This function requires Paper 1.17 or higher.", t);
				}
			}
			throw new CREException("The block at the specified location is not a commandblock", t);
		}

		public String getName() {
			return "get_command_block_success";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "int {location} Gets the success count for a commandblock. (1.17)";
		}

		public Version since() {
			return MSVersion.V3_3_5;
		}

	}

	@api
	public static class set_command_block_success extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREInvalidWorldException.class, CRERangeException.class, CRECastException.class,
					CREFormatException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCLocation loc = ObjectGenerator.GetGenerator().location(args[0], null, t);
			int count = ArgumentValidation.getInt32(args[1], t);
			if(loc.getBlock().isCommandBlock()) {
				MCCommandBlock cb = loc.getBlock().getCommandBlock();
				try {
					((CommandBlock) cb.getHandle()).setSuccessCount(count);
				} catch(NoSuchMethodError ex) {
					throw new CREException("This function requires Paper 1.17 or higher.", t);
				}
				cb.update();
			} else {
				throw new CREException("The block at the specified location is not a commandblock", t);
			}
			return CVoid.VOID;
		}

		public String getName() {
			return "set_command_block_success";
		}

		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		public String docs() {
			return "void {location, int} Sets the success count for a commandblock. (1.17)"
					+ " Plugin commands will increment the success count of commandblocks by one afterwards."
					+ " So when setting the success count inside a command,"
					+ " make sure to subtract one below the desired result.";
		}

		public Version since() {
			return MSVersion.V3_3_5;
		}

	}

	@api
	public static class minimessage extends AbstractFunction {

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREPlayerOfflineException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCCommandSender sender;
			String message;
			if(args.length == 2) {
				sender = Static.GetCommandSender(args[0].val(), t);
				message = args[1].val();
			} else {
				sender = environment.getEnv(CommandHelperEnvironment.class).GetCommandSender();
				if(sender == null) {
					throw new CREPlayerOfflineException("No recipient to send minimessage to.", t);
				}
				message = args[0].val();
			}
			try {
				((CommandSender) sender.getHandle()).sendMessage(net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(message));
			} catch(NoClassDefFoundError ex) {
				throw new CREException("minimessage() requires Paper 1.18.2+.", t);
			}
			return CVoid.VOID;
		}

		public String getName() {
			return "minimessage";
		}

		public Integer[] numArgs() {
			return new Integer[]{1, 2};
		}

		public String docs() {
			return "void {[recipient], message} Sends a MiniMessage formatted message. (requires Paper 1.18.2+)"
					+ " If recipient argument is absent, command sender from current context is used.";
		}

		public Version since() {
			return MSVersion.V3_3_5;
		}

	}
}

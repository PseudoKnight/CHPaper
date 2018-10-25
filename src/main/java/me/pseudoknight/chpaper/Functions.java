package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREBadEntityException;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CRELengthException;
import com.laytonsmith.core.exceptions.CRE.CREPlayerOfflineException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

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

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			World w = (World) Static.getWorld(args[0].val(), t).getHandle();
			CArray values = CArray.GetAssociativeArray(t);
			values.set("animal-spawns", CBoolean.get(w.getAllowAnimals()), t);
			values.set("monster-spawns", CBoolean.get(w.getAllowMonsters()), t);
			values.set("ambient-spawn-limit", new CInt(w.getAmbientSpawnLimit(), t), t);
			values.set("wateranimal-spawn-limit", new CInt(w.getWaterAnimalSpawnLimit(), t), t);
			values.set("animal-spawn-limit", new CInt(w.getAnimalSpawnLimit(), t), t);
			values.set("monster-spawn-limit", new CInt(w.getMonsterSpawnLimit(), t), t);
			values.set("ticks-per-animal-spawns", new CInt(w.getTicksPerAnimalSpawns(), t), t);
			values.set("ticks-per-monster-spawns", new CInt(w.getTicksPerMonsterSpawns(), t), t);
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
			return CHVersion.V3_3_2;
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

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			World w = (World) Static.getWorld(args[0].val(), t).getHandle();
			if(args[1] instanceof CArray) {
				CArray values = (CArray) args[1];

				boolean animalspawns = w.getAllowAnimals();
				boolean monsterspawns = w.getAllowMonsters();
				if(values.containsKey("animal-spawns")) {
					animalspawns = Static.getBoolean(values.get("animal-spawns", t), t);
				}
				if(values.containsKey("monster-spawns")) {
					monsterspawns = Static.getBoolean(values.get("monster-spawns", t), t);
				}
				w.setSpawnFlags(monsterspawns, animalspawns);

				if(values.containsKey("ambient-spawn-limit")) {
					w.setAmbientSpawnLimit(Static.getInt32(values.get("ambient-spawn-limit", t), t));
				}
				if(values.containsKey("wateranimal-spawn-limit")) {
					w.setWaterAnimalSpawnLimit(Static.getInt32(values.get("wateranimal-spawn-limit", t), t));
				}
				if(values.containsKey("animal-spawn-limit")) {
					w.setAnimalSpawnLimit(Static.getInt32(values.get("animal-spawn-limit", t), t));
				}
				if(values.containsKey("monster-spawn-limit")) {
					w.setMonsterSpawnLimit(Static.getInt32(values.get("monster-spawn-limit", t), t));
				}

				if(values.containsKey("ticks-per-animal-spawns")) {
					w.setTicksPerAnimalSpawns(Static.getInt32(values.get("ticks-per-animal-spawns", t), t));
				}
				if(values.containsKey("ticks-per-monster-spawns")) {
					w.setTicksPerMonsterSpawns(Static.getInt32(values.get("ticks-per-monster-spawns", t), t));
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
			return CHVersion.V3_3_2;
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

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
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
			return CHVersion.V3_3_2;
		}

	}
}

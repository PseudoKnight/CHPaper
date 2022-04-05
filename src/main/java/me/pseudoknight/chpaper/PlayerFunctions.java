package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.*;
import com.laytonsmith.core.compiler.CompilerEnvironment;
import com.laytonsmith.core.compiler.CompilerWarning;
import com.laytonsmith.core.compiler.FileOptions;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.*;
import com.laytonsmith.core.exceptions.ConfigCompileException;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PlayerFunctions {
	public static String docs() {
		return "Player functions using the Paper API.";
	}

	@api
	public static class set_pview_distance extends AbstractFunction implements Optimizable {

		public String getName() {
			return "set_pview_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{1, 2};
		}

		public String docs() {
			return "void {[player], distance} (deprecated) Sets view distance for player in chunks.";
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCPlayer p;
			int distance;
			if(args.length == 1) {
				p = environment.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
				distance = ArgumentValidation.getInt32(args[0], t);
			} else {
				p = Static.GetPlayer(args[0], t);
				distance = ArgumentValidation.getInt32(args[1], t);
			}
			((Player) p.getHandle()).setViewDistance(distance);
			return CVoid.VOID;
		}

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREPlayerOfflineException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

		@Override
		public Set<Optimizable.OptimizationOption> optimizationOptions() {
			return EnumSet.of(
					Optimizable.OptimizationOption.OPTIMIZE_DYNAMIC
			);
		}

		@Override
		public ParseTree optimizeDynamic(Target t, Environment env,
				Set<Class<? extends Environment.EnvironmentImpl>> envs,
				List<ParseTree> children, FileOptions fileOptions) throws ConfigCompileException, ConfigRuntimeException {
			env.getEnv(CompilerEnvironment.class).addCompilerWarning(fileOptions,
					new CompilerWarning(getName() + " is deprecated.", t, null));
			return null;
		}

	}

	@api
	public static class pview_distance extends AbstractFunction implements Optimizable {

		public String getName() {
			return "pview_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		public String docs() {
			return "void {[player]} (deprecated) Gets view distance for player in chunks.";
		}

		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			MCPlayer p;
			if(args.length == 0) {
				p = environment.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
			} else {
				p = Static.GetPlayer(args[0], t);
			}
			return new CInt(((Player) p.getHandle()).getViewDistance(), t);
		}

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREPlayerOfflineException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

		@Override
		public Set<OptimizationOption> optimizationOptions() {
			return EnumSet.of(
					OptimizationOption.OPTIMIZE_DYNAMIC
			);
		}

		@Override
		public ParseTree optimizeDynamic(Target t, Environment env,
				Set<Class<? extends Environment.EnvironmentImpl>> envs,
				List<ParseTree> children, FileOptions fileOptions) throws ConfigCompileException, ConfigRuntimeException {
			env.getEnv(CompilerEnvironment.class).addCompilerWarning(fileOptions,
					new CompilerWarning(getName() + " is deprecated.", t, null));
			return null;
		}
	}

	@api
	public static class paffects_spawning extends AbstractFunction {

		public String getName() {
			return "paffects_spawning";
		}

		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		public String docs() {
			return "boolean {[player]} Gets whether or not this player affects mob spawning.";
		}

		public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
			MCPlayer p;
			if(args.length == 1) {
				p = Static.GetPlayer(args[0].val(), t);
			} else {
				p = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
			}
			return CBoolean.get(((Player) p.getHandle()).getAffectsSpawning());
		}

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREPlayerOfflineException.class, CRELengthException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

	}

	@api
	public static class set_paffects_spawning extends AbstractFunction {

		public String getName() {
			return "set_paffects_spawning";
		}

		public Integer[] numArgs() {
			return new Integer[]{1, 2};
		}

		public String docs() {
			return "void {[player], boolean} Sets whether or not this player affects mob spawning.";
		}

		public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
			MCPlayer p;
			boolean doesAffect;
			if(args.length == 2) {
				p = Static.GetPlayer(args[0].val(), t);
				doesAffect = ArgumentValidation.getBooleanObject(args[1], t);
			} else {
				p = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
				doesAffect = ArgumentValidation.getBooleanObject(args[0], t);
			}
			((Player) p.getHandle()).setAffectsSpawning(doesAffect);
			return CVoid.VOID;
		}

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREPlayerOfflineException.class, CRELengthException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Version since() {
			return MSVersion.V3_3_2;
		}

	}
	@api
	public static class get_firework_shooter extends AbstractFunction {

		public String getName() {
			return "get_firework_shooter";
		}

		public Integer[] numArgs() {
			return new Integer[]{1};
		}

		public String docs() {
			return "UUID {fireworkUUID} Gets the entity that spawned this firework, or null if none exists.";
		}

		public Mixed exec(Target t, Environment env, Mixed... args) throws ConfigRuntimeException {
			Entity e = (Entity) Static.getEntity(args[0], t).getHandle();
			if(!(e instanceof Firework)) {
				throw new CREBadEntityException("Entity is not a firework.", t);
			}
			UUID spawner = ((Firework) e).getSpawningEntity();
			if(spawner == null) {
				return CNull.NULL;
			}
			return new CString(spawner.toString(), t);
		}

		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CREBadEntityException.class};
		}

		public boolean isRestricted() {
			return true;
		}

		public Boolean runAsync() {
			return false;
		}

		public Version since() {
			return MSVersion.V3_3_4;
		}

	}
}

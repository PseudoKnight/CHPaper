package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.CommandHelperEnvironment;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CRELengthException;
import com.laytonsmith.core.exceptions.CRE.CREPlayerOfflineException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import org.bukkit.entity.Player;

public class PlayerFunctions {
	public static String docs() {
		return "Player functions using the Paper API.";
	}

	@api
	public static class set_pview_distance extends AbstractFunction {

		public String getName() {
			return "set_pview_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{1, 2};
		}

		public String docs() {
			return "void {[player], distance} Sets view distance for player in chunks.";
		}

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			MCPlayer p;
			int distance;
			if(args.length == 1) {
				p = environment.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
				distance = Static.getInt32(args[0], t);
			} else {
				p = Static.GetPlayer(args[0], t);
				distance = Static.getInt32(args[1], t);
			}
			((Player) p).setViewDistance(distance);
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
			return CHVersion.V3_3_2;
		}

	}

	@api
	public static class pview_distance extends AbstractFunction {

		public String getName() {
			return "pview_distance";
		}

		public Integer[] numArgs() {
			return new Integer[]{0, 1};
		}

		public String docs() {
			return "void {[player]} Gets view distance for player in chunks.";
		}

		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			MCPlayer p;
			if(args.length == 0) {
				p = environment.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
			} else {
				p = Static.GetPlayer(args[0], t);
			}
			return new CInt(((Player) p).getViewDistance(), t);
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
			return CHVersion.V3_3_2;
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

		public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
			MCPlayer p;
			if(args.length == 1) {
				p = Static.GetPlayer(args[0].val(), t);
			} else {
				p = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
			}
			return CBoolean.get(((Player) p).getAffectsSpawning());
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
			return CHVersion.V3_3_2;
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

		public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
			MCPlayer p;
			boolean doesAffect;
			if(args.length == 2) {
				p = Static.GetPlayer(args[0].val(), t);
				doesAffect = Static.getBoolean(args[1]);
			} else {
				p = env.getEnv(CommandHelperEnvironment.class).GetPlayer();
				Static.AssertPlayerNonNull(p, t);
				doesAffect = Static.getBoolean(args[0]);
			}
			((Player) p).setAffectsSpawning(doesAffect);
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
			return CHVersion.V3_3_2;
		}

	}
}

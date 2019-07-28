package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.MCEntity;
import com.laytonsmith.abstraction.MCLivingEntity;
import com.laytonsmith.abstraction.events.MCProjectileLaunchEvent;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.ArgumentValidation;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CDouble;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.BoundEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.exceptions.CRE.CRECastException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import me.pseudoknight.chpaper.abstraction.MCBeaconEffectEvent;
import me.pseudoknight.chpaper.abstraction.MCPlayerElytraBoostEvent;
import me.pseudoknight.chpaper.abstraction.MCPlayerJumpEvent;

import java.util.HashMap;
import java.util.Map;

public class Events {
	public static String docs() {
		return "This augments CommandHelper with PaperSpigot-specific events";
	}

	@api
	public static class beacon_effect extends AbstractEvent {

		@Override
		public String getName() {
			return "beacon_effect";
		}

		@Override
		public String docs() {
			return "{} "
					+ "This event is called when beacon effect is applied to a player. "
					+ "{player: The player the effect is being applied to. | location: The location of the beacon."
					+ " | effect: The array of the potion effect being applied to the player. | primary: Whether or not"
					+ " the effect is the primary effect from the beacon.} "
					+ "{effect} "
					+ "{}";
		}

		@Override
		public Driver driver() {
			return Driver.EXTENSION;
		}

		@Override
		public BindableEvent convert(CArray manualObject, Target t) {
			return null;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_2;
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			return e instanceof MCBeaconEffectEvent;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
			MCBeaconEffectEvent event = (MCBeaconEffectEvent) e;
			Map<String, Mixed> map = new HashMap<>();
			Target t = Target.UNKNOWN;

			map.put("player", new CString(event.getPlayer().getName(), t));
			map.put("location", ObjectGenerator.GetGenerator().location(event.getBlock().getLocation()));
			MCLivingEntity.MCEffect eff = event.getEffect();
			CArray effect = CArray.GetAssociativeArray(t);
			effect.set("id", new CInt(eff.getPotionEffectType().getId(), t), t);
			effect.set("strength", new CInt(eff.getStrength(), t), t);
			effect.set("seconds",  new CDouble(eff.getTicksRemaining() / 20.0, t), t);
			effect.set("ambient", CBoolean.get(eff.isAmbient()), t);
			effect.set("particles", CBoolean.get(eff.hasParticles()), t);
			map.put("effect", effect);
			map.put("primary", CBoolean.get(event.isPrimary()));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
			MCBeaconEffectEvent event = (MCBeaconEffectEvent)e;
			if(key.equals("effect")){
				Target t = value.getTarget();
				if(value instanceof CArray) {
					CArray ca = (CArray) value;
					int id = Static.getInt32(ca.get("id", t), t);
					int strength = Static.getInt32(ca.get("strength", t), t);
					double seconds = Static.getDouble(ca.get("seconds", t), t);
					boolean ambient = Static.getBoolean(ca.get("ambient", t), t);
					boolean particles = Static.getBoolean(ca.get("particles", t), t);
					event.setEffect(id, strength, (int)(seconds * 20), ambient, particles);
					return true;
				} else {
					throw new CRECastException("Effect must be an array", t);
				}
			}
			return false;
		}
	}

	@api
	public static class player_jump extends AbstractEvent {

		@Override
		public String getName() {
			return "player_jump";
		}

		@Override
		public String docs() {
			return "{player: <string match> The player jumping.} "
					+ "This event is called when a player jumps. "
					+ "{player | from: The location from which the player is jumping. | to: The location the player"
					+ " is moving to.} "
					+ "{from} "
					+ "{}";
		}

		@Override
		public Driver driver() {
			return Driver.EXTENSION;
		}

		@Override
		public BindableEvent convert(CArray manualObject, Target t) {
			return null;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_2;
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			if(!(e instanceof MCPlayerJumpEvent)) {
				return false;
			}
			MCPlayerJumpEvent event = (MCPlayerJumpEvent) e;
			if(prefilter.containsKey("player")
					&& !event.getPlayer().getName().equals(prefilter.get("player").val())) {
				return false;
			}
			return true;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
			MCPlayerJumpEvent event = (MCPlayerJumpEvent) e;
			Map<String, Mixed> map = new HashMap<>();
			Target t = Target.UNKNOWN;

			map.put("player", new CString(event.getPlayer().getName(), t));
			map.put("from", ObjectGenerator.GetGenerator().location(event.getFrom()));
			map.put("to", ObjectGenerator.GetGenerator().location(event.getTo()));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
			MCPlayerJumpEvent event = (MCPlayerJumpEvent)e;
			if(key.equals("from")){
				Target t = value.getTarget();
				event.setFrom(ObjectGenerator.GetGenerator().location(value, null, t));
				return true;
			}
			return false;
		}
	}

	@api
	public static class player_elytra_boost extends AbstractEvent {

		@Override
		public String getName() {
			return "player_elytra_boost";
		}

		@Override
		public String docs() {
			return "{player: <string match> The player boosting.} "
					+ "This event is called when a player boosts using a firework while gliding."
					+ "{player | consume: Whether or not the firework item will be consumed."
					+ " | firework: The entity id of the firework created. | item: The item array of the firework used.}"
					+ "{consume} "
					+ "{}";
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			if(!(e instanceof MCPlayerElytraBoostEvent)) {
				return false;
			}
			MCPlayerElytraBoostEvent event = (MCPlayerElytraBoostEvent) e;
			if(prefilter.containsKey("player")
					&& !event.getPlayer().getName().equals(prefilter.get("player").val())) {
				return false;
			}
			return true;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent e) throws EventException {
			MCPlayerElytraBoostEvent event = (MCPlayerElytraBoostEvent) e;
			Map<String, Mixed> map = new HashMap<>();
			Target t = Target.UNKNOWN;

			map.put("player", new CString(event.getPlayer().getName(), t));
			map.put("consume", CBoolean.get(event.willConsumeItem()));
			map.put("firework", new CString(event.getFirework().getUniqueId().toString(), t));
			map.put("item", ObjectGenerator.GetGenerator().item(event.getItemStack(), t));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent e) {
			MCPlayerElytraBoostEvent event = (MCPlayerElytraBoostEvent)e;
			if(key.equals("consume")){
				Target t = value.getTarget();
				event.setConsumeItem(ArgumentValidation.getBooleanObject(value, t));
				return true;
			}
			return false;
		}

		@Override
		public void preExecution(Environment env, BoundEvent.ActiveEvent activeEvent) {
			if(activeEvent.getUnderlyingEvent() instanceof MCPlayerElytraBoostEvent) {
				MCEntity entity = ((MCPlayerElytraBoostEvent) activeEvent.getUnderlyingEvent()).getFirework();
				Static.InjectEntity(entity);
			}
		}

		@Override
		public void postExecution(Environment env, BoundEvent.ActiveEvent activeEvent) {
			if(activeEvent.getUnderlyingEvent() instanceof MCPlayerElytraBoostEvent) {
				MCEntity entity = ((MCPlayerElytraBoostEvent) activeEvent.getUnderlyingEvent()).getFirework();
				Static.UninjectEntity(entity);
			}
		}

		@Override
		public Driver driver() {
			return Driver.EXTENSION;
		}

		@Override
		public BindableEvent convert(CArray manualObject, Target t) {
			return null;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_4;
		}
	}
}

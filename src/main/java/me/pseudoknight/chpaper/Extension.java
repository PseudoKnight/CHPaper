package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.MSLog;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import me.pseudoknight.chpaper.abstraction.paper.PaperListeners;

import java.util.logging.Level;

@MSExtension("CHPaper")
public class Extension extends AbstractExtension {

	public Version getVersion() {
		return new SimpleVersion(0,3,3);
	}

	@Override
	public void onStartup() {
		PaperListeners.register();
		Static.getLogger().log(Level.INFO, "CHPaper " + getVersion() + " loaded.");
	}

	@Override
	public void onShutdown() {
		PaperListeners.unregister();
		Static.getLogger().log(Level.INFO, "CHPaper " + getVersion() + " unloaded.");
	}
}

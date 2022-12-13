package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import me.pseudoknight.chpaper.abstraction.paper.PaperListeners;

@MSExtension("CHPaper")
public class Extension extends AbstractExtension {

	private static final Version version = new SimpleVersion(0,4,0);

	@Override
	public Version getVersion() {
		return version;
	}

	@Override
	public void onStartup() {
		PaperListeners.register();
		Static.getLogger().info( "CHPaper " + getVersion() + " loaded.");
	}

	@Override
	public void onShutdown() {
		PaperListeners.unregister();
		Static.getLogger().info( "CHPaper " + getVersion() + " unloaded.");
	}
}

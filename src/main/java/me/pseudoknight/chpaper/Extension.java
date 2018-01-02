package me.pseudoknight.chpaper;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import me.pseudoknight.chpaper.abstraction.paper.PaperListeners;

@MSExtension("CHPaper")
public class Extension extends AbstractExtension {

	public Version getVersion() {
		return new SimpleVersion(0,1,0);
	}

	@Override
	public void onStartup() {
		PaperListeners.register();
		System.out.println("CHPaper " + getVersion() + " loaded.");
	}

	@Override
	public void onShutdown() {
		PaperListeners.unregister();
		System.out.println("CHPaper " + getVersion() + " unloaded.");
	}
}

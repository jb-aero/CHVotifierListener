package io.github.jbaero.votes;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import com.vexsoftware.votifier.model.VotifierEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.logging.Level;

@MSExtension("CHVotifierListener")
public class CHVoteListener extends AbstractExtension implements Listener {

	protected CHVoteListener vl;

	@Override
	public void onStartup() {
		try {
			Static.getLogger().log(Level.INFO, "Checking for Votifier");
			Static.checkPlugin("Votifier", Target.UNKNOWN);
			if (vl == null)
				vl = new CHVoteListener();
			CommandHelperPlugin.self.registerEvents(vl);
			Static.getLogger().log(Level.INFO, "Vote event registered");
		} catch (ConfigRuntimeException e) {
			CommandHelperPlugin.self.getLogger().warning(e.getMessage());
		}
	}

	@Override
	public void onShutdown() {
		VotifierEvent.getHandlerList().unregister(vl);
		Static.getLogger().log(Level.INFO, "Vote event unregistered.");
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onVoteReceived(VotifierEvent event) {
		Static.getLogger().log(Level.FINE, "Vote event occurred.");
		EventUtils.TriggerListener(Driver.EXTENSION, "vote_received", new CHVoteEvent(event));
	}

	public Version getVersion() {
		return new SimpleVersion(1, 0, 3);
	}
}

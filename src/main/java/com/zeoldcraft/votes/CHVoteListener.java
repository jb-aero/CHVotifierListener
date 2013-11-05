package com.zeoldcraft.votes;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import com.laytonsmith.annotations.shutdown;
import com.laytonsmith.annotations.startup;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.CHLog;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.CHLog.Tags;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.vexsoftware.votifier.model.VotifierEvent;

public class CHVoteListener implements Listener {

	protected static CHVoteListener vl;
	
	@startup
	public static void register() {
		try {
			CHLog.GetLogger().d(Tags.EXTENSIONS, "Checking for Votifier", Target.UNKNOWN);
			Static.checkPlugin("Votifier", Target.UNKNOWN);
			if (vl == null)
				vl = new CHVoteListener();
			CommandHelperPlugin.self.registerEvent(vl);
			CHLog.GetLogger().d(Tags.EXTENSIONS, "Vote event registered", Target.UNKNOWN);
		} catch (ConfigRuntimeException e) {
			CommandHelperPlugin.self.getLogger().warning(e.getMessage());
		}
	}
	
	@shutdown
	public static void unregister() {
		VotifierEvent.getHandlerList().unregister(vl);
		CHLog.GetLogger().d(Tags.EXTENSIONS, "vote event unregistered", Target.UNKNOWN);
	}
	
	@EventHandler(priority=EventPriority.LOWEST)
	public void onVoteReceived(VotifierEvent event) {
		CHLog.GetLogger().d(Tags.EXTENSIONS, "vote event occured", Target.UNKNOWN);
		EventUtils.TriggerListener(Driver.EXTENSION, "vote_received", new CHVoteEvent(event));
	}
}

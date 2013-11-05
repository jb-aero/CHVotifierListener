package com.zeoldcraft.votes;

import com.laytonsmith.core.events.BindableEvent;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.model.VotifierEvent;

public class CHVoteEvent implements BindableEvent {

	VotifierEvent ve;
	public CHVoteEvent(VotifierEvent event) {
		this.ve = event;
	}
	
	public Object _GetObject() {
		return ve;
	}

	public Vote getVote() {
		return ve.getVote();
	}
}

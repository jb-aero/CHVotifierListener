package com.zeoldcraft.votes;

import java.util.Map;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.events.Prefilters.PrefilterType;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import com.vexsoftware.votifier.model.Vote;

public class VoteEvent {

	@api
	public static class vote_received extends AbstractEvent {

		public String getName() {
			return "vote_received";
		}

		public String docs() {
			return "{service}"
					+ " Fired when Votifier decides to fire it."
					+ " {address|service|timestamp|username}"
					+ " {address|service|timestamp|username}"
					+ " {}";
		}

		public boolean matches(Map<String, Construct> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			if (e instanceof CHVoteEvent) {
				Prefilters.match(prefilter, "service", ((CHVoteEvent) e).getVote().getServiceName(), PrefilterType.MACRO);
				return true;
			}
			return false;
		}

		public BindableEvent convert(CArray manualObject) {
			throw new ConfigRuntimeException("This operation is not supported", ExceptionType.BindException, Target.UNKNOWN);
		}

		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			if (event instanceof CHVoteEvent) {
				Map<String, Construct> ret = evaluate_helper(event);
				Vote vote = ((CHVoteEvent) event).getVote();
				Target t = Target.UNKNOWN;
				ret.put("address", new CString(vote.getAddress(), t));
				ret.put("service", new CString(vote.getServiceName(), t));
				ret.put("timestamp", new CString(vote.getTimeStamp(), t));
				ret.put("username", new CString(vote.getUsername(), t));
				return ret;
			} else {
				throw new EventException("Couldn't process the event... oh.");
			}
		}

		public Driver driver() {
			return Driver.EXTENSION;
		}

		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			if (event instanceof CHVoteEvent) {
				Vote vote = ((CHVoteEvent) event).getVote();
				if (key.equals("username")) {
					vote.setUsername(value.val());
					return true;
				}
				if (key.equals("service")) {
					vote.setServiceName(value.val());
					return true;
				}
				if (key.equals("address")) {
					vote.setAddress(value.val());
					return true;
				}
				if (key.equals("timestamp")) {
					vote.setTimeStamp(value.val());
					return true;
				}
			}
			return false;
		}

		public Version since() {
			return CHVersion.V3_3_1;
		}
	}
}

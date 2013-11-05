CHVotifierListener
==================

A Votifier listener, for Commandhelper

Contains one event: vote_received

Data: address, service, timestamp, username

Modifiable: all of the above

Prefilters: service

Example usage:

bind(vote_received, null, array(service: 'PlanetMinecraft'), @vote,
  runas('~console', '/op '.@vote[username])
)

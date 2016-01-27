CHVotifierListener
==================

A Votifier listener, for Commandhelper

Download the correct version for your version of CommandHelper:
<br>[CH 3.3.1-Snapshot](https://letsbuild.net/jenkins/job/CHVotifierListener/1/) (Old version)
<br>[CH 3.3.1 (Release)](https://letsbuild.net/jenkins/job/CHVotifierListener/2/) (You probably want this one)
<br>[CH 3.3.2-Snapshot](https://letsbuild.net/jenkins/job/CHVotifierListener/lastSuccessfulBuild/) (Dev builds)

Contains one event: vote_received

Data: address, service, timestamp, username

Modifiable: all of the above

Prefilters: service

Example usage:

bind(vote_received, null, array(service: 'PlanetMinecraft'), @vote,
  runas('~console', '/op '.@vote[username])
)

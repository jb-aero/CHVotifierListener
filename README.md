CHVotifierListener
==================

A (Nu)Votifier listener, for Commandhelper

<br>[Builds](https://letsbuild.net/jenkins/job/CHVotifierListener/lastSuccessfulBuild/)

Contains one event: vote_received

Data: address, service, timestamp, username

Modifiable: all of the above

Prefilters: service

Example usage:

```php
bind(vote_received, null, array(service: "PlanetMinecraft"), @vote) {
  runas("~console", "/op ".@vote[username]);
}
```

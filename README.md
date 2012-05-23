NuxFlags
===========

With NuxFlags you can add personnalized markers for dynmap simply with ingame signs.


Installation
------------

* First, you need the PermissionsEx (3) plugin ([here](http://dev.bukkit.org/server-mods/permissionsex/)).
* Download the latest jar [here](https://github.com/NuxosMinecraft/NuxFlags/downloads).
* Copy the downloaded jar file into the plugins folder and rename it to "NuxFlags.jar".

TODO
------------

* Add more parameters available to MarkersConfig

Permission's nodes
------------------

* nuxflags.add
* nuxgrief.remove

Configuration
-------------

The configuration file is : plugins/NuxFlags/config.yml

You can find info about dynmap markers ([here](https://github.com/webbukkit/dynmap/wiki/Using-markers))
Accepted parameters for MarkersConfig are only icon and minzoom.

Example :

    MarkerSets:
     - home
     - lieu
     - mine
	 
    MarkersConfig:
     home:
      icon: "house"
      minzoom: 2
     lieu:
      icon: "blueflag"
      minzoom: 1
     mine:
      icon: "hammer"
      minzoom: 1
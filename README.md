NuxFlags
===========

With NuxFlags you can add personnalized markers for dynmap simply with ingame signs.


Installation
------------

* First, you need the PermissionsEx (3) plugin ([here](http://dev.bukkit.org/server-mods/permissionsex/)).
* Download the latest jar [here](https://github.com/NuxosMinecraft/NuxFlags/downloads).
* Copy the downloaded jar file into the plugins folder and rename it to "NuxFlags.jar".

Configuration
-------------

The configuration file is : plugins/NuxFlags/config.yml

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
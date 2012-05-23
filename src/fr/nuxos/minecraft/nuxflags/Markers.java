package fr.nuxos.minecraft.nuxflags;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Markers {
	
	public Logger log;
    private FileConfiguration config;
    public NuxFlags plugin;
    private Server serv;

    public Markers(NuxFlags plugin) {
    	log = plugin.getServer().getLogger();
    	config = plugin.getConfig();
    	serv = plugin.getServer();
    	InitMarkerSets();
    }
    
    private void InitMarkerSets() {
       log.info("[NuxFlags] Initializing markersets into dynmap");
   	   List<String> MSets = config.getStringList("MarkerSets");
   	   
	   	for (String M : MSets) {
            
	   		ConfigurationSection cs = config.getConfigurationSection("MarkersConfig." + M);
	   		Integer minzoom = cs.getInt("minzoom");

	   		serv.dispatchCommand(serv.getConsoleSender(), "dmarker addset " + M + " minzoom:" + minzoom);
	   		log.info("[NuxFlags] Adding markset : " + M + " minzoom:" + minzoom);
   	    }
    }

	public void AddMarker(Player player, String MarkerSet, String MarkerID, String MarkerLabel) {

   		ConfigurationSection cs = config.getConfigurationSection("MarkersConfig." + MarkerSet);
   		String icon = cs.getString("icon");   		
   		PermissionUser user = PermissionsEx.getUser(player);
   		
   		// /dmarker add id:<marker-id> <marker-label> icon:<icon-id> set:<markerset-id>
   		if(!user.has("dynmap.marker.add")){
   			user.addPermission("dynmap.marker.add");
   	    	log.info("[NuxFlags] Adding marker id : \"dmarker add id:" + MarkerID + " " + MarkerLabel + " icon:" + icon + " set:" + MarkerSet + "\" by " + player.getName());
   	    	player.performCommand("dmarker add id:" + MarkerID + " " + MarkerLabel + " icon:" + icon + " set:" + MarkerSet);
   		    user.removePermission("dynmap.marker.add");
   		}
   		else {
   		log.info("[NuxFlags] Adding marker id : \"dmarker add id:" + MarkerID + " " + MarkerLabel + " icon:" + icon + " set:" + MarkerSet + "\" by " + player.getName());
   	    player.performCommand("dmarker add id:" + MarkerID + " " + MarkerLabel + " icon:" + icon + " set:" + MarkerSet);
   		}

	}
	
	public void RemoveMarker(Player player, String MarkerSet, String MarkerID) {
		
		PermissionUser user = PermissionsEx.getUser(player);
		
		if(!user.has("dynmap.marker.delete")){
			user.addPermission("dynmap.marker.delete");
	    	log.info("[NuxFlags] Removing marker id : \"dmarker delete id:" + MarkerID + " set:" + MarkerSet + "\" by " + player.getName());
	    	player.performCommand("dmarker delete id:" + MarkerID + " set:" + MarkerSet);
	    	user.removePermission("dynmap.marker.delete");
		}
		else {
			log.info("[NuxFlags] Removing marker id : \"dmarker delete id:" + MarkerID + " set:" + MarkerSet + "\" by " + player.getName());
	    	player.performCommand("dmarker delete id:" + MarkerID + " set:" + MarkerSet);
		}

	}
	
}

/*
 * Copyright (C) 2012      Bueno Théo "Munrek"
 *
 *  NuxBridge is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package fr.nuxos.minecraft.nuxflags;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import fr.nuxos.minecraft.nuxflags.*;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import org.bukkit.event.Listener;

public class SignListener implements Listener {
	public Logger log;
    private Connection conn;
    private FileConfiguration config;
    public NuxFlags plugin;
    private Server serv;
    private Markers Markerer;
    
    public SignListener(NuxFlags plugin) {
    	plugin.getServer().getPluginManager().registerEvents(this, plugin);
    	log = plugin.getServer().getLogger();
    	config = plugin.getConfig();
    	serv = plugin.getServer();
    	Markerer = new Markers(plugin);
    }
    
    @EventHandler
    public void onSignChange(SignChangeEvent event) {
    	
    	String[] SignLines = event.getLines();
    	Player player = event.getPlayer(); 
    	
    	List<String> MSets = config.getStringList("MarkerSets");
    	PermissionUser user = PermissionsEx.getUser(player);
		
    	for (String M : MSets) {

            if(SignLines[0].equalsIgnoreCase("[" + M + "]")) {

            	if (SignLines[1].isEmpty()){

        	player.sendMessage(ChatColor.YELLOW + "[NuxFlags] You must provide a label at sign's second line.");
        	event.setCancelled(true);
        	}            	
            	else {
		if(user.has("nuxflags.add")){
        	String idpos = "NF_" + event.getBlock().getX() + "-" + event.getBlock().getY() + "-" + event.getBlock().getZ();
      		Markerer.addMarker(player, M, idpos, SignLines[1].replace(" ","_"));
		}
	    else {
	    player.sendMessage(ChatColor.YELLOW + "[NuxFlags] You can't do that !");
	    event.setCancelled(true);				
	    }
	 }
    	}
       }
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
    	Block block = event.getBlock();
    	Player player = event.getPlayer();
    	
    	if(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN) {
    		
    		Sign sign = (Sign) block.getState();
        	List<String> MSets = config.getStringList("MarkerSets");
        	PermissionUser user = PermissionsEx.getUser(player);
			
        	for (String M : MSets) {
        		
        		if(sign.getLine(0).equalsIgnoreCase("[" + M + "]")) {
			   if(user.has("nuxflags.remove")){
            		   String idpos = "NF_" + event.getBlock().getX() + "-" + event.getBlock().getY() + "-" + event.getBlock().getZ();
         		   Markerer.removeMarker(player, M, idpos);
			   }
			   else {
			   player.sendMessage(ChatColor.YELLOW + "[NuxFlags] You can't do that !");
			   event.setCancelled(true);
			   }

        		}
        	}
        	
    		
    	}  	

    }
}

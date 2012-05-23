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

import fr.nuxos.minecraft.nuxflags.*;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class NuxFlags extends JavaPlugin {
	
    public Logger log;
    
    private FileConfiguration customConfig = null;
    private File configFile = null;

    public void onEnable() {
    	
        log = this.getServer().getLogger();
        new SignListener(this);

        configFile = new File("plugins/NuxFlags/config.yml");
        
        if (configFile.exists()) {
        	customConfig = YamlConfiguration.loadConfiguration(configFile);
        } else {
            log.severe("[NuxFlags] File not found : plugins/NuxFlags/config.yml");
        }

        PluginDescriptionFile pdfFile = this.getDescription();
        log.info("[NuxFlags] " + pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
        
    }

    public void onDisable() {
       	log.severe("[NuxFlags] Disabling NuxFlags will cause the deleted signs won't remove markers on dynmap !");
    }
}

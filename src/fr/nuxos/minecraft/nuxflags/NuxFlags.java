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

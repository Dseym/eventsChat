package eventsChat;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main plugin;
	public static String tagPlugin;
	
	public void onEnable() {
		
		plugin = this;
		tagPlugin = ChatColor.RESET + "[" + ChatColor.BLUE + getName() + ChatColor.RESET + "] ";
		
		File config = new File(getDataFolder() + File.separator + "config.yml");
		
		if(!config.exists()) {
			
			try {
				
				config.createNewFile();
				
			} catch (IOException e) {}
			
		}
		
		FileConfiguration configFile = (FileConfiguration)YamlConfiguration.loadConfiguration(config);
		if(configFile.contains("events"))
			EventManager.listEvents = configFile.getStringList("events");
			
		this.getCommand("events").setExecutor((CommandExecutor)new Commands());
		this.getLogger().info("Started!");
		
	}
	
	
	public static void sendMessageAll(String mess) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.sendMessage(Main.tagPlugin + mess);
		
	}
	
}

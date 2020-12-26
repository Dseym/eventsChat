package ru.dseymo.eventsChat;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import lombok.Getter;
import ru.dseymo.eventsChat.bettings.BettingManager;
import ru.dseymo.eventsChat.executes.EventsExecutor;
import ru.dseymo.eventsChat.gameEvents.GameEventManager;
import ru.dseymo.eventsChat.listeners.ChatListener;
import ru.dseymo.eventsChat.listeners.PlayerListener;

public class Main extends JavaPlugin {
	
	@Getter
	private static Main instance;
	
	private EventsExecutor eventsExecutor;
	@Getter
	private boolean debug = false;
	
	public void onEnable() {
		
		instance = this;
		
		getDataFolder().mkdirs();
		eventsExecutor = new EventsExecutor(new File(getDataFolder() + File.separator + "config.yml"));
		
		Bukkit.getPluginManager().registerEvents(new ChatListener(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		BettingManager.getManager().setup(this);
		GameEventManager.getManager().setup(this);
		
		this.getCommand("events").setExecutor(eventsExecutor);
		this.getLogger().info("Started!");
		
	}
	
	public void onDisable() {
		
		eventsExecutor.finalize();
		
	}
	
}

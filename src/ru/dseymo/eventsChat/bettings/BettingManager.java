package ru.dseymo.eventsChat.bettings;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import lombok.Getter;
import ru.dseymo.eventsChat.events.NewBettingEvent;

public class BettingManager {
	
	@Getter
	private static BettingManager manager = new BettingManager();
	
	@Getter
	private HashMap<String, Betting> bettings = new HashMap<>();
	
	public void setup(Plugin plugin) {}
	
	public void newBetting(Betting betting) {
		
		if(isBetting(betting.getName())) return;
		
		NewBettingEvent event = new NewBettingEvent(betting);
		Bukkit.getPluginManager().callEvent(event);
		
		if(event.isCancelled()) return;
		
		betting.activate();
		
		bettings.put(betting.getName(), betting);
		
	}
	
	public boolean isBetting(String name) {return bettings.containsKey(name);}
	public void removeBetting(String name) {bettings.remove(name).finalize();}
	public void removeBetting(Betting betting) {removeBetting(betting.getName());}
	
}

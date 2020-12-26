package ru.dseymo.eventsChat.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.spectators.Spectator;

public class ChatEvent extends Event {

	private static HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
		
        return handlers;
        
    }
	
    
	@Getter @Setter
	private boolean command = false;
	@Getter
	private Spectator spectator;
	@Getter @Setter
	private String message;
	@Getter
	private ArrayList<UUID> targetPlayers = new ArrayList<>();
	
    public ChatEvent(Spectator spectator, String message, boolean command) {
    	
    	this.spectator = spectator;
    	this.message = message;
    	this.command = command;
    	for(Player p: Bukkit.getOnlinePlayers())
    		targetPlayers.add(p.getUniqueId());
    	
    }
    
    public HandlerList getHandlers() {
    	
        return handlers;
        
    }
    
}

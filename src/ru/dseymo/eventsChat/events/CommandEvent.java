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

public class CommandEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
		
        return handlers;
        
    }
	
    
	@Getter @Setter
	private boolean cancelled = false;
	@Getter
	private Spectator spectator;
	@Getter
	private String command;
	@Getter
	private String[] args;
	@Getter
	private ArrayList<UUID> targetPlayers = new ArrayList<>();
	
    public CommandEvent(Spectator spectator, String command, String[] args) {
    	
    	this.spectator = spectator;
    	this.command = command;
    	this.args = args;
    	for(Player p: Bukkit.getOnlinePlayers())
    		targetPlayers.add(p.getUniqueId());
    	
    }
    
    public HandlerList getHandlers() {
    	
        return handlers;
        
    }
	
}

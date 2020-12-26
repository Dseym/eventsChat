package ru.dseymo.eventsChat.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;

public class CallGameEventEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
		
        return handlers;
        
    }
	
    
	@Getter @Setter
	private boolean cancelled = false, resetToDefault = false;
	@Getter
	private Spectator spectator;
	@Getter @Setter
	private GameEvent event;
	@Getter @Setter
	private ArrayList<UUID> targetPlayers = new ArrayList<>();
	
    public CallGameEventEvent(Spectator spectator, GameEvent event) {
    	
    	this.spectator = spectator;
    	this.event = event;
    	for(Player p: Bukkit.getOnlinePlayers())
    		targetPlayers.add(p.getUniqueId());
    	
    }
    
    public HandlerList getHandlers() {
    	
        return handlers;
        
    }
    
}

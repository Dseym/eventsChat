package ru.dseymo.eventsChat.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.spectators.Spectator;

public class NewSpectatorEvent extends Event {
	
	private static HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
		
        return handlers;
        
    }
	
	
	@Getter @Setter
	private boolean cancelled = false;
	@Getter
	private Spectator spectator;
    
    public NewSpectatorEvent(Spectator spectator) {
    	
    	this.spectator = spectator;
    	
    }
    
    public HandlerList getHandlers() {
    	
        return handlers;
        
    }
	
}

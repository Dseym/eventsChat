package ru.dseymo.eventsChat.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.bettings.Betting;

public class NewBettingEvent extends Event {

	private static HandlerList handlers = new HandlerList();
	
	public static HandlerList getHandlerList() {
		
        return handlers;
        
    }
	
	
	@Getter
	private Betting betting;
	@Getter @Setter
	private boolean cancelled;
	
    public NewBettingEvent(Betting betting) {
    	
    	this.betting = betting;
    	
    }
    
    public HandlerList getHandlers() {
    	
        return handlers;
        
    }

}

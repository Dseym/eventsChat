package ru.dseymo.eventsChat.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import ru.dseymo.eventsChat.bettings.BettingManager;
import ru.dseymo.eventsChat.bettings.bettings.PlayerDied;

public class PlayerListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageEvent e) {
		
		if(e.getEntityType() != EntityType.PLAYER) return;
		
		BettingManager.getManager().newBetting(new PlayerDied());
		
	}
	
}

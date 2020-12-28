package ru.dseymo.eventsChat.listeners;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import ru.dseymo.eventsChat.bettings.BettingManager;
import ru.dseymo.eventsChat.bettings.bettings.FindDiamonds;
import ru.dseymo.eventsChat.bettings.bettings.PlayerDied;

public class PlayerListener implements Listener {
	
	@EventHandler
	private void damage(EntityDamageEvent e) {
		
		if(e.getEntityType() != EntityType.PLAYER || e.getDamage() < 2 || ((Player)e.getEntity()).getHealth() > 8) return;
		
		BettingManager.getManager().newBetting(new PlayerDied());
		
	}
	
	@EventHandler
	private void findDiamond(BlockBreakEvent e) {
		
		if(e.getBlock().getType() != Material.DIAMOND_ORE || Math.random() > 0.5) return;
		
		BettingManager.getManager().newBetting(new FindDiamonds());
		
	}
	
}

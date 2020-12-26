package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class IronBlocks extends GameEvent {
	
	public IronBlocks() {
		super("iron", "Вот тебе немного железа", 111);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation().clone().add(1, 1, 0);
			new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()).getBlock().setType(Material.IRON_BLOCK);
			new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()+1).getBlock().setType(Material.IRON_BLOCK);
			new Location(loc.getWorld(), loc.getX()+1, loc.getY(), loc.getZ()+1).getBlock().setType(Material.IRON_BLOCK);
			new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ()).getBlock().setType(Material.IRON_BLOCK);
			
		}
		
	}
	
}

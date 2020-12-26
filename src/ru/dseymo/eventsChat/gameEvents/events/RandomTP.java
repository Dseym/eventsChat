package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class RandomTP extends GameEvent {

	public RandomTP() {
		super("tp", "Ой, ты куда?", 345);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			List<Location> blocksLoc = new ArrayList<Location>();
			for(int x = -100; x < 100; x++)
				for(int y = -25; y < 25; y++)
					for(int z = -100; z < 100; z++) {
						
						Location locTP = new Location(loc.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y-1, loc.getBlockZ()+z);
						
						Material blockDown = locTP.getBlock().getType();
						Material blockHead = new Location(loc.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y+1, loc.getBlockZ()+z).getBlock().getType();
						Material blockLegs = new Location(loc.getWorld(), loc.getBlockX()+x, loc.getBlockY()+y, loc.getBlockZ()+z).getBlock().getType();
						
						if(blockDown == Material.AIR || blockDown == Material.WATER || blockDown == Material.LAVA ||
						   blockHead != Material.AIR ||
						   blockLegs != Material.AIR)
							continue;
						
						blocksLoc.add(locTP);
						
					}
			
			if(blocksLoc.size() == 0)
				continue;
			
			p.teleport(blocksLoc.get((int)Math.floor(Math.random()*blocksLoc.size())));
			
		}
		
	}

}

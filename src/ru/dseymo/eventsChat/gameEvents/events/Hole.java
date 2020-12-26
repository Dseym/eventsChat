package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Hole extends GameEvent {

	public Hole() {
		super("hole", "Смотри под ноги!", 260);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			for(int x = 0; x < 3; x++)
				for(int y = 0; y < 10; y++)
					for(int z = 0; z < 3; z++) {
						
						Block block = new Location(loc.getWorld(), loc.getBlockX()+x-1, loc.getBlockY()-y-1, loc.getBlockZ()+z-1).getBlock();
						if(block.getType() != Material.BEDROCK)
							block.setType(Material.AIR);
						
					}
		}
		
	}
	
}

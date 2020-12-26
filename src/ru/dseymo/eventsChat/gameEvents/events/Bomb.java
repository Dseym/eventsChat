package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Bomb extends GameEvent {
	
	public Bomb() {
		super("bomb", "Смотри не взорвись!", 360);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			loc.getWorld().spawnEntity(loc, EntityType.ENDER_CRYSTAL);
			for(int x = 0; x < 5; x++)
				for(int y = 0; y < 5; y++)
					for(int z = 0; z < 5; z++)
						new Location(loc.getWorld(), loc.getBlockX()+x-2, loc.getBlockY()+y-1, loc.getBlockZ()+z-2).getBlock().setType(Material.WEB);
			
		}
		
	}
	
}

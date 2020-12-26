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

public class Lava extends GameEvent {

	public Lava() {
		super("lava", "Горячо стало!", 300);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc1 = p.getLocation().clone();
			loc1.add(2, 1, 2);
			Location loc2 = p.getLocation().clone();
			loc2.add(-2, 1, -2);
			Location loc3 = p.getLocation().clone();
			loc3.add(-2, 1, 2);
			Location loc4 = p.getLocation().clone();
			loc4.add(2, 1, -2);
			loc1.getBlock().setType(Material.LAVA);
			loc2.getBlock().setType(Material.LAVA);
			loc3.getBlock().setType(Material.LAVA);
			loc4.getBlock().setType(Material.LAVA);
			
		}
		
	}
	
}

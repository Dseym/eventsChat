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

public class Anvil extends GameEvent {

	public Anvil() {
		super("anvil", "Берегись!", 130);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			for(int i = 0; i < 5; i++)
				for(int i2 = 0; i2 < 5; i2++)
					new Location(loc.getWorld(), loc.getBlockX()+i-2, loc.getBlockY()+4, loc.getBlockZ()+i2-2).getBlock().setType(Material.ANVIL);
			
		}
		
	}

}

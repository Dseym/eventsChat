package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import ru.dseymo.eventsChat.Main;
import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Error extends GameEvent {
	
	public Error() {
		super("error", "ОШ?$К@! ^Ш?$К@! &k^Ш?$К@!&7", 404);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				for(UUID uuid: targets) {
					
					Player p = Bukkit.getPlayer(uuid);
					
					p.sendTitle("", ChatColor.translateAlternateColorCodes('&', "&" + (i/3) + "&k^Ш?$К@!"), 1, 0, 1);
					
					Chat.info(p, "&" + (i/4) + message + " (" + spec.getNick() + ")");
					
					Location loc = p.getLocation().clone();
					
					Random r = new Random();
					loc.setPitch(r.nextFloat()*360);
					loc.setYaw(r.nextFloat()*360);
					p.teleport(loc.add(r.nextDouble()*2-1, r.nextDouble()*2-1, r.nextDouble()*2-1));
					
					if(++i > 29)
						cancel();
					
				}
				
			}
			
		}.runTaskTimer(Main.getInstance(), 0, 2);
		
	}
	
}

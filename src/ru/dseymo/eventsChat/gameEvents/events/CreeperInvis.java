package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class CreeperInvis extends GameEvent {

	public CreeperInvis() {
		super("creeper", "Невидимый крипер!?", 150);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			Creeper ent = (Creeper)loc.getWorld().spawnEntity(loc, EntityType.CREEPER);
			ent.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 9999, 0));
			ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 5));
			ent.setMaxFuseTicks(60);
			
		}
		
	}
	
}

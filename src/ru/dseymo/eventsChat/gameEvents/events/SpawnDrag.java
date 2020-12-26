package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.EnderDragon.Phase;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class SpawnDrag extends GameEvent {

	public SpawnDrag() {
		super("dragon", "Смотри, яйцо треснуло!", 1000);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			EnderDragon drag = (EnderDragon)p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDER_DRAGON);
			drag.setPhase(Phase.CHARGE_PLAYER);
			
		}
		
	}

}

package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class MinusHeart extends GameEvent {

	public MinusHeart() {
		super("-heart", "Тебе разбили сердце :(", 150);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			if(p.getMaxHealth() > 2)
				p.setMaxHealth(p.getMaxHealth() - 2);
			
		}
		
	}

}

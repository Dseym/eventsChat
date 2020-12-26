package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Poisoning extends GameEvent {

	public Poisoning() {
		super("poison", "Зря ты съел тот беляш", 135);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 15*20, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 17*20, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 15*20, 0));
			
		}
		
	}

}

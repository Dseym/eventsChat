package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

import ru.dseymo.eventsChat.Main;
import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Hardcore extends GameEvent implements Listener {

	public Hardcore() {
		super("hardcore", "Хардкор теперь %enable%!", 777);
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
		
	}
	
	private ArrayList<UUID> uuids = new ArrayList<>();
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			if(uuids.contains(p.getUniqueId())) {
				
				uuids.remove(p.getUniqueId());
				Chat.info(p, message.replace("%enable%", "ВЫКЛ") + " (" + spec.getNick() + ")");
				
			} else {
				
				uuids.add(p.getUniqueId());
				Chat.info(p, message.replace("%enable%", "ВКЛ") + " (" + spec.getNick() + ")");
				
			}
			
		}
		
	}
	
	
	@EventHandler
	private void regen(EntityRegainHealthEvent e) {
		
		if(!uuids.contains(e.getEntity().getUniqueId())) return;
		
		if(Arrays.asList(RegainReason.EATING, RegainReason.SATIATED).contains(e.getRegainReason()))
			e.setCancelled(true);
		
	}
	
}

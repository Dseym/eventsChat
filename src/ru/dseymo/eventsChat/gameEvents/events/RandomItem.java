package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class RandomItem extends GameEvent {

	public RandomItem() {
		super("item", "Лови предмет!", 115);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			try {
				
				p.getInventory().addItem(new ItemStack(Material.values()[(int) Math.floor(Math.random() * Material.values().length)], 1));
			
			} catch (Exception e) {
				
				p.getInventory().addItem(new ItemStack(Material.APPLE, 1));
				
			}
			
		}
		
	}
	
}

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

public class AppleNotch extends GameEvent {
	
	public AppleNotch() {
		super("notch", "Держи яблоко Нотча!", 333);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			p.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short)1, (byte)1));
			
		}
		
	}
	
}

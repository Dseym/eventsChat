package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class DropItem extends GameEvent {

	public DropItem() {
		super("drop", "Ой!", 100);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			Location loc = p.getLocation();
			loc.setY(loc.getY()+1);
			ItemStack item = p.getInventory().getItemInMainHand();
			if(item == null || item.getType() == Material.AIR) return;
			
			p.getWorld().dropItem(loc, item).setVelocity(loc.getDirection());
			item.setType(Material.AIR);
			p.getInventory().setItemInMainHand(item);
			
		}
		
	}

}

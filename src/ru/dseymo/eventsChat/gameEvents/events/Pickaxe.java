package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class Pickaxe extends GameEvent {
	
	public Pickaxe() {
		super("pickaxe", "Самая быстрая кирка на диком западе!", 222);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			ItemStack item = new ItemStack(Material.GOLD_PICKAXE);
			item.addEnchantment(Enchantment.DIG_SPEED, 5);
			p.getInventory().addItem(item);
			
		}
		
	}
	
}

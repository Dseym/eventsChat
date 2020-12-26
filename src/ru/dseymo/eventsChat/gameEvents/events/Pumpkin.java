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

public class Pumpkin extends GameEvent {

	public Pumpkin() {
		super("pumpkin", "Попробуй снять эту тыкву :D", 333);
	}
	
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			Chat.info(p, message + " (" + spec.getNick() + ")");
			
			p.getInventory().setHelmet(new ItemStack(Material.PUMPKIN));
			p.getInventory().getHelmet().addEnchantment(Enchantment.BINDING_CURSE, 1);
			
		}
		
	}
	
}

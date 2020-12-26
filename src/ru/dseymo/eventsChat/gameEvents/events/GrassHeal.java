package ru.dseymo.eventsChat.gameEvents.events;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ru.dseymo.eventsChat.Main;
import ru.dseymo.eventsChat.gameEvents.GameEvent;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class GrassHeal extends GameEvent implements Listener {
	
	public GrassHeal() {
		super("grass", "Травушка %enable%", 310);
		
		Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
		
	}
	
	private ArrayList<UUID> uuids = new ArrayList<>();
	@Override
	public void call(Spectator spec, ArrayList<UUID> targets) {
		
		for(UUID uuid: targets) {
			
			Player p = Bukkit.getPlayer(uuid);
			
			if(uuids.contains(p.getUniqueId())) {
				
				uuids.remove(p.getUniqueId());
				Chat.info(p, message.replace("%enable%", "теперь не поможет") + " (" + spec.getNick() + ")");
				
			} else {
				
				uuids.add(p.getUniqueId());
				Chat.info(p, message.replace("%enable%", "поможет") + " (" + spec.getNick() + ")");
				
			}
			
		}
		
	}
	
	@EventHandler
	private void heal(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		Block down = e.getTo().getBlock().getRelative(BlockFace.DOWN);
		if(uuids.contains(p.getUniqueId()) && down.getType() == Material.GRASS && p.getPotionEffect(PotionEffectType.REGENERATION) == null)
			p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 2));
		
	}
	
}

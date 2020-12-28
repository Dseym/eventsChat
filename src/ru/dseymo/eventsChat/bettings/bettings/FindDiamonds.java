package ru.dseymo.eventsChat.bettings.bettings;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;

import ru.dseymo.eventsChat.Main;
import ru.dseymo.eventsChat.bettings.Betting;

public class FindDiamonds extends Betting {

	public FindDiamonds() {
		super("diamonds", "Найдет ли игрок %count% алмазов?", Main.getInstance(), 60);
	}
	
	private int count = 1, find = 0;
	@EventHandler
	private void find(BlockBreakEvent e) {
		
		if(!targets.contains(e.getPlayer().getUniqueId()) || e.getBlock().getType() != Material.DIAMOND_ORE) return;
		
		find++;
		
		setTitle(getDescription().replace("%count%", (count-find) + ""));
		
		if(find > count-1)
			result(true);
		
	}
	
	
	@Override
	protected void abstActivate() {
		
		count = new Random().nextInt(6)+5;
		setTitle(getDescription().replace("%count%", (count-find) + ""));
		
	}
	
	
	@Override
	protected void abstFinalize() {
		
		BlockBreakEvent.getHandlerList().unregister(this);
		
	}

}

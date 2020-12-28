package ru.dseymo.eventsChat.bettings.bettings;

import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import ru.dseymo.eventsChat.Main;
import ru.dseymo.eventsChat.bettings.Betting;

public class PlayerDied extends Betting {

	public PlayerDied() {
		super("died", "Умрет ли игрок?", Main.getInstance(), 30);
	}
	
	@EventHandler
	private void died(PlayerDeathEvent e) {
		
		if(targets.contains(e.getEntity().getUniqueId()))
			result(true);
		
	}
	
	@Override
	protected void abstActivate() {}
	
	
	@Override
	public void abstFinalize() {
		
		PlayerDeathEvent.getHandlerList().unregister(this);
		
	}

}

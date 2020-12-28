package ru.dseymo.eventsChat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.eventsChat.bettings.Betting;
import ru.dseymo.eventsChat.bettings.BettingManager;
import ru.dseymo.eventsChat.events.CommandEvent;
import ru.dseymo.eventsChat.gameEvents.GameEventManager;
import ru.dseymo.eventsChat.spectators.Platform;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class CommandListener implements Listener {
	
	@EventHandler
	private void command(CommandEvent e) {
		
		String command = e.getCommand();
		String[] args = e.getArgs();
		Spectator spec = e.getSpectator();
		Platform platf = spec.getPlatform();
		
		if(command.equalsIgnoreCase("event")) {
			
			if(args.length > 0)
				GameEventManager.getManager().callEvent(spec, args[0]);
		
		} else if(command.equalsIgnoreCase("money")) {
			
			Chat.message(e.getTargetPlayers(), platf.getPrefix() + spec.getNick() + platf.getSuffix() + " твой баланс: " + spec.getMoney());
			
		} else if(command.equalsIgnoreCase("bet")) {
			
			if(args.length > 2) {
				
				Betting bet = BettingManager.getManager().getBettings().get(args[0]);
				if(bet == null) return;
				
				try {
					
					if (args[1].startsWith("yes"))
						bet.addYes(spec, Integer.parseInt(args[2]));
					else if (args[1].startsWith("no"))
						bet.addNo(spec, Integer.parseInt(args[2]));
					
				} catch (Exception e2) {}
				
			}
			
		}
		
	}
	
}

package ru.dseymo.eventsChat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import ru.dseymo.eventsChat.bettings.Betting;
import ru.dseymo.eventsChat.bettings.BettingManager;
import ru.dseymo.eventsChat.events.ChatEvent;
import ru.dseymo.eventsChat.spectators.Platform;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public class ChatListener implements Listener {
	
	@EventHandler
	private void newMess(ChatEvent e) {
		
		Spectator spec = e.getSpectator();
		Platform platf = spec.getPlatform();
		String mess = e.getMessage();
		
		if(mess.startsWith("!money")) {
			
			e.setCommand(true);
			Chat.message(e.getTargetPlayers(), platf.getPrefix() + spec.getNick() + platf.getSuffix() + " твой баланс: " + spec.getMoney());
			
			return;
			
		} else if(mess.startsWith("!bet ")) {
			
			e.setCommand(true);
			String[] args = mess.split("!bet ")[1].split(" ");
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
			
			return;
			
		}
		
		if(!e.isCommand()) {
			
			spec.depositMoney(mess.length()/10 + 10);
			
			Chat.message(e.getTargetPlayers(), platf.getPrefix() + spec.getNick() + platf.getSuffix() + ": " + e.getMessage());
			
		}
		
	}
	
}

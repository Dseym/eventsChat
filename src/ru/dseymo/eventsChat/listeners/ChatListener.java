package ru.dseymo.eventsChat.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

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
		
		spec.depositMoney(mess.length()/10 + 10);
		
		Chat.message(e.getTargetPlayers(), platf.getPrefix() + spec.getNick() + platf.getSuffix() + ": " + e.getMessage());
		
	}
	
}

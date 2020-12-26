package ru.dseymo.eventsChat.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;

import ru.dseymo.eventsChat.events.ChatEvent;
import ru.dseymo.eventsChat.events.NewSpectatorEvent;
import ru.dseymo.eventsChat.gameEvents.GameEventManager;
import ru.dseymo.eventsChat.spectators.Platform;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.youtubeStream.IMessagesListener;

public class MessageYouTubeListener implements IMessagesListener {

	private HashMap<String, Spectator> spectators = new HashMap<>();
	private String channel;
	
	public MessageYouTubeListener(String channel) {
		
		this.channel = channel;
		
	}
	
	@Override
	public void onMessage(String nick, String mess) {
		
		if(!spectators.containsKey(nick)) {
			
			Spectator spectator = new Spectator(nick, channel, Platform.YOUTUBE);
			
			NewSpectatorEvent event = new NewSpectatorEvent(spectator);
			Bukkit.getPluginManager().callEvent(event);
			if(event.isCancelled()) return;
			
			spectators.put(nick, spectator);
			
		}
		
		Spectator spectator = spectators.get(nick);
		
		boolean isCommand = false;
		if(mess.startsWith("!event ")) {
			
			isCommand = true;
			
			String[] args = mess.split("!event ")[1].split(" ");
			if(args.length != 0)
				GameEventManager.getManager().callEvent(spectator, args[0]);
			
		}
		
		ChatEvent event = new ChatEvent(spectator, mess, isCommand);
		Bukkit.getPluginManager().callEvent(event);
		
	}

}

package ru.dseymo.eventsChat.listeners;

import java.util.HashMap;

import org.bukkit.Bukkit;

import ru.dseymo.eventsChat.events.ChatEvent;
import ru.dseymo.eventsChat.events.CommandEvent;
import ru.dseymo.eventsChat.events.NewSpectatorEvent;
import ru.dseymo.eventsChat.spectators.Platform;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.twitchStream.IMessagesListener;

public class MessageTwitchListener implements IMessagesListener {
	
	private HashMap<String, Spectator> spectators = new HashMap<>();
	private String channel;
	
	public MessageTwitchListener(String channel) {
		
		this.channel = channel;
		
	}
	
	@Override
	public void onMessage(String nick, String mess) {
		
		if(!spectators.containsKey(nick)) {
			
			Spectator spectator = new Spectator(nick, channel, Platform.TWITCH);
			
			NewSpectatorEvent event = new NewSpectatorEvent(spectator);
			Bukkit.getPluginManager().callEvent(event);
			if(event.isCancelled()) return;
			
			spectators.put(nick, spectator);
			
		}
		
		Bukkit.getPluginManager().callEvent(new ChatEvent(spectators.get(nick), mess));
		
	}

	@Override
	public void onCommand(String nick, String command, String[] args) {
		
		if(!spectators.containsKey(nick)) {
			
			Spectator spectator = new Spectator(nick, channel, Platform.TWITCH);
			
			NewSpectatorEvent event = new NewSpectatorEvent(spectator);
			Bukkit.getPluginManager().callEvent(event);
			if(event.isCancelled()) return;
			
			spectators.put(nick, spectator);
			
		}
		
		Bukkit.getPluginManager().callEvent(new CommandEvent(spectators.get(nick), command, args));
		
	}

}

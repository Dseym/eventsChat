package ru.dseymo.eventsChat.utils;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lombok.Getter;

public class Chat {
	
	@Getter
	private static String prefix = "&7[&9&lEventsChat&7]&r ";
	
    private static String color(String text) {return ChatColor.translateAlternateColorCodes('&', text);}
    
    public static void message(CommandSender sender, String message) {
    	
        sender.sendMessage(color(prefix + message));
        
    }
    
    public static void success(CommandSender sender, String message) {
    	
        sender.sendMessage(color(prefix + "&2" + message));
        
    }

    public static void fail(CommandSender sender, String message) {
    	
        sender.sendMessage(color(prefix + "&4" + message));
        
    }
    
    public static void noPrefix(CommandSender sender, String... messages) {
    	
        for (String message : messages)
        	sender.sendMessage(color("&7" + message));
        
    }
    
    public static void info(CommandSender sender, String... messages) {
    	
        for (String message : messages)
        	sender.sendMessage(color(prefix + "&7" + message));
        
    }
    
    public static void message(ArrayList<UUID> uuids, String... messages) {
    	
    	for(UUID uuid: uuids) {
    		
    		Player p = Bukkit.getPlayer(uuid);
    		if(p == null) continue;
    		noPrefix(p, messages);
    		
    	}
    	
    }
	
}

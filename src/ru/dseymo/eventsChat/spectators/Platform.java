package ru.dseymo.eventsChat.spectators;

import org.bukkit.ChatColor;

import lombok.Getter;

public enum Platform {
	
	TWITCH("Twitch", "&8[&5Twitch&8]&2 ", "&f"),
	YOUTUBE("YouTube", "&8[&fYou&cTube&8]&2 ", "&f");
	
	@Getter
	private String name, prefix, suffix;
	
	Platform(String name, String prefix, String suffix) {
		
		this.name = name;
		this.prefix = ChatColor.translateAlternateColorCodes('&', prefix);
		this.suffix = ChatColor.translateAlternateColorCodes('&', suffix);
		
	}
	
}

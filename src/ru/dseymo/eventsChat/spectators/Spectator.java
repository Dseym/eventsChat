package ru.dseymo.eventsChat.spectators;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.Main;

public class Spectator {
	
	@Getter
	private String nick, channel;
	@Getter
	private Platform platform;
	@Getter @Setter
	private int money;
	
	public Spectator(String nick, String channel, Platform platform) {
		
		this.nick = nick;
		this.channel = channel;
		this.platform = platform;
		money = 0;
		
	}
	
	public void depositMoney(int amount) {money += amount;}
	public void withdrawMoney(int amount) {money -= amount;}
	public boolean checkMoney(int amount) {
		
		if(Main.getInstance().isDebug())
			return true;
		
		return money+1 > amount;
		
	}
	
}

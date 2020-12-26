package ru.dseymo.eventsChat.gameEvents;

import java.util.ArrayList;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import ru.dseymo.eventsChat.spectators.Spectator;

public abstract class GameEvent {
	
	@Getter
	protected String name;
	@Getter @Setter
	protected String message;
	@Getter @Setter
	protected int cost;
	@Getter @Setter
	protected boolean enable = true;
	
	public GameEvent(String name, String message, int cost) {
		
		this.name = name;
		defMessage = message;
		defCost = cost;
		toDefault();
		
	}
	
	private String defMessage;
	private int defCost;
	public void toDefault() {
		
		message = defMessage;
		cost = defCost;
		
	}
	
	public abstract void call(Spectator spec, ArrayList<UUID> targets);
	
}

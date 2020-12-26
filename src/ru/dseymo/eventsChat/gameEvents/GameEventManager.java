package ru.dseymo.eventsChat.gameEvents;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import ru.dseymo.eventsChat.events.CallGameEventEvent;
import ru.dseymo.eventsChat.gameEvents.events.Anvil;
import ru.dseymo.eventsChat.gameEvents.events.AppleNotch;
import ru.dseymo.eventsChat.gameEvents.events.Bomb;
import ru.dseymo.eventsChat.gameEvents.events.CreeperInvis;
import ru.dseymo.eventsChat.gameEvents.events.DropItem;
import ru.dseymo.eventsChat.gameEvents.events.Error;
import ru.dseymo.eventsChat.gameEvents.events.GiveEat;
import ru.dseymo.eventsChat.gameEvents.events.GiveTotem;
import ru.dseymo.eventsChat.gameEvents.events.GrassHeal;
import ru.dseymo.eventsChat.gameEvents.events.Hardcore;
import ru.dseymo.eventsChat.gameEvents.events.Hello;
import ru.dseymo.eventsChat.gameEvents.events.Hole;
import ru.dseymo.eventsChat.gameEvents.events.IronBlocks;
import ru.dseymo.eventsChat.gameEvents.events.Lava;
import ru.dseymo.eventsChat.gameEvents.events.MinusHeart;
import ru.dseymo.eventsChat.gameEvents.events.Obsidian;
import ru.dseymo.eventsChat.gameEvents.events.Pickaxe;
import ru.dseymo.eventsChat.gameEvents.events.PlusHeart;
import ru.dseymo.eventsChat.gameEvents.events.Poisoning;
import ru.dseymo.eventsChat.gameEvents.events.Pumpkin;
import ru.dseymo.eventsChat.gameEvents.events.RandomItem;
import ru.dseymo.eventsChat.gameEvents.events.RandomTP;
import ru.dseymo.eventsChat.gameEvents.events.SpawnDrag;
import ru.dseymo.eventsChat.gameEvents.events.SpawnWither;
import ru.dseymo.eventsChat.gameEvents.events.Zeus;
import ru.dseymo.eventsChat.spectators.Spectator;

public class GameEventManager {
	
	@Getter
	private static GameEventManager manager = new GameEventManager();
	
	@Getter
	private HashMap<String, GameEvent> events = new HashMap<>();
	private Plugin plugin;
	
	public void setup(Plugin plugin) {
		
		this.plugin = plugin;
		
		registerEvent(new Anvil());
		registerEvent(new AppleNotch());
		registerEvent(new Bomb());
		registerEvent(new CreeperInvis());
		registerEvent(new DropItem());
		registerEvent(new Error());
		registerEvent(new GiveEat());
		registerEvent(new GiveTotem());
		registerEvent(new GrassHeal());
		registerEvent(new Hardcore());
		registerEvent(new Hello());
		registerEvent(new Hole());
		registerEvent(new IronBlocks());
		registerEvent(new Lava());
		registerEvent(new MinusHeart());
		registerEvent(new Obsidian());
		registerEvent(new Pickaxe());
		registerEvent(new PlusHeart());
		registerEvent(new Poisoning());
		registerEvent(new Pumpkin());
		registerEvent(new RandomItem());
		registerEvent(new RandomTP());
		registerEvent(new SpawnDrag());
		registerEvent(new SpawnWither());
		registerEvent(new Zeus());
		
	}
	
	public void registerEvent(GameEvent event) {
		
		events.put(event.getName(), event);
		
	}
	
	public void callEvent(Spectator spec, String name) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				GameEvent event = events.get(name);
				if(event != null && event.isEnable()) {
					
					if(!spec.checkMoney(event.getCost())) return;
					
					CallGameEventEvent callevent = new CallGameEventEvent(spec, event);
					Bukkit.getPluginManager().callEvent(callevent);
					if(callevent.isCancelled()) return;
					
					spec.withdrawMoney(event.getCost());
					event.call(spec, callevent.getTargetPlayers());
					
					if(callevent.isResetToDefault()) event.toDefault();
					
				}
				
			}
			
		}.runTask(plugin);
		
	}
	
}

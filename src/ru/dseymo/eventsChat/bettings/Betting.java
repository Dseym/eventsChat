package ru.dseymo.eventsChat.bettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import lombok.Getter;
import ru.dseymo.eventsChat.spectators.Platform;
import ru.dseymo.eventsChat.spectators.Spectator;
import ru.dseymo.eventsChat.utils.Chat;

public abstract class Betting implements Listener {
	
	private static String prefix = "&8[&3Ставки&8]&9 ";
	
	@Getter
	private String name, description;
	private BossBar bar;
	private HashMap<Spectator, Integer> yes = new HashMap<>(), no = new HashMap<>();
	@Getter
	protected ArrayList<UUID> targets = new ArrayList<>();
	private Plugin plugin;
	private int maxTime, time;
	private BukkitTask timer;
	
	public Betting(String name, String description, Plugin plugin, int time) {
		
		this.name = name;
		this.description = description;
		this.plugin = plugin;
		this.maxTime = time;
		this.time = time;
		for(Player p: Bukkit.getOnlinePlayers())
			targets.add(p.getUniqueId());
		
	}
	
	private boolean activated = false;
	public void activate() {
		
		if(activated) return;
		
		activated = true;
		
		bar = Bukkit.createBossBar(name + " -> " + description, BarColor.BLUE, BarStyle.SOLID);
		for(UUID uuid: targets)
			bar.addPlayer(Bukkit.getPlayer(uuid));
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
		
		timer = new BukkitRunnable() {
			
			@Override
			public void run() {
				
				bar.setProgress((double)time / (double)maxTime);
				if(--time < 1) result(false);
				
			}
			
		}.runTaskTimer(plugin, 20, 20);
		
		Chat.message(targets, prefix + name + "&e: начались ставки! " + description);
		
	}
	
	public void addYes(Spectator spec, int money) {
		
		Platform platf = spec.getPlatform();
		if(isSpectorBetting(spec)) {
			
			Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " -> " + name + ": ты уже участвуешь");
			return;
			
		}
		
		if(!spec.checkMoney(money)) {
			
			Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " недостаточно монет");
			return;
			
		}
		
		Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " -> " + name + ": &oпоставил &3&o" + money + " &a&oза");
		spec.withdrawMoney(money);
		
		yes.put(spec, money);
		
	}
	
	public void addNo(Spectator spec, int money) {
		
		Platform platf = spec.getPlatform();
		if(isSpectorBetting(spec)) {
			
			Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " -> " + name + ": ты уже участвуешь");
			return;
			
		}
		
		if(!spec.checkMoney(money)) {
			
			Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " недостаточно монет");
			return;
			
		}
		
		Chat.message(targets, platf.getPrefix() + spec.getNick() + platf.getSuffix() + " -> " + name + ": &oпоставил &3&o" + money + " &c&oпротив");
		spec.withdrawMoney(money);
		
		no.put(spec, money);
		
	}
	
	public boolean isSpectorBetting(Spectator spec) {return yes.containsKey(spec) || no.containsKey(spec);}
	public int getBank() {return getBankYes() + getBankNo();}
	
	public int getBankYes() {
		
		int amount = 0;
		for(Integer money: yes.values()) amount += money;
		
		return amount;
		
	}
	
	public int getBankNo() {
		
		int amount = 0;
		for(Integer money: no.values()) amount += money;
		
		return amount;
		
	}
	
	public void result(boolean result) {
		
		if(result) {
			
			double k;
			if(getBank() != 0 && getBankYes() != 0) k = getBank() / getBankYes();
			else k = 1;
			
			Chat.message(targets, prefix + name + "&e: победила ставка &aза&e. Общий банк: " + getBank());
			
			for(Entry<Spectator, Integer> set: yes.entrySet())
				set.getKey().depositMoney((int)(((double)set.getValue()) * k));
			
		} else {
			
			double k;
			if(getBank() != 0 && getBankNo() != 0) k = getBank() / getBankNo();
			else k = 1;
			
			Chat.message(targets, prefix + name + "&e: победила ставка &cпротив&e. Общий банк: " + getBank());
			
			for(Entry<Spectator, Integer> set: no.entrySet())
				set.getKey().depositMoney((int)(((double)set.getValue()) * k));
			
		}
		
		BettingManager.getManager().removeBetting(this);
		
	}
	
	
	@Override
	public void finalize() {
		
		if(bar != null) bar.removeAll();
		targets.clear();
		yes.clear();
		no.clear();
		timer.cancel();
		
		bar = null;
		
		abstFinalize();
		
	}
	
	public abstract void abstFinalize();
	
}

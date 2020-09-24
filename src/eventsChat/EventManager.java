package eventsChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import streamInfoTwitch.Twitch;
import streamInfoYoutube.YouTube;

public class EventManager implements Runnable {

	protected static YouTube youtube;
	protected static Twitch twitch;
	protected static List<String> listEvents = new ArrayList<String>();
	protected static BukkitTask timerEvents;
	protected static boolean start = false;
	protected static Map<String, Integer> users = new HashMap<String, Integer>();
	
	@Override
	public void run() {
		
		try {
			
			Map<String, String> listMessages = new HashMap<String, String>();
			if(youtube != null)
				listMessages.putAll(youtube.getMessages());
			if(twitch != null)
				listMessages.putAll(twitch.getMessages());
			
			for(String nick: listMessages.keySet()) {
				
				String mess = listMessages.get(nick);
				
				if(users.containsKey(nick)) {
					
					if(mess.split(" ").length == 2 && mess.split(" ")[0].equalsIgnoreCase("!event")) {
						
						String event = mess.split(" ")[1].toLowerCase();
						switch (event) {
							case "-heart":
								if(takeMoney(nick, 160))
									minusHeart(nick);
							break;
							case "+heart":
								if(takeMoney(nick, 185))
									plusHeart(nick);
							break;
							case "+hardcore":
								if(takeMoney(nick, 140))
									hardcoreEnable(nick);
							break;
							case "-hardcore":
								if(takeMoney(nick, 150))
									hardcoreDisable(nick);
							break;
							case "hello":
								if(takeMoney(nick, 50))
									messOnTitle(nick);
							break;
							case "anvil":
								if(takeMoney(nick, 110))
									anvil(nick);
							break;
							case "poison":
								if(takeMoney(nick, 115))
									poisoning(nick);
							break;
							case "lava":
								if(takeMoney(nick, 190))
									floorIsLava(nick);
							break;
							case "drop":
								if(takeMoney(nick, 100))
									dropItem(nick);
							break;
							case "apple":
								if(takeMoney(nick, 70))
									giveGoldApple(nick);
							break;
							case "tp":
								if(takeMoney(nick, 150))
									randomTP(nick);
							break;
							case "wither":
								if(takeMoney(nick, 400))
									spawnWither(nick);
							break;
							case "dragon":
								if(takeMoney(nick, 500))
									spawnDragon(nick);
							break;
							case "item":
								if(takeMoney(nick, 80))
									randomItem(nick);
							break;

						}						
						
					} else if(mess.equalsIgnoreCase("!money")) {
						
						Main.sendMessageAll(nick + ", баланс " + users.get(nick));
						
					} else {
						
						users.replace(nick, users.get(nick) + (((int)(mess.length() / 5) + 1) * ((int)(mess.split(" ").length / 4) + 1)) + 1);
						Main.sendMessageAll(nick + ": " + mess);
						
					}
					
				} else {
					
					users.put(nick, 0);
					Main.sendMessageAll("Новый игрок! " + nick + ", добро пожаловать!");
					
				}
				
			}
			
		} catch (Exception e) {
			
			Main.sendMessageAll("Указанный стрим скорее всего закончился, выключаю ивенты...");
			
			start = false;
			timerEvents.cancel();
			
		}		
		
	}
	
	
	private boolean takeMoney(String nick, int amount) {
		
		if(users.get(nick) < amount)
			return false;
		
		users.replace(nick, users.get(nick) - amount);
		
		return true;
		
	}
	
	@SuppressWarnings("deprecation")
	private void minusHeart(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.setMaxHealth(p.getMaxHealth() - 2);
		
		Main.sendMessageAll("Тебе разбили сердце :) (" + nick + ")");
		
	}
	
	private void anvil(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			Location loc = p.getLocation();
			
			for(int i = 0; i < 4; i++)
				for(int i2 = 0; i2 < 4; i2++)
					new Location(loc.getWorld(), loc.getX()+i-2, loc.getY()+4, loc.getZ()+i2-2).getBlock().setType(Material.ANVIL);;
			
			Main.sendMessageAll("Берегись! (" + nick + ")");
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void dropItem(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			Location loc = p.getLocation();
			loc.setY(loc.getY()+1);
			p.getWorld().dropItem(loc, p.getItemInHand()).setVelocity(new Vector(1, 0, 0));
			p.getItemInHand().setType(Material.AIR);
			
		}
		
		Main.sendMessageAll("Ой! (" + nick + ")");
		
	}
	
	private void giveGoldApple(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
		
		Main.sendMessageAll("Держи Бро! (" + nick + ")");
		
	}
	
	private void randomTP(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			Location loc = p.getLocation();
			List<Location> blocksLoc = new ArrayList<Location>();
			for(int x = -100; x < 100; x++)
				for(int y = -100; y < 100; y++)
					for(int z = -100; z < 100; z++) {
						
						Location block = new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y-1, loc.getZ()+z);
						
						if(block.getBlock().getType() == Material.AIR || block.getBlock().getType() == Material.WATER || block.getBlock().getType() == Material.LAVA)
							continue;
						
						block = new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y, loc.getZ()+z);
						Location block2 = new Location(loc.getWorld(), loc.getX()+x, loc.getY()+y+1, loc.getZ()+z);
						if(block.getBlock().getType() != Material.AIR || block2.getBlock().getType() != Material.AIR)
							continue;
						
						blocksLoc.add(block);
						
					}
			
			if(blocksLoc.size() == 0)
				continue;
			
			p.teleport(blocksLoc.get((int)Math.floor(Math.random()*blocksLoc.size())));
		
		}
		
		Main.sendMessageAll("А где ты? (" + nick + ")");
		
	}
	
	private void poisoning(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
		
			p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 10*20, 0));
			p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 10*20, 0));
			
		}
		
		Main.sendMessageAll("Походу отравили... (" + nick + ")");
		
	}
	
	@SuppressWarnings("deprecation")
	private void plusHeart(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.setMaxHealth(p.getMaxHealth() + 2);
		
		Main.sendMessageAll("Сердечко :B (" + nick + ")");
		
	}
	
	private void hardcoreEnable(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, false);
		
		Main.sendMessageAll("Хардкор! (" + nick + ")");
		
	}
	
	private void hardcoreDisable(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.getWorld().setGameRule(GameRule.NATURAL_REGENERATION, true);
		
		Main.sendMessageAll("Восстановление! (" + nick + ")");
		
	}
	
	private void floorIsLava(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers()) {
			
			Location loc = p.getLocation();
			
			for(int i = 0; i < 7; i++)
				for(int i2 = 0; i2 < 7; i2++)
					if(i != 3 && i2 != 3)
						new Location(loc.getWorld(), loc.getX()+i-3, loc.getY()-1, loc.getZ()+i2-3).getBlock().setType(Material.LAVA);
					else
						continue;
						
			Main.sendMessageAll("Пол - лава! (" + nick + ")");
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void messOnTitle(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.sendTitle("Привет от " + nick, "");
		
	}
	
	private void spawnDragon(String nick) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				for(Player p: Bukkit.getOnlinePlayers())
					p.getWorld().spawnEntity(p.getLocation(), EntityType.ENDER_DRAGON);
				
			}
		};
		
		Bukkit.getScheduler().runTaskLater(Main.plugin, runnable, 15*20);
		
		Main.sendMessageAll("Ой, дракончик вылупляется! Осталось 15 сек! (" + nick + ")");
		
	}
	
	private void spawnWither(String nick) {
		
		Runnable runnable = new Runnable() {
			
			@Override
			public void run() {
				
				for(Player p: Bukkit.getOnlinePlayers())
					p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER);
				
			}
		};
		
		Bukkit.getScheduler().runTaskLater(Main.plugin, runnable, 15*20);
		
		Main.sendMessageAll("Как то сухо во рту... Осталось 15 сек! (" + nick + ")");
		
	}
	
	private void randomItem(String nick) {
		
		for(Player p: Bukkit.getOnlinePlayers())
			p.getWorld().dropItem(p.getLocation(), new ItemStack(Material.values()[(int)Math.floor(Math.random()*Material.values().length)], 1));
		
		Main.sendMessageAll("Рандом предмет! (" + nick + ")");
		
	}
	
}

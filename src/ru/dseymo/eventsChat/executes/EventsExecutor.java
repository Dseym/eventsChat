package ru.dseymo.eventsChat.executes;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import ru.dseymo.eventsChat.listeners.MessageTwitchListener;
import ru.dseymo.eventsChat.listeners.MessageYouTubeListener;
import ru.dseymo.eventsChat.utils.Chat;
import ru.dseymo.twitchStream.Result;
import ru.dseymo.twitchStream.Twitch;
import ru.dseymo.youtubeStream.YouTube;

public class EventsExecutor implements CommandExecutor {
	
	private FileConfiguration config;
	
	public EventsExecutor(File config) {
		
		if(!config.exists())
			try {config.createNewFile();} catch (IOException e) {}
		
		this.config = YamlConfiguration.loadConfiguration(config);
		
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0)
			help(sender);
		else if(args[0].equalsIgnoreCase("stop"))
			stop(sender);
		else if(args[0].equalsIgnoreCase("youtube"))
			youtube(sender, args);
		else if(args[0].equalsIgnoreCase("twitch"))
			twitch(sender, args);
		else
			help(sender);
		
		return true;
		
	}
	
	
	private void twitch(CommandSender sender, String[] args) {
		
		if(!config.contains("twitch.oauth") || !config.contains("twitch.nick")) {
			
			Chat.fail(sender, "Настройки для Twitch не указаны в конфиге");
			return;
			
		}
		
		if(args.length == 1)
			help(sender);
		else if(args[1].equalsIgnoreCase("set"))
			setT(sender, args);
		else
			help(sender);
		
	}
	
	private Twitch twitch;
	private void setT(CommandSender sender, String[] args) {
		
		if(args.length == 2) help(sender);
		else {
		
			Twitch tw = new Twitch(args[2], config.getString("twitch.oauth"), config.getString("twitch.nick"), '!', new MessageTwitchListener(args[2]));
			
			Result answ = tw.connect();
			if(answ == Result.SUCCESS) {
				if(twitch != null) twitch.disconnect();
				twitch = tw;
				Chat.success(sender, answ.getMessage());
			} else Chat.fail(sender, answ.getMessage());
		
		}
		
	}
	

	private void youtube(CommandSender sender, String[] args) {
		
		if(!config.contains("youtube.api")) {
			
			Chat.fail(sender, "Настройки для YouTube не указаны в конфиге");
			return;
			
		}
		
		if(args.length == 1)
			help(sender);
		else if(args[1].equalsIgnoreCase("set"))
			setY(sender, args);
		else
			help(sender);
		
	}
	
	private YouTube youtube;
	private void setY(CommandSender sender, String[] args) {
		
		if(args.length == 2) help(sender);
		else {
		
			YouTube yt = new YouTube(args[2], config.getString("youtube.api"), '!', new MessageYouTubeListener(args[2]));
			
			ru.dseymo.youtubeStream.Result answ = yt.connect();
			if(answ == ru.dseymo.youtubeStream.Result.SUCCESS) {
				if(youtube != null) youtube.disconnect();
				youtube = yt;
				Chat.success(sender, answ.getMessage());
			} else Chat.fail(sender, answ.getMessage());
		
		}
		
	}
	

	private void stop(CommandSender sender) {
		
		if(youtube != null) youtube.disconnect();
		if(twitch != null) twitch.disconnect();
		
		youtube = null;
		twitch = null;
		
		Chat.success(sender, "Успешное отсоединение");
		
	}
	
	public void finalize() {
		
		stop(Bukkit.getConsoleSender());
		
	}

	private void help(CommandSender sender) {
		
		Chat.noPrefix(sender, "&l=========== EventsChat - Commands ===========",
							  "&b/events twitch set <chanName> &7- &eподключиться к &5Twitch &eканалу",
							  "&b/events youtube set <vidID> &7- &eподключиться к &fYou&cTube &eстриму",
							  "&b/events stop &7- &eотключиться от всех каналов/стримов",
						  	  "&l=========== EventsChat - Commands ===========");
		
	}

}

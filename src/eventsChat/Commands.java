package eventsChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import StreamInfoYoutube.YouTube;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 0)
			return false;
		
		if(args[0].equalsIgnoreCase("stream"))
			if(args.length != 2) {
				
				sender.sendMessage(Main.tagPlugin + "Введите ID стрима");
				return true;
				
			} else {
				
				EventManager.youtube = new YouTube(args[1]);
				try {
					
					EventManager.youtube.getChat();
					sender.sendMessage(Main.tagPlugin + "Подключен/Изменен стрим: " + EventManager.youtube.getNameStream());
					
				} catch (Exception e) {
					
					sender.sendMessage(Main.tagPlugin + "Возможно, это не стрим");
					
				}
				
			}
		else if(args[0].equalsIgnoreCase("start")) {
			
			if(EventManager.start) {
				
				sender.sendMessage(Main.tagPlugin + "Игра уже идет");
				return true;
				
			}
			
			if(EventManager.youtube == null) {
				
				sender.sendMessage(Main.tagPlugin + "Сначала укажите ID стрима");
				return true;
				
			}
			
			EventManager.timerEvents = Bukkit.getScheduler().runTaskTimer(Main.plugin, new EventManager(), 10*20, 5*20);
			sender.sendMessage(Main.tagPlugin + "Ивенты включатся через 10 сек");
			
		}
		
		return true;
		
	}

}

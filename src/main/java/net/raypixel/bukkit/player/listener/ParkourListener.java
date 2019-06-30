package net.raypixel.bukkit.player.listener;

import net.raypixel.bukkit.parkour.events.ParkourFinishEvent;
import net.raypixel.bukkit.player.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;

public class ParkourListener implements Listener {
	@EventHandler
	public void onParkourFinish(ParkourFinishEvent event) {
		Player player = event.getPlayer();
		ConfigManager.getData(player).set("parkour-records." + event.getArena().getName(), event.getRecord());
		try {
			ConfigManager.getData(player).save(ConfigManager.getDataFile(player));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

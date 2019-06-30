package net.raypixel.bukkit.player;

import net.raypixel.bukkit.player.commands.Language;
import net.raypixel.bukkit.player.listener.EventListener;
import net.raypixel.bukkit.player.listener.ParkourListener;
import net.raypixel.bukkit.player.utils.*;
import org.apache.commons.lang.SystemUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

	public static Logger logger;
	private static Main instance;
	private ProfileManager p;

	public static Main getInstance() {
		return instance;
	}

	public void onEnable() {

		instance = this;
		logger = getLogger();
		p = new ProfileManager();
		getConfig().options().copyDefaults(true);
		getConfig().options().copyHeader(true);
		saveDefaultConfig();
		for (String language : getConfig().getStringList("languages")) {
			File localeFile = new File(getDataFolder(), language + ".yml");
			if (localeFile.exists()) {
				if (getConfig().getBoolean("update-language-files")) {
					saveResource(language + ".yml", true);
				}
			} else {
				saveResource(language + ".yml", false);
			}
		}
		File playerDataFolder;
		if (getConfig().getBoolean("auto-detect")) {
			playerDataFolder = new File(getDataFolder().getAbsolutePath().split("Raypixel" + (SystemUtils.IS_OS_WINDOWS ? "\\\\" : File.separator))[0] + "Raypixel" + File.separator + "PlayerData" + File.separator);
			if (!playerDataFolder.exists()) {
				playerDataFolder.mkdir();
			}
		} else {
			playerDataFolder = new File(getConfig().getString("data-path"));
		}
		FriendsManager f = null;
		if (getConfig().getBoolean("mysql.enable")) {
			new MySQLManager(this);
		}
		if (getConfig().getBoolean("friends.enable")) {
			f = new FriendsManager(this);
		}
		getServer().getPluginManager().registerEvents(new EventListener(p, f), this);
		getCommand("language").setExecutor(new Language());
		if (Bukkit.getPluginManager().isPluginEnabled("RaypixelParkour"))
			getServer().getPluginManager().registerEvents(new ParkourListener(), this);
		new ConfigManager(playerDataFolder);
		new SkullManager();
		new RankManager();

		for (Player player : Bukkit.getOnlinePlayers()) {
			ConfigManager.updateDataFile(player, true);
		}

		getCommand("profile").setExecutor(p);

		logger.info("Enabled " + getDescription().getName() + " v" + getDescription().getVersion());
	}

	public void onDisable() {
		instance = null;
		logger.info("Disabled " + getDescription().getName() + " v" + getDescription().getVersion());
	}

	public String getCurrentFormattedDate(String format) {
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat(format);
		return ft.format(date);
	}

}

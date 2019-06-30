package net.raypixel.bukkit.player.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.raypixel.bukkit.player.Main;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class ConfigManager {

	public static File playerDataFolder;
	public static Map<UUID, YamlConfiguration> playerDataMap = new HashMap<>();
	static ConfigurationSection defaultData;
	private static Map<UUID, File> playerFileMap = new HashMap<>();

	public ConfigManager(File file) {
		playerDataFolder = file;
		defaultData = Main.getInstance().getConfig().getConfigurationSection("default");
	}

	public static void updateDataFile(Player player, boolean existenceCheck) {
		File playerData = new File(playerDataFolder.getAbsolutePath(), player.getUniqueId() + ".yml");
		if (!existenceCheck) {
			try {
				playerData.createNewFile();
				YamlConfiguration data = YamlConfiguration.loadConfiguration(playerData);
				for (String dataItem : defaultData.getKeys(true)) {
					if (dataItem.equals("name")) {
						data.set("name", player.getName());
					} else {
						data.set(dataItem, defaultData.get(dataItem));
					}
				}
				data.save(playerData);
				playerDataMap.put(player.getUniqueId(), data);
				playerFileMap.put(player.getUniqueId(), playerData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (playerData.exists()) {
			setupData(player);
			YamlConfiguration data = playerDataMap.containsKey(player.getUniqueId())
					? playerDataMap.get(player.getUniqueId())
					: YamlConfiguration.loadConfiguration(playerData);
			data.set("name", player.getName());
			if (!playerDataMap.containsKey(player.getUniqueId()))
				playerDataMap.put(player.getUniqueId(), data);
			if (!playerFileMap.containsKey(player.getUniqueId()))
				playerFileMap.put(player.getUniqueId(), playerData);
			try {
				data.save(playerData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				playerData.createNewFile();
				YamlConfiguration data = YamlConfiguration.loadConfiguration(playerData);
				for (String dataItem : defaultData.getKeys(true)) {
					if (dataItem.equals("name")) {
						data.set("name", player.getName());
					} else {
						data.set(dataItem, defaultData.get(dataItem));
					}
				}
				data.save(playerData);
				playerDataMap.put(player.getUniqueId(), data);
				playerFileMap.put(player.getUniqueId(), playerData);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void setupData(Player player) {
		File playerDataFile = new File(getDataFolder().getAbsolutePath(), player.getUniqueId() + ".yml");
		YamlConfiguration data = playerDataMap.containsKey(player.getUniqueId())
				? playerDataMap.get(player.getUniqueId())
				: YamlConfiguration.loadConfiguration(playerDataFile);
		for (String dataItem : defaultData.getKeys(true)) {
			if (dataItem.equals("name") && data.get("name") == null) {
				data.set("name", player.getName());
			} else {
				if (data.get(dataItem) == null) {
					data.set(dataItem, defaultData.get(dataItem));
				}
			}
		}
		if (!playerDataMap.containsKey(player.getUniqueId()))
			playerDataMap.put(player.getUniqueId(), data);
		if (!playerFileMap.containsKey(player.getUniqueId()))
			playerFileMap.put(player.getUniqueId(), playerDataFile);

		try {
			data.save(playerDataFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static YamlConfiguration getData(OfflinePlayer player) {
		if (!playerDataMap.containsKey(player.getUniqueId())) {
			YamlConfiguration data = YamlConfiguration
					.loadConfiguration(new File(getDataFolder().getAbsolutePath(), player.getUniqueId() + ".yml"));
			playerDataMap.put(player.getUniqueId(), data);
		}
		return playerDataMap.get(player.getUniqueId());
	}

	public static File getDataFile(OfflinePlayer player) {
		if (!playerFileMap.containsKey(player.getUniqueId())) {
			File playerDataFile = new File(getDataFolder().getAbsolutePath(), player.getUniqueId() + ".yml");
			playerFileMap.put(player.getUniqueId(), playerDataFile);
		}
		return playerFileMap.get(player.getUniqueId());
	}

	public static File getDataFolder() {
		return playerDataFolder;
	}

	public static String getLanguageName(OfflinePlayer player) {
		return getData(player).getString("language");
	}

}

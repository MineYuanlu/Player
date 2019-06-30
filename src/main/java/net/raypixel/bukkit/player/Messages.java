package net.raypixel.bukkit.player;

import net.raypixel.bukkit.player.utils.CU;
import net.raypixel.bukkit.player.utils.ConfigManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Messages {

    public static final String NOT_A_PLAYER = getMessage("zh-CN", "NOT_A_PLAYER");
    public static final String MYSQL_CONNECTED = CU.t("[" + Main.getInstance().getDescription().getName() + "] " + getMessage("zh-CN", "MYSQL_CONNECTED"));

    public static String getMessage(YamlConfiguration language, String message) {
        return CU.t(language.getString(message));
    }

    public static String getMessage(String language, String message) {
        return CU.t(YamlConfiguration.loadConfiguration(new File(Main.getInstance().getDataFolder(), language + ".yml")).getString(message));
    }

    public static String getMessage(OfflinePlayer player, String message) {
        return CU.t(getLanguage(player).getString(message));
    }

    public static YamlConfiguration getLanguage(OfflinePlayer player) {
        File locale = new File(Main.getInstance().getDataFolder(), ConfigManager.getLanguageName(player) + ".yml");
        return YamlConfiguration.loadConfiguration(locale);
    }

}

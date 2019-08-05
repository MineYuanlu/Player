package net.raypixel.bukkit.player.utils;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RankManager {

	public static Map<UUID, String> storage = new HashMap<>();

	static String getDisplayRank(String rank) {
		if (rank.equalsIgnoreCase("owner")) {
			return ChatColor.RED + "OWNER";
		} else if (rank.equalsIgnoreCase("admin")) {
			return ChatColor.RED + "ADMIN";
		} else if (rank.equalsIgnoreCase("mod")) {
			return ChatColor.DARK_GREEN + "MOD";
		} else if (rank.equalsIgnoreCase("up")) {
			return ChatColor.WHITE + "UP";
		} else if (rank.equalsIgnoreCase("mvpplusplus") || rank.equalsIgnoreCase("mvp++")) {
			return ChatColor.GOLD + "MVP" + ChatColor.RED + "++";
		} else if (rank.equalsIgnoreCase("mvpplus") || rank.equalsIgnoreCase("mvp+")) {
			return ChatColor.AQUA + "MVP+";
		} else if (rank.equalsIgnoreCase("mvp")) {
			return ChatColor.AQUA + "MVP";
		} else if (rank.equalsIgnoreCase("vipplus") || rank.equalsIgnoreCase("vip+")) {
			return ChatColor.GREEN + "VIP" + ChatColor.GOLD + "+";
		} else if (rank.equalsIgnoreCase("vip")) {
			return ChatColor.GREEN + "VIP";
		} else if (rank.equalsIgnoreCase("hacker")) {
			return ChatColor.DARK_PURPLE + "HACKER";
		} else {
			return ChatColor.GRAY + rank.toUpperCase();
		}
	}

	static String getDisplayRank(OfflinePlayer player) {
		if (storage.containsKey(player.getUniqueId()))
			return getDisplayRank(storage.get(player.getUniqueId()));
		else
			return CU.t("&7MEMBER");
	}

	public static String getRankedName(OfflinePlayer player, String rank) {
		String rankedName;
		if (rank.equalsIgnoreCase("owner")) {
			rankedName = ChatColor.RED + "[OWNER] " + ChatColor.RED + player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("admin")) {
			rankedName = ChatColor.RED + "[ADMIN] " + ChatColor.RED + player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mod")) {
			rankedName = ChatColor.DARK_GREEN + "[MOD] " + ChatColor.DARK_GREEN + player.getName() + ChatColor.WHITE
					+ "";
		} else if (rank.equalsIgnoreCase("up")) {
			rankedName = ChatColor.RED + "[" + ChatColor.WHITE + "UP" + ChatColor.RED + "] " + ChatColor.RED
					+ player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvpplusplus") || rank.equalsIgnoreCase("mvp++")) {
			rankedName = ChatColor.GOLD + "[MVP" + ChatColor.RED + "++" + ChatColor.GOLD + "] " + ChatColor.GOLD
					+ player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvpplus") || rank.equalsIgnoreCase("mvp+")) {
			rankedName = ChatColor.AQUA + "[MVP" + ChatColor.RED + "+" + ChatColor.AQUA + "] " + ChatColor.AQUA
					+ player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvp")) {
			rankedName = ChatColor.AQUA + "[MVP] " + ChatColor.AQUA + player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("vipplus") || rank.equalsIgnoreCase("vip+")) {
			rankedName = ChatColor.GREEN + "[VIP" + ChatColor.GOLD + "+" + ChatColor.GREEN + "] " + ChatColor.GREEN
					+ player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("vip")) {
			rankedName = ChatColor.GREEN + "[VIP] " + ChatColor.GREEN + player.getName() + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("hacker")) {
			rankedName = ChatColor.DARK_PURPLE + "[HACKER] " + ChatColor.DARK_PURPLE + player.getName() + ""
					+ ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("member")) {
			rankedName = ChatColor.GRAY + player.getName();
		} else {
			rankedName = ChatColor.GRAY + "[" + rank.toUpperCase() + "] " + player.getName();
		}
		return rankedName;
	}

	public static String getRankedName(UUID uuid, String rank) {
		String rankedName;
		String name = ConfigManager.getData(uuid).getString("name");
		if (rank.equalsIgnoreCase("owner")) {
			rankedName = ChatColor.RED + "[OWNER] " + ChatColor.RED + name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("admin")) {
			rankedName = ChatColor.RED + "[ADMIN] " + ChatColor.RED + name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mod")) {
			rankedName = ChatColor.DARK_GREEN + "[MOD] " + ChatColor.DARK_GREEN + name + ChatColor.WHITE
					+ "";
		} else if (rank.equalsIgnoreCase("up")) {
			rankedName = ChatColor.RED + "[" + ChatColor.WHITE + "UP" + ChatColor.RED + "] " + ChatColor.RED
					+ name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvpplusplus") || rank.equalsIgnoreCase("mvp++")) {
			rankedName = ChatColor.GOLD + "[MVP" + ChatColor.RED + "++" + ChatColor.GOLD + "] " + ChatColor.GOLD
					+ name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvpplus") || rank.equalsIgnoreCase("mvp+")) {
			rankedName = ChatColor.AQUA + "[MVP" + ChatColor.RED + "+" + ChatColor.AQUA + "] " + ChatColor.AQUA
					+ name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("mvp")) {
			rankedName = ChatColor.AQUA + "[MVP] " + ChatColor.AQUA + name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("vipplus") || rank.equalsIgnoreCase("vip+")) {
			rankedName = ChatColor.GREEN + "[VIP" + ChatColor.GOLD + "+" + ChatColor.GREEN + "] " + ChatColor.GREEN
					+ name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("vip")) {
			rankedName = ChatColor.GREEN + "[VIP] " + ChatColor.GREEN + name + ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("hacker")) {
			rankedName = ChatColor.DARK_PURPLE + "[HACKER] " + ChatColor.DARK_PURPLE + name + ""
					+ ChatColor.WHITE + "";
		} else if (rank.equalsIgnoreCase("member")) {
			rankedName = ChatColor.GRAY + name;
		} else {
			rankedName = ChatColor.GRAY + "[" + rank.toUpperCase() + "] " + name;
		}
		return rankedName;
	}

}

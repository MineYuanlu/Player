package net.raypixel.bukkit.player.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.raypixel.bukkit.player.Messages;
import org.bukkit.ChatColor;
import net.raypixel.bukkit.player.Main;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SkullManager {

	public static ItemStack getPersonalSkull(OfflinePlayer player, String language) {
		ItemStack person = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		SkullMeta meta = (SkullMeta) person.getItemMeta();
		meta.setDisplayName(RankManager.getRankedName(player, ConfigManager.getData(player).getString("rank")));
		meta.setOwner(player.getName());
		List<String> personalInfo = new ArrayList<>();
		personalInfo.add(ChatColor.GRAY + Messages.getMessage(language, "RANK") + ": " + RankManager.getDisplayRank(player));
		personalInfo.add(ChatColor.GRAY + Messages.getMessage(language, "LEVEL") + ": " + ConfigManager.getLevel(player));
		personalInfo.add(ChatColor.GRAY + Messages.getMessage(language, "POINT") + ": " + ChatColor.AQUA + ConfigManager.getData(player).getInt("point"));
		if (!ConfigManager.getData(player).getString("birth-year").equalsIgnoreCase("UNSET")) {
			personalInfo.add(ChatColor.GRAY + Messages.getMessage(language, "AGE") + ": " + ChatColor.AQUA + (Integer.valueOf(Main.getInstance().getCurrentFormattedDate("yyyy"))
					- ConfigManager.getData(player).getInt("birth-year")));
		}
		if (!ConfigManager.getData(player).getString("gender").equalsIgnoreCase("UNSET")) {
			String gender = ConfigManager.getData(player).getString("gender").toUpperCase();
			personalInfo.add(ChatColor.GRAY + Messages.getMessage(language, "GENDER") + ": " + (gender.equalsIgnoreCase("male") ? ChatColor.AQUA : ChatColor.LIGHT_PURPLE) + Messages.getMessage(language, gender));
		}
		meta.setLore(personalInfo);
		person.setItemMeta(meta);
		return person;
	}

	public static ItemStack getCustomSkull(String url) {
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		PropertyMap propertyMap = profile.getProperties();
		if (propertyMap == null) {
			throw new IllegalStateException("Profile doesn't contain a property map");
		}
		final Base64 base64 = new Base64();
		byte[] encodedData = base64.encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", url).getBytes());
		propertyMap.put("textures", new Property("textures", new String(encodedData)));
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		ItemMeta headMeta = head.getItemMeta();
		Class<?> headMetaClass = headMeta.getClass();
		Reflections.getField(headMetaClass, "profile", GameProfile.class).set(headMeta, profile);
		head.setItemMeta(headMeta);
		return head;
	}

}

package net.raypixel.bukkit.player.utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class CU {

    public static String t(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static List<String> t(List<String> stringList) {
        List<String> newStringList = new ArrayList<>();
        for (int i = 0; i < stringList.size(); i++) {
            newStringList.add(ChatColor.translateAlternateColorCodes('&', stringList.get(i)));
        }
        return newStringList;
    }

}

package com.extendedclip.pickarank;

import org.bukkit.plugin.java.JavaPlugin;

import com.extendedclip.pickarank.menu.Item;

public class PickARank extends JavaPlugin {

	protected Settings settings;
	
	@Override
	public void onEnable() {
		settings = new Settings(this);
		settings.loadConfig();	
		Item.setMenuItems(settings.getMenuItems());
		new PlayerListener(this);
		new Commands(this);
	}
	
	@Override
	public void onDisable() {
		Item.unload();
	}
	
	public Settings getSettings() {
		return settings;
	}
	
}

package com.extendedclip.pickarank;

import org.bukkit.plugin.java.JavaPlugin;

import com.extendedclip.pickarank.menu.Item;

public class PickARank extends JavaPlugin {

	private static PickARank instance;
	protected Settings settings;
	
	@Override
	public void onEnable() {
		instance = this;
		settings = new Settings(this);
		settings.loadConfig();	
		Item.setMenuItems(settings.getMenuItems());
		new PlayerListener(this);
		new Commands(this);
	}
	
	@Override
	public void onDisable() {
		Item.unload();
		instance = null;
	}
	
	public static PickARank getInstance() {
		return instance;
	}
	
	public Settings getSettings() {
		return settings;
	}
	
}

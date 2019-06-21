/*
 *
 * PickARank
 * Copyright (C) 2018 Ryan McCarthy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *
 */
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

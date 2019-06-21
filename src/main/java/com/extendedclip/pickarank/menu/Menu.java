/*
 *
 * PickARank
 * Copyright (C) 2019 Ryan McCarthy
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
package com.extendedclip.pickarank.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.extendedclip.pickarank.PickARank;
import me.clip.placeholderapi.PlaceholderAPI;

public class Menu implements InventoryHolder {
	private Player p;
	private String name;
	private int slots;
		
	public Menu(PickARank plugin, Player p) {
		this.p = p;
		this.name = PlaceholderAPI.setPlaceholders(p, plugin.getSettings().getMenuName());
		this.slots = plugin.getSettings().getMenuSize();
	}

	@Override
	public Inventory getInventory() {
		if (name.length() > 32) {
			name = name.substring(0, 31);
		}
		
		Inventory inv = Bukkit.createInventory(this, slots, name);
		
		for (Item i : Item.getMenuItems()) {
			org.bukkit.inventory.ItemStack iStack = i.getItemStack(p);
			inv.setItem(i.getSlot(), iStack);
		}
		return inv;
	}
}

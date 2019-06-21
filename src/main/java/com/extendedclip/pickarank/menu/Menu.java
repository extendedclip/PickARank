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
		
	public Menu(Player p) {
		this.p = p;
		this.name = PlaceholderAPI.setPlaceholders(p, PickARank.getInstance().getSettings().getMenuName());
		this.slots = PickARank.getInstance().getSettings().getMenuSize();
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

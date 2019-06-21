package com.extendedclip.pickarank;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

import com.extendedclip.pickarank.menu.Item;
import com.extendedclip.pickarank.menu.Menu;

public class PlayerListener implements Listener {

	private PickARank plugin;

	public PlayerListener(PickARank i) {
		plugin = i;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("pickarank.open")) {
			Menu m = new Menu(p);
			p.openInventory(m.getInventory());
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onClose(InventoryCloseEvent e) {
		
		final Inventory inventory = e.getInventory();
		
		if (!(e.getPlayer() instanceof Player)) {
			return;
		}
		
		Player p = (Player) e.getPlayer();
		
		if (!(inventory.getHolder() instanceof Menu)) {
			return;
		}
			
		if (!p.hasPermission("pickarank.open")) {
			return;
		}
			
		final String name = p.getName();
			
			Bukkit.getScheduler().runTaskLater(PickARank.getInstance(), new Runnable() {

				@Override
				public void run() {
					
					Player who = Bukkit.getPlayer(name);
					
					if (who == null) {
						return;
					}
					
					who.openInventory(inventory);
				}
				
			}, 20L);
			return;
		}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onClick(InventoryClickEvent e) {
		
		if (!(e.getWhoClicked() instanceof Player)) {
			return;
		}
		if (!(e.getClickedInventory().getHolder() instanceof Menu)) {
			return;
		}
		
		e.setCancelled(true);
		Player p = (Player) e.getWhoClicked();
		int slot = e.getRawSlot();
		Item i = Item.getMenuItem(slot);
		
		if (i == null) {
			return;
		}
		
		if (i.getClickHandler() == null) {
			return;
		}

		i.getClickHandler().onClick(p);

		return;		
	}

}

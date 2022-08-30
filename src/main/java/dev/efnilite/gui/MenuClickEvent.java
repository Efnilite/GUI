package dev.efnilite.gui;

import dev.efnilite.gui.item.MenuItem;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * A container class for when a player clicks on a specific item.
 */
public record MenuClickEvent(int slot, Menu menu, MenuItem item, InventoryClickEvent event) {

    public Player getPlayer() {
        return (Player) event.getWhoClicked();
    }
}
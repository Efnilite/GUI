package dev.efnilite.gui.item;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;

/**
 * An item which may be moved by the player
 *
 * @author Efnilite
 */
public class MovableItem extends Item {

    public MovableItem(Material material, Component name) {
        super(material, name);
    }

    public MovableItem(Material material, int amount, Component name) {
        super(material, amount, name);
    }

    @Override
    public boolean isMovable() {
        return true;
    }
}
package dev.efnilite.gui.util.event;

import dev.efnilite.gui.Menu;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

/**
 * Wrapper for Listener, adding useful methods.
 *
 * @author Efnilite
 */
public interface EventWatcher extends Listener {

    /**
     * Unregisters every listener in this class
     */
    default void unregisterAll() {
        HandlerList.unregisterAll(this);
    }

    /**
     * Registers this listener
     */
    default void register() {
        Bukkit.getPluginManager().registerEvents(this, Menu.PLUGIN);
    }
}
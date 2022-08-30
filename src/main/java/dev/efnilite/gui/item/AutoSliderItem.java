package dev.efnilite.gui.item;

import com.google.common.annotations.Beta;
import dev.efnilite.gui.Menu;
import dev.efnilite.gui.MenuClickEvent;
import dev.efnilite.gui.util.Task;
import dev.efnilite.gui.util.collections.ViMap;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

/**
 * Class for an item which automatically slides.
 *
 * @author Efnilite
 */
@Beta
public class AutoSliderItem extends MenuItem {

    /**
     * The initial value which will be displayed
     */
    private int current;
    private int cooldown;
    private final int slot;
    private final Menu menu;
    private final ViMap<Integer, Item> items = new ViMap<>();
    private final ViMap<Integer, Consumer<MenuClickEvent>> clickFunctions = new ViMap<>();

    /**
     * The constructor.
     *
     * @param   menu
     *          The menu that this item will be displayed in.
     */
    public AutoSliderItem(int slot, Menu menu) {
        this.slot = slot;
        this.menu = menu;
    }

    /**
     * Sets the initial viewing index.
     * If you set up 2 items, with index 0 and 1, you can specify which will be viewed first.
     *
     * @param   initial
     *          The initial viewing index
     *
     * @return the instance of this class
     */
    public AutoSliderItem initial(int initial) {
        this.current = initial;
        return this;
    }

    /**
     * Sets the cooldown between sliding to the next item.
     *
     * @param   ticks
     *          The amount of ticks until the transition occurs.
     *
     * @return the instance of this class
     */
    public AutoSliderItem cooldown(int ticks) {
        this.cooldown = ticks;
        return this;
    }

    /**
     * Adds an item to the possible options. This uses a Function.
     * The Function will determine whether the item will update in the inventory.
     * If this returns false, it will not update the item in the menu, but it will execute the code.
     *
     * @param   value
     *          The value assigned to this item
     *
     * @param   item
     *          The item
     *
     * @param   onClick
     *          What happens on switch to this item. Returns true if it should update, false if not.
     *
     * @return the instance of this class
     */
    public AutoSliderItem add(int value, Item item, Consumer<MenuClickEvent> onClick) {
        items.put(value, item);
        clickFunctions.put(value, onClick);
        return this;
    }

    @Override
    public void handleClick(Menu menu, InventoryClickEvent event, ClickType clickType) {
        Consumer<MenuClickEvent> function = clickFunctions.get(current);
        if (function == null) {
            return;
        }
        function.accept(new MenuClickEvent(event.getSlot(), menu, this, event));
    }

    @Override
    public ItemStack build() {
        if (items.keySet().size() == 0) {
            throw new IllegalArgumentException("Items size is 0!");
        }

        Item init = items.get(current);
        if (init == null) {
            init = items.get(items.randomKey());
        }

        if (items.size() > 1) { // loop through if there are more than 1 players
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    InventoryView view = menu.getPlayer().getOpenInventory();

                    if (view.title().equals(menu.getTitle())) {
                        view.getTopInventory().setItem(slot, getNextItem().build());
                    } else {
                        cancel(); // prevent going on forever
                    }
                }
            };

            Task task = Task.create(Menu.PLUGIN)
                    .delay(cooldown)
                    .repeat(cooldown)
                    .execute(runnable);
            task.run();
        }

        return init.build();
    }

    /**
     * Returns the next item. Increases the counter.
     *
     * @return the next item
     */
    public Item getNextItem() {
        current++;
        if (current >= items.size() - 1) {
            current = 0;
        }

        return items.get(current);
    }

    @Override
    public boolean isMovable() {
        return false;
    }
}
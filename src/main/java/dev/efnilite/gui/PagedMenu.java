package dev.efnilite.gui;

import dev.efnilite.gui.item.Item;
import dev.efnilite.gui.item.MenuItem;
import dev.efnilite.gui.util.Numbers;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A menu which, depending on the amount of items on display, supports a page view.
 * Add items with {@link #addToDisplay(List)}.
 *
 * @author Efnilite
 */
public class PagedMenu extends Menu {

    private int total;
    private int current;
    private int nextPageSlot;
    private MenuItem nextPageItem;
    private int prevPageSlot;
    private MenuItem prevPageItem;
    private final List<Integer> displaySlots = new ArrayList<>();
    private final List<MenuItem> totalToDisplay = new ArrayList<>();
    private final Map<Integer, List<MenuItem>> assigned = new HashMap<>();

    /**
     * Constructor for a paged menu.
     *
     * @param   rows
     *          The amount of rows this menu will have.
     *
     * @param   title
     *          The title.
     */
    public PagedMenu(int rows, Component title) {
        super(rows, title);
    }

    /**
     * Sets the rows in which items will be displayed.
     *
     * @param   rows
     *          The rows which will display the items provided in {@link #addToDisplay(List)}. Starts from 0.
     *
     * @return the instance of this menu
     */
    public PagedMenu displayRows(int... rows) {
        int begin = Integer.MAX_VALUE;
        int end = Integer.MIN_VALUE;

        for (int row : rows) {
            if (row < 0 || row > 5) {
                throw new IllegalArgumentException("Row must be above 0 and below 6!");
            }

            int min = row * 9;
            int max = min + 8;

            begin = Numbers.min(begin, min);
            end = Numbers.max(end, max);
        }
        displaySlots.addAll(Numbers.getFromTo(begin, end));
        return this;
    }

    @Override
    public void open(Player player) {
        assignPages();

        page(0);
        super.open(player);
    }

    /**
     * Opens a specific page to the player.
     *
     * @param   delta
     *          The relative page to the player's current page. -1 will go back one page, 1 will go to the next page.
     */
    public void page(int delta) {
        int newPage = current + delta;
        if (newPage < 0 || newPage > total) {
            return;
        }
        if (!assigned.containsKey(newPage) || assigned.get(newPage) == null) {
            return;
        }

        List<MenuItem> values = new ArrayList<>(assigned.get(newPage));
        Item missingFiller = new Item(filler, MiniMessage.miniMessage().deserialize("<red> "));

        items.remove(prevPageSlot);
        items.remove(nextPageSlot);

        if (newPage > 0) {
            items.put(prevPageSlot, prevPageItem);
        } else if (filler != null) {
            items.put(prevPageSlot, missingFiller);
        }
        if (newPage < total - 1) {
            items.put(nextPageSlot, nextPageItem);
        } else if (filler != null) {
            items.put(nextPageSlot, missingFiller);
        }

        for (int slot : displaySlots) {
            items.remove(slot);

            if (values.size() > 0) {
                items.put(slot, values.get(0));
                values.remove(0);
            } else if (filler != null) {
                items.put(slot, missingFiller);
            }
        }
        if (delta != 0) {
            update();
        }
        current = newPage;
    }

    private void assignPages() {
        List<MenuItem> total = new ArrayList<>(totalToDisplay);
        List<MenuItem> thisPage = new ArrayList<>();

        int page = 0;
        while (total.size() > 0) {
            for (int slot = 0; slot < displaySlots.size(); slot++) {
                if (total.size() <= 0) {
                    break;
                }
                thisPage.add(total.get(0));
                total.remove(0);
            }

            this.assigned.put(page, new ArrayList<>(thisPage));
            thisPage.clear();
            page++;
        }

        this.current = 0;
        this.total = assigned.keySet().size();
    }

    /**
     * Adds item to the display.
     *
     * @param   items
     *          The items
     *
     * @return the instance of this class
     */
    public PagedMenu addToDisplay(List<MenuItem> items) {
        totalToDisplay.addAll(items);
        return this;
    }

    /**
     * Sets all the display items in one go.
     *
     * @param   items
     *          The items to display
     */
    public void setToDisplay(List<MenuItem> items) {
        totalToDisplay.clear();
        totalToDisplay.addAll(items);
    }

    /**
     * Set the item which players can use to go to the next page.
     *
     * @param   slot
     *          The slot.
     *
     * @param   item
     *          The item.
     *
     * @return the instance of this menu
     */
    public PagedMenu nextPage(int slot, MenuItem item) {
        this.nextPageSlot = slot;
        this.nextPageItem = item;
        return this;
    }

    /**
     * Set the item which players can use to go to the previous page.
     *
     * @param   slot
     *          The slot.
     *
     * @param   item
     *          The item.
     *
     * @return the instance of this menu
     */
    public PagedMenu prevPage(int slot, MenuItem item) {
        this.prevPageSlot = slot;
        this.prevPageItem = item;
        return this;
    }

    /**
     * Gets all items that will be displayed.
     *
     * @return all items that will be displayed.
     */
    public List<MenuItem> getTotalToDisplay() {
        return totalToDisplay;
    }

    /**
     * Returns the current page
     * @return the current page
     */
    public int getCurrent() {
        return current;
    }
}
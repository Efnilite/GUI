package dev.efnilite.gui.animation;

import dev.efnilite.gui.Menu;
import dev.efnilite.gui.item.MenuItem;
import dev.efnilite.gui.util.Task;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * Super class for all Menu Opening Animations.
 *
 * @author Efnilite
 */
public abstract class MenuAnimation {

    private int currentStep = 0;
    private BukkitTask task;

    /**
     * The amount of ticks per step
     */
    protected int ticksPerStep = 1;

    /**
     * Which slots will be set on which ticks?
     */
    protected Map<Integer, List<Integer>> steps = new HashMap<>();

    /**
     * Set how many ticks it will take to execute one step.
     * By default 1.
     *
     * @param   ticksPerStep
     *          The ticks required to perform one step
     */
    protected void ticksPerStep(int ticksPerStep) {
        if (ticksPerStep < 1) {
            throw new IllegalArgumentException("Ticks cannot be under 1");
        }
        this.ticksPerStep = ticksPerStep;
    }

    /**
     * Add which slots will be set during which tick
     * If two of the same keys are registered they will be added together.
     *
     * @param   tick
     *          The tick. This must start from 0 and increment by one each time.
     *
     * @param   slots
     *          The slots which will be set during this time period
     */
    protected void add(int tick, Integer... slots) {
        this.add(tick, Arrays.asList(slots));
    }

    /**
     * Add which slots will be set during which tick.
     * If two of the same keys are registered they will be added together.
     *
     * @param   tick
     *          The tick. This must start from 0 and increment by one each time.
     *
     * @param   slots
     *          The slots which will be set during this time period
     */
    protected void add(int tick, List<Integer> slots) {
        if (steps.containsKey(tick)) {
            List<Integer> contained = steps.get(tick);
            contained.addAll(slots);
            steps.put(tick, contained);
        } else {
            steps.put(tick, slots);
        }
    }

    /**
     * Initializes this animation, settings all slots
     *
     * @param   rows
     *          The amount of rows the eventual inventory will have.
     */
    public abstract void init(int rows);

    /**
     * Starts the animation
     */
    public void run(Menu menu) {
        Map<Integer, MenuItem> items = menu.getItems();
        Inventory top = menu.getPlayer().getOpenInventory().getTopInventory();
        task = Task.create(Menu.PLUGIN)
                .execute(() -> {
                    List<Integer> slots = steps.get(currentStep);
                    if (slots == null || slots.isEmpty()) {
                        stop();
                        return;
                    }

                    for (int slot : slots) {
                        MenuItem item = items.get(slot);
                        if (item != null) {
                            top.setItem(slot, item.build());
                        }
                    }

                    currentStep++;
                })
                .repeat(ticksPerStep)
                .run();
    }

    public void stop() {
        task.cancel();
    }

    /**
     * Gets all slots from a beginning slot to delta amount extra.
     * The default direction is always right (east). Turn it left by entering a negative delta.
     *
     * @param   begin
     *          The beginning slot (inclusive)
     *
     * @param   delta
     *          The difference between the two, where the last slot is excluded. A delta of 9 will result in 9 elements, but only go up to slot 8.
     *
     * @return the slots between begin and delta amount away
     */
    protected List<Integer> getHorizontal(int begin, int delta) {
        List<Integer> result = new ArrayList<>();

        int direction = delta < 0 ? -1 : 1;
        for (int i = 0; i <= Math.abs(delta) + 1; i++) {
            result.add(begin + (i * direction));
        }
        return result;
    }

    /**
     * Gathers slots vertically down from the beginning slot.
     * The default direction is always down (south). Turn it left by entering a negative delta.
     *
     * @param   begin
     *          The beginning slot
     *
     * @param   delta
     *          How many rows the vertical selection goes down
     *
     * @return the vertical slots between the beginning slots
     */
    protected List<Integer> getVertical(int begin, int delta) {
        List<Integer> result = new ArrayList<>();

        int direction = delta < 0 ? -9 : 9;
        for (int i = 0; i <= Math.abs(delta); i++) {
            result.add(begin + (i * direction));
        }
        return result;
    }
}
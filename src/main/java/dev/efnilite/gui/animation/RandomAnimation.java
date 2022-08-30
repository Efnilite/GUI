package dev.efnilite.gui.animation;

import dev.efnilite.gui.util.Numbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * An animation which picks random slots to be set
 *
 * ---------   -----x---   -----x---   -xx--x---
 * --------- > x-------- > x--x---x- > x--x---x- > etc.
 * ---------   ----x----   -x--x----   -x--x--x-
 *
 * Expected duration: 18 ticks
 * Expected time: 900ms
 *
 * @author Efnilite
 */
public final class RandomAnimation extends MenuAnimation {

    // the amount of items per tick
    private int amountPerTick;
    private Random random;
    private List<Integer> available;

    @Override
    public void init(int rows) {
        random = ThreadLocalRandom.current();
        available = Numbers.getFromZero(rows * 9 - 1);

        ticksPerStep(1);
        amountPerTick = rows;
        // (9 * 2) / 1 = 18 ticks, and thus for
        // (9n * 2) / n = 18

        for (int i = 0; i < 9; i++) {
            add(i, getRandomSlots());
        }
    }

    private List<Integer> getRandomSlots() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < amountPerTick; i++) {
            int index = random.nextInt(available.size());
            result.add(available.get(index));
            available.remove(index);
        }
        return result;
    }
}

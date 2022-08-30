package dev.efnilite.gui.animation;

/**
 * An animation which goes from the middle to the sides.
 *
 * ---------   ----x----   ---xxx---   --xxxxx--
 * --------- > ----x---- > ---xxx--- > --xxxxx-- > etc.
 * ---------   ----x----   ---xxx---   --xxxxx--
 *
 * Expected duration: 15 ticks
 * Expected time: 750ms
 *
 * @author Efnilite
 */
public final class SplitMiddleOutAnimation extends MenuAnimation {

    @Override
    public void init(int rows) {
        ticksPerStep(2);
        // middle row first since uneven
        add(0, getVertical(4, rows));

        add(1, getVertical(5, rows));
        add(1, getVertical(3, rows));

        add(2, getVertical(6, rows));
        add(2, getVertical(2, rows));

        add(3, getVertical(7, rows));
        add(3, getVertical(1, rows));

        add(4, getVertical(8, rows));
        add(4, getVertical(0, rows));
    }
}

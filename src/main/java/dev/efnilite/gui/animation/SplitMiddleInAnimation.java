package dev.efnilite.gui.animation;

/**
 * An animation which goes from the sides to the middle
 *
 * ---------   x-------x   xx-----xx   xxx---xxx
 * --------- > x-------x > xx-----xx > xxx---xxx > etc.
 * ---------   x-------x   xx-----xx   xxx---xxx
 *
 * Expected duration: 15 ticks
 * Expected time: 750ms
 *
 * @author Efnilite
 */
public final class SplitMiddleInAnimation extends MenuAnimation {

    @Override
    public void init(int rows) {
        ticksPerStep(2);

        add(0, getVertical(8, rows));
        add(0, getVertical(0, rows));

        add(1, getVertical(7, rows));
        add(1, getVertical(1, rows));

        add(2, getVertical(6, rows));
        add(2, getVertical(2, rows));

        add(3, getVertical(5, rows));
        add(3, getVertical(3, rows));

        add(4, getVertical(4, rows));
    }
}

package dev.efnilite.gui.animation;

/**
 * An animation which waves towards the west.
 *
 * ---------   --------x   -------xx   ------xxx
 * --------- > --------x > -------xx > ------xxx > etc.
 * ---------   --------x   -------xx   ------xxx
 *
 * Expected duration: 20 ticks
 * Expected time: 1000ms
 *
 * @author Efnilite
 */
public final class WaveWestAnimation extends MenuAnimation {

    @Override
    public void init(int rows) {
        ticksPerStep(1);
        add(0, getVertical(8, rows));
        add(1, getVertical(7, rows));
        add(2, getVertical(6, rows));
        add(3, getVertical(5, rows));
        add(4, getVertical(4, rows));
        add(5, getVertical(3, rows));
        add(6, getVertical(2, rows));
        add(7, getVertical(1, rows));
        add(8, getVertical(0, rows));
    }
}

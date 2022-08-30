package dev.efnilite.gui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Number utils
 */
public class Numbers {

    /**
     * Returns a random real number between two bounds.
     *
     * @param   lower
     *          The lower bound (inclusive)
     *
     * @param   upper
     *          The upper bound (exclusive)
     *
     * @return a random real number between the two bounds
     */
    public static int random(int lower, int upper) {
        return ThreadLocalRandom.current().nextInt(lower, upper);
    }

    /**
     * Gets all ints from 0 (inclusive) to n (inclusive)
     *
     * @param   n
     *          The max value
     *
     * @return a list with all ints from 0 to n
     */
    public static List<Integer> getFromZero(int n) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            result.add(i);
        }
        return result;
    }

    /**
     * Gets the min int from an Array of ints
     *
     * @param   ints
     *          All ints
     *
     * @return the smallest of the values of parameter ints
     */
    public static int min(int... ints) {
        int min = Integer.MAX_VALUE;

        for (int value : ints) {
            min = Math.min(min, value);
        }

        return min;
    }

    /**
     * Gets the max int from an array of ints
     *
     * @param   ints
     *          All ints
     *
     * @return the int with the biggest value
     */
    public static int max(int... ints) {
        int max = Integer.MIN_VALUE;

        for (int value : ints) {
            max = Math.max(max, value);
        }

        return max;
    }

    /**
     * Returns all real numbers between two bounds
     *
     * @param   from
     *          The lower bound (inclusive)
     *
     * @param   to
     *          The upper bound (inclusive)
     *
     * @return a list of all real numbers between the two bounds
     */
    public static List<Integer> getFromTo(int from, int to) {
        List<Integer> result = new ArrayList<>();
        for (int i = min(from, to); i <= max(from, to); i++) {
            result.add(i);
        }
        return result;
    }
}

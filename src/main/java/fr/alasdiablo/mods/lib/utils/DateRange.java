package fr.alasdiablo.mods.lib.utils;

import java.util.Calendar;

/**
 * Utility class that provides methods and constants for determining seasons and special dates.
 * The seasons are defined as follows:
 * - Winter: November, December, January, February
 * - Spring: March, April
 * - Summer: May, June, July, August
 * - Autumn: September, October
 */
@SuppressWarnings("unused")
public class DateRange {

    /**
     * Represents the current month as a number (1-12)
     */
    public static final int CURRENT_MONTH = currentMonth();

    /**
     * Indicates whether the current date falls in winter (months 11, 12, 1, 2)
     */
    public static final boolean IS_WINTER = winter();

    /**
     * Indicates whether the current date falls in spring (months 3, 4)
     */
    public static final boolean IS_SPRING = spring();

    /**
     * Indicates whether the current date falls in summer (months 5, 6, 7, 8)
     */
    public static final boolean IS_SUMMER = summer();

    /**
     * Indicates whether the current date falls in autumn (months 9, 10)
     */
    public static final boolean IS_AUTUMN = autumn();

    /**
     * Indicates whether the current date is April 1st
     */
    public static final boolean IS_APRIL_FIRST = april();

    /**
     * Gets the current month as a number from 1-12
     *
     * @return The current month number, where January = 1 through December = 12
     */
    private static int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * Checks if the current date is April 1st
     *
     * @return true if the current date is April 1st, false otherwise
     */
    private static boolean april() {
        return currentMonth() == 4 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1;
    }

    /**
     * Checks if the current month falls in spring (March or April)
     *
     * @return true if the current month is spring, false otherwise
     */
    private static boolean spring() {
        return switch (CURRENT_MONTH) {
            case 3, 4 -> true;
            default -> false;
        };
    }

    /**
     * Checks if the current month falls in summer (May through August)
     *
     * @return true if the current month is summer, false otherwise
     */
    private static boolean summer() {
        return switch (CURRENT_MONTH) {
            case 5, 6, 7, 8 -> true;
            default -> false;
        };
    }

    /**
     * Checks if the current month falls in autumn (September or October)
     *
     * @return true if the current month is autumn, false otherwise
     */
    private static boolean autumn() {
        return switch (CURRENT_MONTH) {
            case 9, 10 -> true;
            default -> false;
        };
    }

    /**
     * Checks if the current month falls in winter (November, December, January or February)
     *
     * @return true if the current month is winter, false otherwise
     */
    private static boolean winter() {
        return switch (CURRENT_MONTH) {
            case 11, 12, 1, 2 -> true;
            default -> false;
        };
    }
}
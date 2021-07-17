package fr.alasdiablo.diolib.util;

import java.util.Calendar;

@SuppressWarnings("unused")
public class DateRange {
    // Season
    //   - Winter: 11, 12, 1, 2
    //   - Spring: 3, 4
    //   - Summer: 5, 6, 7, 8
    //   - Autumn: 9, 10

    public static final int CURRENT_MONTH = currentMonth();
    public static final boolean IS_WINTER = winter();
    public static final boolean IS_SPRING = spring();
    public static final boolean IS_SUMMER = summer();
    public static final boolean IS_AUTUMN = autumn();
    public static final boolean IS_APRIL_FIRST = april();

    private static int currentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    private static boolean april() {
        return currentMonth() == 4 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1;
    }

    private static boolean spring() {
        switch (CURRENT_MONTH) {
            case 3: case 4:
                return true;
            default:
                return false;
        }
    }

    private static boolean summer() {
        switch (CURRENT_MONTH) {
            case 5: case 6: case 7: case 8:
                return true;
            default:
                return false;
        }
    }

    private static boolean autumn() {
        switch (CURRENT_MONTH) {
            case 9: case 10:
                return true;
            default:
                return false;
        }
    }

    private static boolean winter() {
        switch (CURRENT_MONTH) {
            case 11: case 12: case 1: case 2:
                return true;
            default:
                return false;
        }
    }
}

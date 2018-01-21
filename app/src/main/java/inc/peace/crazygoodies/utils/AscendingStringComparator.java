package inc.peace.crazygoodies.utils;

import android.support.annotation.NonNull;

import java.util.Comparator;

/**
 * Created by Udit on 1/9/2018.
 */

public class AscendingStringComparator implements Comparator {
    @Override
    public int compare(Object string1, Object string2) {
        return string1.toString().toUpperCase().compareTo(string2.toString().toUpperCase());
    }


    @Override
    public boolean equals(Object o) {
        return false;
    }
}

package agrobank.task.util;

import agrobank.task.entity.City;

import java.util.Comparator;

public class CityComparatorByName implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        int compare = CharSequence.compare(o1.getName(), o2.getName());
        if (compare == 0) {
            return Long.compare(o1.getId(), o2.getId());
        }
        return compare;
    }
}

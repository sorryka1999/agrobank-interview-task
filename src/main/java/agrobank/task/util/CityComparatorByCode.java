package agrobank.task.util;

import agrobank.task.entity.City;

import java.util.Comparator;

public class CityComparatorByCode implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {
        int compare = Integer.compare(o1.getCode(), o2.getCode());
        if (compare == 0) {
            return Long.compare(o1.getId(), o2.getId());
        }
        return compare;
    }
}

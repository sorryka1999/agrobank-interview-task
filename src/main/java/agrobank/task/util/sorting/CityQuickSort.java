package agrobank.task.util.sorting;

import agrobank.task.entity.City;

import java.util.Comparator;
import java.util.List;

public class CityQuickSort implements SortingStrategy<City>{

    private Comparator<City> comparator;

    public CityQuickSort(Comparator<City> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<City> list) {
        quickSort(list, 0, list.size()-1);
    }

    private int partition(List<City> list, int low, int high) {
        City pivot = list.get(high);
        int i = low;

        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                City temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
                i++;
            }
        }

        City temp = list.get(i);
        list.set(i, list.get(high));
        list.set(high, temp);
        return i;
    }

    private void quickSort(List<City> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }
}

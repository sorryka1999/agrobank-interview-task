package agrobank.task.util.sorting;

import agrobank.task.entity.City;

import java.util.Comparator;
import java.util.List;

public class CityHeapSort implements SortingStrategy<City>{

    private Comparator<City> comparator;

    public CityHeapSort(Comparator<City> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(List<City> list) {
        int n = list.size();

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(list, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            City temp = list.get(0);
            list.set(0, list.get(i));
            list.set(i, temp);

            heapify(list, i, 0);
        }
    }

    private void heapify(List<City> list, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && comparator.compare(list.get(l), list.get(largest)) > 0)
            largest = l;

        if (r < n && comparator.compare(list.get(r), list.get(largest)) > 0)
            largest = r;

        if (largest != i) {
            City swap = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, swap);

            heapify(list, n, largest);
        }
    }
}

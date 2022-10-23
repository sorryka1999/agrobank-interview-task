package agrobank.task.util.sorting;

import java.util.List;

/**
 * Strategy Design Pattern from GOF Design Patterns
 */

public interface SortingStrategy<T> {
    public void sort(List<T> list);
}

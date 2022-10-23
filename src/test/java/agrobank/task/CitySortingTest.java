package agrobank.task;

import agrobank.task.entity.City;
import agrobank.task.repository.CityRepo;
import agrobank.task.util.CityComparatorByCode;
import agrobank.task.util.CityComparatorByName;
import agrobank.task.util.sorting.CityHeapSort;
import agrobank.task.util.sorting.CityQuickSort;
import agrobank.task.util.sorting.SortingStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootTest
public class CitySortingTest {

    @Autowired
    private CityRepo cityRepo;

    private static List<City> sortedByCode;
    private static List<City> sortedByName;

    @BeforeAll
    public static void initData() throws IOException {
        sortedByCode = readFile("src/test/resources/csv-files/sortedByCode.csv");
        sortedByName = readFile("src/test/resources/csv-files/sortedByName.csv");
    }

    private static List<City> readFile(String pathname) {
        File file = new File(pathname);
        List<City> list = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String[] row = reader.nextLine().split(",");
                list.add(new City(
                        Long.valueOf(row[0]),
                        Integer.valueOf(row[1]),
                        row[2]
                ));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Test
    void testCityQuickSortByCodeWithDataFromFile() {
        List<City> origin = readFile("src/test/resources/csv-files/originWithIndexWithoutHeader.csv");

        SortingStrategy<City> citySortingStrategy = new CityQuickSort(new CityComparatorByCode());
        citySortingStrategy.sort(origin);

        Assertions.assertTrue(equalOrderByCode(sortedByCode, origin));
        System.out.println("testQuickSortByCodeWithDataFromFile - success");
    }

    @Test
    void testCityQuickSortByNameWithDataFromFile() {
        List<City> origin = readFile("src/test/resources/csv-files/originWithIndexWithoutHeader.csv");

        SortingStrategy<City> citySortingStrategy = new CityQuickSort(new CityComparatorByName());
        citySortingStrategy.sort(origin);

        Assertions.assertTrue(equalOrderByName(sortedByName, origin));
        System.out.println("testQuickSortByNameWithDataFromFile - success");
    }

    @Test
    void testCityQuickSortByCodeWithDataFromDB() {
        List<City> citiesFromDB = cityRepo.findAll();

        SortingStrategy<City> citySortingStrategy = new CityQuickSort(new CityComparatorByCode());
        citySortingStrategy.sort(citiesFromDB);

        Assertions.assertTrue(equalOrderByCode(sortedByCode, citiesFromDB));
        System.out.println("testQuickSortByCodeWithDataFromDB - success");
    }

    @Test
    void testCityQuickSortByNameWithDataFromDB() {
        List<City> citiesFromDB = cityRepo.findAll();

        SortingStrategy<City> citySortingStrategy = new CityQuickSort(new CityComparatorByName());
        citySortingStrategy.sort(citiesFromDB);

        Assertions.assertTrue(equalOrderByName(sortedByName, citiesFromDB));
        System.out.println("testQuickSortByNameWithDataFromDB - success");
    }

    @Test
    void testCityHeapSortByCodeWithDataFromFile() {
        List<City> origin = readFile("src/test/resources/csv-files/originWithIndexWithoutHeader.csv");

        SortingStrategy<City> citySortingStrategy = new CityHeapSort(new CityComparatorByCode());
        citySortingStrategy.sort(origin);

        Assertions.assertTrue(equalOrderByCode(sortedByCode, origin));
        System.out.println("testHeapSortByCodeWithDataFromFile - success");
    }

    @Test
    void testCityHeapSortByNameWithDataFromFile() {
        List<City> origin = readFile("src/test/resources/csv-files/originWithIndexWithoutHeader.csv");

        SortingStrategy<City> citySortingStrategy = new CityHeapSort(new CityComparatorByName());
        citySortingStrategy.sort(origin);

        Assertions.assertTrue(equalOrderByName(sortedByName, origin));
        System.out.println("testHeapSortByNameWithDataFromFile - success");
    }

    @Test
    void testCityHeapSortByCodeWithDataFromDB() {
        List<City> citiesFromDB = cityRepo.findAll();

        SortingStrategy<City> citySortingStrategy = new CityHeapSort(new CityComparatorByCode());
        citySortingStrategy.sort(citiesFromDB);

        Assertions.assertTrue(equalOrderByCode(sortedByCode, citiesFromDB));
        System.out.println("testHeapSortByCodeWithDataFromDB - success");
    }

    @Test
    void testCityHeapSortByNameWithDataFromDB() {
        List<City> citiesFromDB = cityRepo.findAll();

        SortingStrategy<City> citySortingStrategy = new CityHeapSort(new CityComparatorByName());
        citySortingStrategy.sort(citiesFromDB);

        Assertions.assertTrue(equalOrderByName(sortedByName, citiesFromDB));
        System.out.println("testHeapSortByNameWithDataFromDB - success");
    }

    private boolean equalOrderByCode(List<City> l1, List<City> l2) {
        if (l1.size() != l2.size()) return false;
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).getCode().equals(l2.get(i).getCode())) return false;
        }
        return true;
    }

    private boolean equalOrderByName(List<City> l1, List<City> l2) {
        if (l1.size() != l2.size()) return false;
        for (int i = 0; i < l1.size(); i++) {
            if (!l1.get(i).getName().equals(l2.get(i).getName())) return false;
        }
        return true;
    }
}

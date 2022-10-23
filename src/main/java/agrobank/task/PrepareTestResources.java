package agrobank.task;

import agrobank.task.entity.City;
import agrobank.task.util.CityComparatorByCode;
import agrobank.task.util.CityComparatorByName;

import java.io.*;
import java.util.*;

public class PrepareTestResources {

    public static void main(String[] args) throws IOException {
        prepareCSVForCopyingToDB();
        createSortedTestFile();
    }

    private static void prepareCSVForCopyingToDB() throws IOException {
        List<City> cityList = readOriginCSV();
        writeCSV("src/test/resources/csv-files/originWithIndexWithoutHeader.csv", cityList);
    }

    private static List<City> readOriginCSV() {
        File file = new File("src/main/resources/csv-files/origin.csv");
        List<City> list = new ArrayList<>();
        try (Scanner reader = new Scanner(file)) {
            long counter = 0L;
            reader.nextLine();
            while (reader.hasNextLine()) {
                String[] row = reader.nextLine().split(",");
                list.add(new City(
                        counter++,
                        Integer.valueOf(row[0]),
                        row[1]
                ));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static void writeCSV(String fileName, Collection<City> list) {
        try (FileWriter writer = new FileWriter(fileName)) {
            list.forEach(city -> {
                try {
                    writer.write(city.getId() + "," + city.getCode() + "," + city.getName() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createSortedTestFile() throws IOException {
        List<City> cityList = readPreparedCSV();

        Set<City> sortedCityByCode = new TreeSet<>(new CityComparatorByCode());
        sortedCityByCode.addAll(cityList);
        writeCSV("src/test/resources/csv-files/sortedByCode.csv", sortedCityByCode);

        Set<City> sortedCityByName = new TreeSet<>(new CityComparatorByName());
        sortedCityByName.addAll(cityList);
        writeCSV("src/test/resources/csv-files/sortedByName.csv", sortedCityByName);
    }

    private static List<City> readPreparedCSV() {
        File file = new File("src/test/resources/csv-files/originWithIndexWithoutHeader.csv");
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
}

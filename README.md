# agrobank-interview-task

Firstly I run PrepareTestResources class to prepare test resources
Files:
 - src/test/resources/csv-files/originWithIndexWithoutHeader.csv
 - src/test/resources/csv-files/sortedByCode.csv
 - src/test/resources/csv-files/sortedByName.csv 

I'm creating sorted data with TreeSet
with comparators CityComparatorByCode and CityComparatorByName <br>
As tests should use correctly sorted data for checking
and TreeSet is the best solution to maintain this

To copy data from origin.csv, used
'COPY <table_name> FROM <path_to_csv> WITH (FORMAT csv);'
sql command <br>
I copied file 'src/test/resources/csv-files/originWithIndexWithoutHeader.csv'

Then wrote entity, repository and util packages and also tests <br>
I'm using Strategy GOF design pattern to create and use diverse sorting strategies

To run tests I used maven command 'mvn clean test'

But it faced an error with character which code is 237
while running command in command prompt

If I run 'src/test/java/agrobank/task/CitySortingTest.java'
test class within Intellij IDEA it passes all tests

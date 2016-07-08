####JARCUL
Stands for Java Row Comparator Utility Library

**Overview**

This library is designed to compare a Row of data with one or more Rows and obtain a resulting Row list with points based on the similarity of the fields being compared

**Usage**

```java
import jarcul.*;
import jarcul.datasource.*;

// Create a RowSetup object which holds the definition of each field

RowSetup rowSetup = new RowSetup(
        new NumberFieldSetup("height"),
        new TextFieldSetup("name")
);

// Create an object which implements RowIterator (CsvDataSource, MemoryTableDataSource and SqlDataSource implementations available)

MemoryTableDataSource memoryTableDataSource = new MemoryTableDataSource(rowSetup);


// In the case of MemoryTableDataSource you have to populate the table

memoryTableDataSource
        .addRow(new Row(170, "Pedro"))
        .addRow(new Row(145, "Pepe"))
        .addRow(new Row(120, "Paco"))
        .addRow(new Row(150, "José"))
        .addRow(new Row(180, "Juan"));


// Define the row to be tested against the RowIterator

Row searchRow = new Row(150, "Pepe");


// Create the comparator object

RowComparator rowComparator = new RowComparator(rowSetup);


// Calculate and get the results

List<ComparisonRowResult> results = rowComparator.compare(memoryTableDataSource, searchRow);
```

For advanced usage, look at the FieldSetup implementations constructor overloads and the FieldComparator implementations found at jarcul.comparator

**Add as Maven dependency**

First edit the pom.xml of your project and add the following repository

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

Then add a new dependency

	<dependency>
	    <groupId>com.github.daneilsan</groupId>
	    <artifactId>jarcul</artifactId>
	    <version>v1.2</version>
	</dependency>

To use other dependency manager, refer to [this site](https://jitpack.io/#daneilsan/jarcul)

**How to build**

Requirements

 - Maven 3 or higher
 - JDK 8 or higher

 Execute the following goals:

`mvn clean compile assembly:single`


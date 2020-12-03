package fr.univrennes1.istic.wikipediamatrix;

import java.io.FileWriter;
import com.opencsv.CSVWriter;
public class WritingToCSV {
   public static void main(String args[]) throws Exception {
      //Instantiating the CSVWriter class
      CSVWriter writer = new CSVWriter(new FileWriter("outputCSV.csv"));
      //Writing data to a csv file
      String line1[] = {"id", "name", "salary", "start_date", "dept"};
      String line2[] = {"1", "Krishna", "2548", "2012-01-01", "IT"};
      String line3[] = {"2", "Vishnu", "4522", "2013-02-26", "Operations"};
      String line4[] = {"3", "Raja", "3021", "2016-10-10", "HR"};
      String line5[] = {"4", "Raghav", "6988", "2012-01-01", "IT"};
      //Writing data to the csv file
      writer.writeNext(line1);
      writer.writeNext(line2);
      writer.writeNext(line3);
      writer.writeNext(line4);
      //Flushing data from writer to file
      writer.flush();
      System.out.println("Data entered");
   }
}
package fr.univrennes1.istic.wikipediamatrix;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
	
	public static int[] read() {

		String csvFile = "/home/jean-yves/Documents/Ensai/SortiesCSV/CSVtest.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int cols = 0;
		int rows = 0;
		int[] rowsAndCols = {cols,rows};
		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				rows = rows + 1;
				// use comma as separator
				String[] record = line.split(cvsSplitBy);
				cols = record.length;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		rowsAndCols[0] = rows;
		rowsAndCols[1] = cols;
		return rowsAndCols;
	}
}

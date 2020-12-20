package fr.univrennes1.istic.wikipediamatrix;

import java.io.IOException;

import com.opencsv.CSVWriter;

public class Table {
	int idURL;
	int idTable;
	String titleURL;
	int nbRows;
	int nbColumns;
	int nbHeaders;
	int nbCells;
	
	public void addToCSVstats(CSVWriter csvStats) throws IOException {
		String currentLine[] = new String[6];
		currentLine[0] = Integer.toString(idURL);
		currentLine[1] = Integer.toString(idTable);
		currentLine[2] = Integer.toString(nbRows);
		currentLine[3] = Integer.toString(nbColumns);
		currentLine[4] = Integer.toString(nbHeaders);
		currentLine[5] = Integer.toString(nbCells);
		csvStats.writeNext(currentLine);
	}
	
}

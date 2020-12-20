package fr.univrennes1.istic.wikipediamatrix;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.FileWriter;

import com.opencsv.CSVWriter;

public class WikipediaHTMLExtractor {

	public Document getDocument(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}

	public static Table writeToCSV(int theIdURL, int theIdTable, Element tabl, String filename) throws Exception {
		// Instantiating the CSVWriter class
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		Table theTable = new Table();
		theTable.idURL = theIdURL;
		theTable.idTable = theIdTable;
		Elements rows = tabl.select("tr");
		int nbRows = rows.size();
		theTable.nbRows = nbRows;
		theTable.nbCells = 0;
		int j = 0;
		for (Element row : rows) {
			j = j + 1;
			Elements headers = row.select("th");
			int nbHeaders = headers.size();
			theTable.nbHeaders = 0;
			String currentRow[] = new String[nbHeaders];
			if (nbHeaders != 0) {
				theTable.nbHeaders++;
				int k = 0;
				for (Element header : headers) {
					currentRow[k] = header.text();
					k = k + 1;
					theTable.nbCells++;
				}
				writer.writeNext(currentRow);
			} else {
				Elements columns = row.select("td");
				int nbColumns = columns.size();
				theTable.nbColumns = nbColumns;
				int k = 0;
				String currentRow2[] = new String[nbColumns];
				for (Element column : columns) {
					currentRow2[k] = column.text();
					k = k + 1;
					theTable.nbCells++;
				}
				writer.writeNext(currentRow2);
			}
		}
		// Flushing data from writer to file
		writer.flush();
		writer.close();
		return theTable;
	}

	// This method extracts tables form an URL
	// and returns and array of tables.

	public static Elements extraction(String theURL) throws IOException {
		WikipediaHTMLExtractor wiki = new WikipediaHTMLExtractor();
		// Reading an HTML document

		String url = theURL;
		try {
			Document doc = wiki.getDocument(url);
			// Reading <table> tags
			Elements theRawTables = doc.select("table");
			int nbRawTables = theRawTables.size();
			// theRawTables contain only HTML codes between <TABLE>...</TABLE>
			// tags

			// theTables will contain 'wikitable sortable' tables
			// from theRawTables

			Elements theTables = new Elements();
			
			for (int i = 0; i < nbRawTables; i++) {
				Element tab = theRawTables.get(i);
				// We want to ignore the tables containing 'rowspan'
				// or 'colspan' attributes in 'td' or 'tr' tags
				boolean tabWithouCS = tab.select("td[colspan]").size()==0;
				boolean tabWithouRS = tab.select("tr[rowspan]").size()==0;
				if (tab.className().equals("wikitable sortable") && tabWithouCS && tabWithouRS) {
					theTables.add(tab);
				}
			}

			return theTables;
		} catch (Exception e) {
			System.out.println("CAUTION: This URL could not be reached. The program will ignore it and go on.");
			
			return new Elements();
		}
	}
}

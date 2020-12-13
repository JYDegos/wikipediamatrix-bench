package fr.univrennes1.istic.wikipediamatrix;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;


public class WikipediaHTMLExtractor {
	
	public  Document getDocument(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}
	
	public static void writeToCSV(Element tabl, String filename) throws Exception {
	    // Instantiating the CSVWriter class
		CSVWriter writer = new CSVWriter(new FileWriter(filename));
		Elements rows = tabl.select("tr");
		int nbRows = rows.size();
		int j=0;
		for(Element row : rows) {
			j = j + 1;
			Elements headers = row.select("th");
			int nbHeaders = headers.size();
			String currentRow[] = new String[nbHeaders];
			if (nbHeaders!=0) {
				int k = 0;
				for (Element header : headers) {
					currentRow[k] = header.text();
					k=k+1;
				}
				writer.writeNext(currentRow);
			} else {
				Elements columns = row.select("td");
				int nbColumns = columns.size();
				int k = 0;
				String currentRow2[] = new String[nbColumns];
				for (Element column : columns) {
					currentRow2[k] = column.text();
					k=k+1;
				}
				writer.writeNext(currentRow2);
			}
		}
		// Flushing data from writer to file
	    writer.flush();
	}
	
	public String conversion(Element tabl) {
		Elements rows = tabl.select("tr");
		int nbRows = rows.size();
		int j=0;
		for(Element row : rows) {
			j = j + 1;
			Elements headers = row.select("th");
			int nbHeaders = headers.size();
			if (nbHeaders!=0) {
				int k = 0;
				String rowCSV ="";
				for (Element header : headers) {
					k = k+1;
					rowCSV = rowCSV+header.text();
					if (k<nbHeaders) {
						rowCSV = rowCSV + ";";
					} else {
						rowCSV = rowCSV + "\n";
					}
				}
				// System.out.println("Ligne "+ j + " : " +ligneCSV);
			} else {
				Elements columns = row.select("td");
				int nbColumns = columns.size();
				int k = 0;
				String rowCSV ="";
				for (Element columun : columns) {
					k = k+1;
					rowCSV = rowCSV+columun.text();
					if (k<nbColumns) {
						rowCSV = rowCSV + ";";
					} else {
						rowCSV = rowCSV + "\n";
					}
				}
			}
		}
		return "Done";
	}
	
	public static ArrayList<String> toStrings(Elements tables) {
		int nbTables = 0;
	    int i= 0; // Loop variables
		WikipediaHTMLExtractor wiki = new WikipediaHTMLExtractor();
	    nbTables = tables.size(); // computes the number of tables
	    ArrayList<String> theCSVs = new ArrayList<String>();
	    for (i=0; i<nbTables; i++) {
	    	Element aTable = tables.get(i);
	    	String aCSV = wiki.conversion(aTable);
	    	theCSVs.add(aCSV);
	    }
	    return theCSVs;
	}
	
	public void display(ArrayList<String> theStrings) {
		int len = theStrings.size();
		int i=0;
		for (i=0; i<len; i++) {
			System.out.println(theStrings.get(i));
		}
		System.out.println("\n");
	}
	
	// This method extracts tables form an URL
	// and returns and array of tables.
	
	public static Elements extraction(String theURL) throws IOException {
		WikipediaHTMLExtractor wiki = new WikipediaHTMLExtractor();
		
		// Reading an HTML document
		
		String url = theURL;
		Document doc = wiki.getDocument(url);
		// Reading <table> tags
		Elements theRawTables = doc.select("table");
		int nbRawTables = theRawTables.size();
		// theRawTables contain only HTML codes between <TABLE>...</TABLE>
		// tags
		
		// theTables will contain 'wikitable sortable' tables
		// from theRawTables
		
		Elements theTables = new Elements();
		for(int i=0; i<nbRawTables; i++) {
			Element tab = theRawTables.get(i);
			if (tab.className().equals("wikitable sortable")) {
				theTables.add(tab);
			} 
		}
		return theTables;
		}
}

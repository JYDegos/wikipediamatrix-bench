package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class BenchTest {

	/*
	 * the challenge is to extract as many relevant tables as possible and save them
	 * into CSV files from the 300+ Wikipedia URLs given see below for more details
	 **/
	@Test
	public void testBenchExtractors() throws Exception {

		String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
		// directory where CSV files are exported (HTML extractor)
		String outputDirHtml = "output" + File.separator + "html" + File.separator;
		assertTrue(new File(outputDirHtml).isDirectory());
		// directory where CSV files are exported (Wikitext extractor)
		String outputDirWikitext = "output" + File.separator + "wikitext" + File.separator;
		assertTrue(new File(outputDirWikitext).isDirectory());

		File file = new File("inputdata" + File.separator + "wikiurls.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String url;
		int nurl = 0;
		while ((url = br.readLine()) != null) {
			// Test
			System.out.println("Page " + nurl + "\n");
			String wurl = BASE_WIKIPEDIA_URL + url;
			System.out.println("Wikipedia url: " + wurl);
			// TODO: do something with the Wikipedia URL
			// (ie extract relevant tables for correct URL, with the two extractors)
			Elements theTables = WikipediaHTMLExtractor.extraction(wurl);
			// computes the number of tables in the URL
			int nbTables = theTables.size();
			System.out.println(nbTables+"\n");
			// converts the tables to an array of Strings
			ArrayList<String> theCSVs = WikipediaHTMLExtractor.toStrings(theTables);

			// writes each of the <nbTables> tables to CSV files

			for (int k = 0; k < nbTables; k++) {
				int index = k + 1;
				// building the correct filename for the k-th table
				String csvFileName = mkCSVFileName(url, index);
				System.out.println("CSV file name: " + csvFileName);
				// selecting the k-th table in the webpage
				Element tab = theTables.get(k);
				try {
					// writing the k-th tables to the corresponding
					// csvFile
					csvFileName = outputDirHtml + csvFileName;
					WikipediaHTMLExtractor.writeToCSV(tab, csvFileName);
					System.out.println(csvFileName+" WRITTEN!\n");
				} catch (Exception e) {
					System.out.println("ERROR!\n");
				}
			}

			// for exporting to CSV files, we will use mkCSVFileName
			// example: for
			// https://en.wikipedia.org/wiki/Comparison_of_operating_system_kernels
			// the *first* extracted table will be exported to a CSV file called
			// "Comparison_of_operating_system_kernels-1.csv"
			// String csvFileName = mkCSVFileName(url, 1);
			// System.out.println("CSV file name: " + csvFileName);
			// the *second* (if any) will be exported to a CSV file called
			// "Comparison_of_operating_system_kernels-2.csv"

			// TODO: the HTML extractor should save CSV files into output/HTML
			// see outputDirHtml

			// TODO: the Wikitext extractor should save CSV files into output/wikitext
			// see outputDirWikitext

			nurl++;
		}

		br.close();
		assertEquals(nurl, 336);

	}

	private String mkCSVFileName(String url, int n) {
		return url.trim() + "-" + n + ".csv";
	}

}

package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor;
import fr.univrennes1.istic.wikipediamatrix.CSVReader;

public class WikipediaHTMLExtractorTest {

	@Test
	public void testHTMLExtractors1() throws Exception {
		fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor.extraction("https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs");
		int[] rowsAndCols = fr.univrennes1.istic.wikipediamatrix.CSVReader.read();
		assertTrue(rowsAndCols[0]==73 && rowsAndCols[1]==22);
		System.out.println("Done.\n");
	}
	
	@Test
	public void testHTMLExtractors2() throws Exception {
		fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor.extraction("https://en.wikipedia.org/wiki/Comparison_of_Axis_&_Allies_games");
		assertTrue(1>0);
		int[] rowsAndCols = fr.univrennes1.istic.wikipediamatrix.CSVReader.read();
		System.out.println("Done.\n");
	}
	
}
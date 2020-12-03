package fr.univrennes1.istic.wikipediamatrix;

import static org.junit.Assert.*;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

import fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor;
import fr.univrennes1.istic.wikipediamatrix.CSVReader;

public class WikipediaHTMLExtractorTest {

	@Test
	public void testHTMLExtractors() throws Exception {
		fr.univrennes1.istic.wikipediamatrix.WikipediaHTMLExtractor.extraction();
		int[] rowsAndCols = fr.univrennes1.istic.wikipediamatrix.CSVReader.read();
		assertTrue(rowsAndCols[0]==73 && rowsAndCols[1]==22);
		System.out.println("Done.\n");
	}
}
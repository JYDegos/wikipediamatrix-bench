package fr.univrennes1.istic.wikipediamatrix;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WikipediaHTMLExtractor {
	
	public  Document getDocument(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		return doc;
	}
	
	public String conversion(Element tableau) {
		String chaine = "";
		Elements lignes = tableau.select("tr");
		int nbLignes = lignes.size();
		int j=0;
		for(Element ligne : lignes) {
			j = j + 1;
			System.out.println("Ligne "+j+", ");
			Elements entetes = ligne.select("th");
			int nbEntetes = entetes.size();
			if (nbEntetes!=0) {
				int k = 0;
				for (Element entete : entetes) {
					k = k+1;
					/* if(j==0 or j==73) {
						System.out.println("Colonne "+k+" :\n");
						System.out.println(colonne);
					} */
				}
				System.out.println(nbEntetes+" entetes \n");
			} else {
				Elements colonnes = ligne.select("td");
				int nbColonnes = colonnes.size();
				int k = 0;
				for (Element colonne : colonnes) {
					k = k+1;
					/* if(j==0 or j==73) {
						System.out.println("Colonne "+k+" :\n");
						System.out.println(colonne);
					} */
				}
				System.out.println(nbColonnes+" colonnes \n");
			}
		}
		return "Traite";
	}
	
	public ArrayList<String> traitement(Elements tableaux) {
		int nbTableaux = 0;
	    int i= 0; // variables de boucles
		WikipediaHTMLExtractor wiki = new WikipediaHTMLExtractor();
	    nbTableaux = tableaux.size(); // calcul du nb de tableaux
	    ArrayList<String> lesCSV = new ArrayList<String>();
	    for (i=0; i<nbTableaux; i++) {
	    	Element unTableau = tableaux.get(i);
	    	String unCSV = wiki.conversion(unTableau);
	    	lesCSV.add(unCSV);
	    }
	    return lesCSV;
	}
	
	public void affichage(ArrayList<String> lesChaines) {
		int taille = lesChaines.size();
		int i=0;
		for (i=0; i<taille; i++) {
			System.out.println(lesChaines.get(i));
		}
		System.out.println("\n");
	}
	
	// Programme principal 
	
	public static void main (String args[]) throws IOException {
		WikipediaHTMLExtractor wiki = new WikipediaHTMLExtractor();
		
		// Lecture d'un document HTML
		
		String url = "https://en.wikipedia.org/wiki/Comparison_of_digital_SLRs";
		Document doc = wiki.getDocument(url);
		
		// Lecture des balises table
	
		Elements lesTableauxBruts = doc.select("table");
		int nombreTableauxBruts = lesTableauxBruts.size();
		System.out.println("Nombre de tableaux bruts " + nombreTableauxBruts);
		
		// Récupération des tableaux corrects -> lesTableaux
		
		Elements lesTableaux = new Elements();
		for(int i=0; i<nombreTableauxBruts; i++) {
			Element tab = lesTableauxBruts.get(i);
			if (tab.className().equals("wikitable sortable")) {
				System.out.println(i+ "\n");
				lesTableaux.add(tab);
			} 
		}
		int nombreTableaux = lesTableaux.size();
		System.out.println("Nombre de tableaux " + nombreTableaux);
		
		// Il n'y en a qu'un : c'est fait pour.
		
		// Maintenant, on veut traiter les bons tableaux,
		// i. e. récupérer un tableau de chaînes de caractères
		// en partant de "lesTableaux"
		
		ArrayList<String> lesCSV = wiki.traitement(lesTableaux);
		
		// Les transformer en fichiers
		
		wiki.affichage(lesCSV);
		
		// Les transformer en fichiers

	}
}

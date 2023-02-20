package textproc;

import java.util.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland", "norge", "nils" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		Set<String> set = new HashSet<String>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			set.add(word);
		}
		GeneralWordCounter gwc = new GeneralWordCounter(set);

		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		while (s.hasNext()) {
			String word = s.next().toLowerCase();
			gwc.process(word);
		}
		gwc.report();

		scan.close();
		s.close();

		long t1 = System.nanoTime();
		System.out.println("Time: " + (t1-t0)/1000000 + " ms");
		// MultiWordCounter mwc = new MultiWordCounter(REGIONS);
		// Scanner s = new Scanner(new File("nilsholg.txt"));
		// s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		//
		//
		// while (s.hasNext()) {
		// String word = s.next().toLowerCase();
		// mwc.process(word);
		// }
		//
		// s.close();
		//
		// mwc.report();
	}
}
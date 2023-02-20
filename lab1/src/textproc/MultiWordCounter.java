package textproc;

import java.util.*;

public class MultiWordCounter implements TextProcessor {
	// private String[] words;
	// private int n;
	private Map<String, Integer> ls = new TreeMap<String, Integer>();

	public MultiWordCounter(String[] words) {
		for (String s : words) {
			ls.put(s, 0);
		}
	}

	public void process(String w) {
		for (String key : ls.keySet()) {
			if (w.equals(key)) {
				ls.put(key, ls.get(key) + 1);
			}
		}
	}

	public void report() {
		for (String key : ls.keySet()) {
		System.out.println(key + ": " + ls.get(key));
		}
	}

}

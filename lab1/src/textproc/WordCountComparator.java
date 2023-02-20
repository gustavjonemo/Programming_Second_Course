package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {

	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {

		int v = (o1.getValue().compareTo(o2.getValue()));
		if (v == 0) {
			return o2.getKey().compareTo(o1.getKey());
		} else {
			return v;
		}
	}
}

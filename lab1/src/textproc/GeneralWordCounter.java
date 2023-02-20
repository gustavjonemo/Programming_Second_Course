package textproc;

import java.util.*;

public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m = new TreeMap<String, Integer>();
	private Set<String> s;

	public GeneralWordCounter(Set<String> s) {
		this.s = s;
	}

	@Override
	public void process(String w) {
		if (!s.contains(w)) {
			if (m.containsKey(w)) {
				m.put(w, m.get(w) + 1);
			} else {
				m.put(w, 1);
			}
		}
	}

	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for (int j = 1, i = wordList.size() - j; i > wordList.size() - j - 5; i--) {
			System.out.println(wordList.get(i));
		}

		// for (String i : m.keySet()) {
		// if(m.get(i) >= 200){
		// System.out.println(i + ": " + m.get(i));
		// }
		// }
	}
	
	public Set<Map.Entry<String, Integer>> getWords(){
		return m.entrySet();
		
	}
}

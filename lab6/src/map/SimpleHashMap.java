package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private int capacity;
	private double loadfactor;
	private int size;

	public static void main(String[] args) {
		SimpleHashMap<Integer, Integer> hm = new SimpleHashMap<Integer, Integer>();
		int range = 30;
		System.out.println("Duplicates: ");
		for (int i = 0; i < 20; i++) {
			int n = (int) Math.round(Math.random() * (-range * 2) + range);
			if (hm.put(n, n) != null) {
				System.out.println(n);
			}
		}
		System.out.println(hm.show());
	}

	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		this.capacity = 16;
		table = (Entry<K, V>[]) new Entry[capacity];
		loadfactor = 0.75;
		size = 0;
	}

	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		this.capacity = capacity;
		table = (Entry<K, V>[]) new Entry[capacity];
		loadfactor = 0.75;
		size = 0;
	}

	public static class Entry<K, V> implements Map.Entry<K, V> {
		private K k;
		private V v;
		public Entry<K, V> next;

		public Entry(K k, V v) {
			this.k = k;
			this.v = v;
		}

		@Override
		public K getKey() throws IllegalStateException {
			return k;
		}

		@Override
		public V getValue() throws IllegalStateException {
			return v;
		}

		@Override
		public V setValue(V value) {
			V v_temp = v;
			v = value;
			return v_temp;
		}

		@Override
		public String toString() {
			return k + " = " + v;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object object) {
		K key = (K) object;
		if (find(index(key), key) == null) {
			return null;
		} else {
			return find(index(key), key).getValue();
		}

	}

	@Override
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	public V put(K key, V value) {
		if (size > capacity * loadfactor) {
			rehash();
		}
		Entry<K, V> new_entry = new Entry<K, V>(key, value);
		int index = index(key);
		if (table[index] == null) {
			table[index] = new_entry;
			size++;
		} else if (table[index].getKey().equals(key)) {
			V temp_V = table[index].getValue();
			table[index].setValue(value);
			return temp_V;
		} else {
			Entry<K, V> temp_E = table[index].next;
			while (temp_E != null) {
				if (temp_E.getKey().equals(key)) {
					V temp_V = temp_E.getValue();
					temp_E.setValue(value);
					return temp_V;
				} else {
					temp_E = temp_E.next;
				}
			}
			new_entry.next = table[index];
			table[index] = new_entry;
			size++;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object object) {
		K key = (K) object;
		int index = index(key);
		Entry<K, V> temp_E = find(index, key);
		if (table == null) {

		}
		if (temp_E == null) {

		} else if (temp_E.equals(table[index])) {
			V temp_V = table[index].getValue();
			if (table[index].next != null) {
				table[index] = table[index].next;
				size--;
				return temp_V;
			} else {
				table[index] = null;
				size--;
				return temp_V;
			}
		} else if (!(temp_E.equals(table[index]))) {
			Entry<K, V> temp_E_current = table[index];
			Entry<K, V> temp_E_next = table[index].next;
			while (temp_E_next != null) {
				if (temp_E.equals(temp_E_next)) {
					V temp_V = temp_E_next.getValue();
					temp_E_current.next = temp_E_next.next;
					size--;
					return temp_V;
				} else {
					temp_E_current = temp_E_next;
					temp_E_next = temp_E_next.next;
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	public String show() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < capacity; i++) {
			if (table[i] == null) {
				sb.append("\n" + i + "\tnull");
			} else {
				sb.append("\n" + i + "\t" + table[i].toString());
				Entry<K, V> E_temp = table[i].next;
				while (E_temp != null) {
					sb.append("  " + E_temp.toString());
					E_temp = E_temp.next;
				}
			}
		}
		sb.append("\n\nCapacity: " + capacity);
		return sb.toString();
	}

	private int index(K key) {
		return Math.abs(key.hashCode() % capacity);
	}

	private Entry<K, V> find(int index, K key) {
		if (table[index] == null) {

		} else if (table[index].getKey().equals(key)) {
			return table[index];
		} else {
			Entry<K, V> E_temp = table[index].next;
			while (E_temp != null) {
				if (E_temp.getKey().equals(key)) {
					return E_temp;
				} else {
					E_temp = E_temp.next;
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void rehash() {
		// int old_capacity = capacity;
		// capacity = capacity * 2;
		// Entry<K, V>[] temp_table = (Entry<K, V>[]) new Entry[capacity];
		// for (int i = 0; i < old_capacity; i++) {
		// temp_table[i] = table[i];
		// }
		// table = temp_table;

		Entry<K, V>[] old_table = table;
		int old_capacity = capacity;
		capacity = capacity * 2;
		table = (Entry<K, V>[]) new Entry[capacity];
		size = 0;
		for (int i = 0; i < old_capacity; i++) {
			Entry<K, V> temp_E = old_table[i];

			while (temp_E != null) {
				put(temp_E.getKey(), temp_E.getValue());
				temp_E = temp_E.next;
			}
		}
	}

}

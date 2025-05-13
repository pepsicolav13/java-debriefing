public class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() { return key; }
    public V getValue() { return value; }


    public Pair<V, K> swap() {
        return new Pair<>(value, key);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<K, V> other = (Pair<K, V>) o;
        return this.key.equals(other.key);
    }

    @Override
    public String toString() {
        return "{ 'K': '" + key + "', 'V': '" + value + "'}";
    }
}

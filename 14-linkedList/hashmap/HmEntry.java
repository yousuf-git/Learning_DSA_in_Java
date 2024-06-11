package hashmap;

public class HmEntry<K, V> {
    HmNode<K, V> node;
    HmEntry(HmNode<K, V> node) {
        this.node = node;
    }
    public K getKey() {
        return node.key;
    }
    public V getValue() {
        return node.value;
    }
}

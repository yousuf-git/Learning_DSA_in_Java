/*----------HashMap Entry class that contains a HashMap node that has 2 attributes : key and value---------*/

package hashmap;

public class HmEntry<K, V> {
    HmNode<K, V> node; // see HmNode.java for its implementation

    HmEntry(HmNode<K, V> node) {
        this.node = node;
    }

    public K getKey() {
        return node.key;
    }

    public V getValue() {
        return node.value;
    }

    public V setValue(V value) {
        V currValue = node.value; // get the old value
        node.value = value; // update the value
        return currValue; // return the old value
    }
}

// Reference to official doc of Map.Entry class
// https://docs.oracle.com/javase/8/docs/api/java/util/Map.Entry.html

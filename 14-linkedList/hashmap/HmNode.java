/* HashMap Node structure
 * Hasmap node has 2 components in it
 * 1. key --> Type K
 * 2. value --> Type V
 * 
 * 
*/ 


package hashmap;



public class HmNode<K, V> {
    K key;
    V value;
    public HmNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

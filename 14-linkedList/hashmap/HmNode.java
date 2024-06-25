/* HashMap Node structure
 * Hasmap node has 2 components in it
 * 1. key --> Type K (generic)
 * 2. value --> Type V (generic)
*/

package hashmap;

public class HmNode<K, V> {
    public K key;
    public V value;

    /** Constructor
     * @param key
     * @param value
     */
    public HmNode(K key, V value) {
        this.key = key;
        this.value = value;
    }
}
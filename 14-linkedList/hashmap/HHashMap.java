/*
 * Harry's HashMap class
 * It includes basic implementation of HashMap Data structure by using array of linked list
 * 
 * Some Components used during the implementation of HashMap:
 * 
 * 1. Array of linked lists is indicated by bucket
 * Each bucket has linked list attached to it that contains nodes of type HmNode
 * HmNode has 2 components : key and value
 * 
 * 2. N --> Number of buckets
 * 3. n --> Total number of nodes / key-value pairs in the map
 * 4. limit --> limit at which rehashing will be done
 *      if n/N > limit => increase the size of buckets (N) = N*2
 *  
 * Incldues the following methods:
 * 01. put(K key, V value)
 * 02. searchInLinkedLL(K key, int bucketIdx) => search a key in linked list at bucketIdx
 * 03. getBucketIdx() => returns index of bucket at which to add the node, based on hashCode of key
 * 04. get(key) => returns value of key, null if key doesn't exist
 * 05. display()
 * 06. remove(K key) => if only key is known. returns true if key is removed successfully, false otherwise
 * 07. remove(K key, V value) => if key and value both are known
 * 08. size() => returns total number of nodes
 * 09. containsKey(K key) => returns true if passed key exists
 * 10. containsValue(V value) => returns true if passed value exists
 * 11. keySet() => returns all keys of map in the form of HashSet<K>
 * 12. entrySet() => returns all entries of map in the form of HashSet<HmEntry<K, V>>
 * 13. isEmpty() => returns true if there is no node in the HashMap, false otherwise
 * 
*/
package hashmap;

import java.util.HashSet;
import java.util.LinkedList;
// import java.util.Set;

import java.util.Iterator;

class HHashMap<K, V> {
    int N; // number of buckets
    double limit; // limit for rehashing
    int n; // number of total nodes
    LinkedList<HmNode<K, V>>[] buckets; // buckets / array of linked lists of type HmNode

    @SuppressWarnings("unchecked") // to remove type related warning for linked list
    HHashMap() {
        N = 4; // initial number of buckets
        n = 0; // initially there are 0 nodes
        limit = 2.0;
        buckets = new LinkedList[N];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>(); // initializing blank linked lists
        }
    }

    private int getBucketIdx(K key) {
        /*
         * This method generates an integer against the key using hashCode() method
         * That integer can be +ve or -ve and is same untill number of buckets (N) is
         * changed
         * Use Math.abs() to make that special integer +ve
         * Now use % operator to make that intger within the range of buckets (array)
         * size
         * (Any Integer) % n is always Between 0 and n-1
         */
        int bucketIdx = key.hashCode(); // can be any random +ve integer
        // System.out.println("-----Get Bucket Index Method------");
        // System.out.println("\n" + key);
        // System.out.println("Bucket Index: " + Math.abs(bucketIdx) % N + "\n");
        return Math.abs(bucketIdx) % N; // convert into valid index
    }

    private int searchInLinkedLL(K key, int bucketIdx) {
        /*
         * search a key in linked list at bucketIdx
         * If key is found - return the index of key, otherwise return -1
         */
        // System.out.println("-----Seach in Linked List Method------");
        // System.out.println(key);
        // System.out.println("Index in linked list: " +
        // buckets[bucketIdx].indexOf(key));
        LinkedList<HmNode<K, V>> list = buckets[bucketIdx];
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key.equals(key)) {
                // System.out.println("found");
                return i;
            }
        }
        // System.out.println("not found");
        return -1;
        // return buckets[bucketIdx].indexOf(key);
    }

    /*------------Rehashing method if n/N increases limit-------------- */
    @SuppressWarnings("unchecked") // to remove type related warning for linked list
    private void reHash() {
        n = 0; // reset the number of nodes
        System.out.println("_____________________________________Rehashing Started !");
        LinkedList<HmNode<K, V>>[] oldBuckets = buckets; // get the old buckets
        N *= 2; // increase number of buckets as per requirements
        buckets = new LinkedList[N]; // create new buckets
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>(); // initializing buckets with new blank linked lists
        }
        // put all old nodes into new buckets
        LinkedList<HmNode<K, V>> list;
        for (int i = 0; i < oldBuckets.length; i++) {
            list = oldBuckets[i]; // grab list from each bucket
            // if (list.size() == 0) {
            // continue;
            // }
            for (HmNode<K, V> node : list) { // grab each node from list
                put(node.key, node.value); // grab key and value from each node and put in the new buckets
            }
        }
        System.out.println("_____________________________________Rehashing done !");
    }

    public void put(K key, V value) {
        /*
         * Step 1.
         * Search in specific linked list if key already exists
         * That specific linked list means we need the bucket index at which we have to
         * search
         * Bucket Index will be generated by getBucketIdx()
         */
        int bucketIdx = getBucketIdx(key);
        /*
         * Step 2.
         * At bucketIdx there is a list
         * Search in that linked list if key exixts there or not
         */
        int keyIdx = searchInLinkedLL(key, bucketIdx); // will return index of key if it exixts, otherwise -1
        // Step 3. Add / Update node based on keyIdx
        if (keyIdx == -1) { // key doesn't exist
            HmNode<K, V> node = new HmNode<K, V>(key, value); // create a new node
            buckets[bucketIdx].add(node); // add the node into the linked list at bucket index generated against the key
            n++; // update nummber of nodes
            // System.out.println(size());
            // System.out.println( (double) (n) / (double) (N) );
            if ((double) (n) / (double) (N) > limit) { // check for rehashing
                reHash();
            }
        } else { // key exist already
            // list => buckets[bucketIdx]
            // node => buckets[bucketIdx].get(keyIdx)
            // node.value => previous value
            buckets[bucketIdx].get(keyIdx).value = value; // update the current value of key in list
        }

    }

    /*--------To Display All Key-Value Pairs / Nodes in the HashMap------ */
    public void display() {
        System.out.println("----------Nodes in Map-------");
        for (LinkedList<HmNode<K, V>> list : buckets) { // goto each list in the bucket
            System.out.println("***List Size: " + list.size());
            for (HmNode<K, V> node : list) { // goto each node in the list
                System.out.println(node.key + " = " + node.value); // display the key and its value from node
            }
        }
        System.out.println();
    }

    /*--------To get value of specific key in the HashMap------ */
    public V get(K key) {
        int bucketIdx = this.getBucketIdx(key); // get bucket index against key
        int keyIdx = searchInLinkedLL(key, bucketIdx);

        if (keyIdx == -1) { // get bucket index against key
            return null;
        } else {
            return buckets[bucketIdx].get(keyIdx).value;
        }
    }

    /*--------To remove specific key-value pair in the HashMap------ */
    // Method 1 : If only key is known, removed value against that key will be
    // returned
    public V remove(K key) {
        int bucketIdx = getBucketIdx(key);
        int keyIdx = searchInLinkedLL(key, bucketIdx);
        if (keyIdx == -1) {
            return null;
        } else {
            HmNode<K, V> node = buckets[bucketIdx].remove(keyIdx); // removed node will be returned
            n--;
            return node.value; // return value from removed node
        }
    }

    // Method 2 : If key, value both are known, retun true if removed successfully,
    // otherwise false
    public boolean remove(K key, V value) {
        int bucketIdx = getBucketIdx(key);
        int keyIdx = searchInLinkedLL(key, bucketIdx);
        if (keyIdx == -1) {
            return false; // key not found
        } else {
            buckets[bucketIdx].remove(keyIdx); // remove at that key index node
            n--;
            return true; // return true, successfull deleted
        }
    }

    /*--------To get size of HashMap------ */
    public int size() {
        return n; // number of nodes / key-value pairs
    }

    /*--------To check whether specific key exists in HashMap------ */
    public boolean containsKey(K key) {
        int bucketIdx = getBucketIdx(key);
        int keyIdx = searchInLinkedLL(key, bucketIdx);
        return keyIdx != -1;
    }

    /*--------To check whether specific value exists in HashMap------ */
    public boolean containsValue(V value) {
        for (LinkedList<HmNode<K, V>> bucket : buckets) {
            for (HmNode<K, V> node : bucket) {
                if (node.value.equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * entrySet()
     * - returns all entries of HashMap in the form of set
     * - in that set, each node has key and value
     * - cannot directly print this set, need to iterate over it
     * 
     */
    public HashSet<HmEntry<K, V>> entrySet() {
        HashSet<HmEntry<K, V>> set = new HashSet<>();
        for (LinkedList<HmNode<K, V>> list : buckets) {
            for (HmNode<K, V> node : list) {
                set.add(new HmEntry<>(node));
            }
        }
        return set;
    }

    /*
     * keySet()
     * - returns all keys of HashMap in the form of set
     * - elements of this set represent keys of Map
     * - that keys can help to get values by using get(key) method
     */
    public HashSet<K> keySet() {
        HashSet<K> set = new HashSet<>();
        for (LinkedList<HmNode<K, V>> bucket : buckets) {
            for (HmNode<K, V> node : bucket) {
                set.add(node.key);
            }
        }
        return set;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println("\n");
        HHashMap<String, Integer> map = new HHashMap<>();

        /*-------------------put() Function Testing------------------- */
        map.put("Harry", 10);
        map.put("Rohn", 20);
        map.put("Harmoini", 25);
        map.put("Harmoini", 30); // update value only
        map.display(); // display test
        System.out.println("Number of buckets: " + map.N);
        System.out.println("Number of Nodes: " + map.n);
        System.out.println("\n");
        map.put("Draco", 40);
        map.put("Jinny", 50);
        map.put("Severous", 45);
        map.put("Snape", 45);
        map.display();
        System.out.println("Number of buckets: " + map.N);
        System.out.println("Number of Nodes: " + map.n);
        System.out.println("\n");

        /*-------------------Rehashing Test------------------- */
        map.put("Dumbledore", 70);
        map.put("Witch", 80);
        map.put("Voldemort", 15);
        // map.put("New", 10000);
        // map.put("New 2", 32328);
        map.display();
        System.out.println("Number of buckets: " + map.N);
        System.out.println("Number of Nodes: " + map.n);
        System.out.println("\n");

        /*-------------------get() Function Testing------------------- */
        System.out.println("Value of Harry: " + map.get("Harry"));
        System.out.println("Value of HarrY: " + map.get("HarrY"));

        /*-------------------containsKey() Function Testing------------------- */
        if (map.containsKey("Harry")) {
            System.out.println("Harry exists");
        } else {
            System.out.println("Harry Doesn't exist");
        }

        if (map.containsKey("HarrY")) {
            System.out.println("HarrY exists");
        } else {
            System.out.println("HarrY Doesn't exist");
        }

        /*-------------------entrySet() Function Testing------------------- */

        HashSet<HmEntry<String, Integer>> set;
        set = map.entrySet();
        System.out.println("\n---Key : Value------------\n");
        // Method 1 to iterate over set
        Iterator<HmEntry<String, Integer>> itr = set.iterator();
        while (itr.hasNext()) {
            HmEntry<String, Integer> entry = itr.next();
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        // Method - 2 to iterate over set
        // for (HmEntry<String, Integer> entry : set) {
        // System.out.print(entry.getKey() + " : ");
        // System.out.println(entry.getValue());
        // }

        /*-------------------keySet() Function Testing------------------- */
        System.out.println("\n---Key : Value------------\n");
        HashSet<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key + " : " + map.get(key));
        }
        System.out.println();
        System.out.println("Current Size: " + map.size());

        /*-------------------remove(key) Function Testing------------------- */
        System.out.println("Removed " + map.remove("Dumbledore"));
        System.out.println("Updated Size: " + map.size());

        /*-------------------remove(key, value) Function Testing------------------- */
        if (map.remove("Harry", 10)) {
            System.out.println("Delete Successfull !");
        } else {
            System.out.println("Key not found ");
        }
        System.out.println("Updated Size: " + map.size());

        map.put("Harry", 10);
        System.out.println("Updated Size: " + map.size());

    }
}
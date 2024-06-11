/* HashMapTest calss to test different methods of java.util.HashMap class
 * - Add Entry(key, value) in HashMap
 * - Remove Entry(key, value) from HashMap
 * - Size of HashMap
 * - Iteration in HashMap by java.util.Map.Entry and java.util.Set classes
 * - Searching key / value in HashMap
 * - Printing HashMap using System.out.println()
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class HHashMap {
    public static void main(String[] args) {
        System.out.println("\n");
        // map --> student : marks

        /*---------Initialization syntax-------*/
        // HashMap<keyType, valueType> name = new HashMap<>();

        HashMap<String, Integer> map = new HashMap<>();

        /*---------Inserting values in map-------
         * If key already exists => its value is updated
         * If key doesn't exist already => new key value pair is added
        */
        map.put("Harry", 20);
        map.put("Rohn", 25);
        map.put("Hermoini", 30);
        map.put("jinny", 25);
        System.out.println("Initial Map: " + map);
        map.put("Harry", 30); // update
        System.out.println("Updated Map: " + map);

        /*---------Searching key/value in map-------
         * containsKey(keyName) --> returns true / false
         * containsValue(value) --> returns true / false
        */
        if (map.containsKey("Harry")) {
            System.out.println("Harry Exists :) ");
        } else {
            System.out.println("Harry not Exists :) ");
        }

        /*---------Get value of key in map-------*/
        System.out.println("Value of Harry: " + map.get("Harry")); // returns null if key doesn't exist in map

        /*---------Iteration in Map-------
         * for (<dataType> value : IterableCollection) --> for each loop
         * Method 1 - entrySet() --> returns Entries of map (key value pair)
         * Method 2 - keySet() --> returns only set of keys
        */

        // Iteration Method 1
        System.out.print("\nAll Entries in map: ");
        Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            System.out.print(entry + "; ");
        }
        System.out.println();

        System.out.println("\n-----Method 1-----");
        System.out.println("\n[Key] : [Value]\n");
        for (Map.Entry<String, Integer> itr : map.entrySet()) {
            // itr has both key and value pair
            System.out.print(itr.getKey() + " : ");
            System.out.println(itr.getValue());
        }
        System.out.println("\n");

        // Iteration Method 2
        System.out.println("-----Method 2-----");
        System.out.println("\n[Key] : [Value]\n");
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            System.out.println(key + " : " + map.get(key));
        }

        /*---------Size of Map-------*/
        System.out.println("\nSize of Map: " + map.size());

        /*---------Remove a key-value pair of Map-------*/
        map.remove("Jinny");
        map.remove("jinny"); // no effect if key doesn't exist
        System.out.println("Updated Size: " + map.size());

        System.out.println("\n");
    }
}
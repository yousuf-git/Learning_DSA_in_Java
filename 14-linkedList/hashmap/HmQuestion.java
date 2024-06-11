/* Includes practice questions related to hash map and hash set
 */
package hashmap;

import java.util.HashSet;

public class HmQuestion {
    public static void majorityElement(int[] nums) {
        // I'm using my own implemented HashMap :)
        HHashMap<Integer, Integer> map = new HHashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) { // if element exist already
                int f = map.get(num); // get previous frequency
                map.put(num, ++f); // update frequency by 1
            } else {
                map.put(num, 1); // add new pair with frequency 1
            }
        }
        for (int key : map.keySet()) { // get all keys
            if (map.get(key) > nums.length / 3) { // check if frequency > length/3
                System.out.print(key + " ");
            }
        }
        System.out.println();
    }

    // Solution 2 - size of union of 2 arrays
    public static int unionSize(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : arr1) {
            set.add(i);
        }
        for (int i : arr2) {
            set.add(i);
        }
        return set.size();
    }

    // Solution 3 - intersection of 2 arrays
    public static void intersection(int[] arr1, int[] arr2) {
        HashSet<Integer> set = new HashSet<>();
        for (Integer num : arr1) {
            set.add(num);
        }
        for (Integer num : arr2) {
            if (set.contains(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }

    // Solution 4 - Itenerary from tickets paths
    public static void printItinerary(HHashMap<String, String> paths) {
        // find start
        String start = "";
        for (String key : paths.keySet()) {
            if (!paths.containsValue(key)) {
                start = key;
                break;
            }
        }
        System.out.print(start); // print start
        while (paths.get(start) != null) { // untill start has destination
            System.out.print(" --> " + paths.get(start)); // print destination
            start = paths.get(start); // make the destination as start
        }
        System.out.println();
    }

    // Solution 5 - Sub Array sum = k
    public static int countSubArray(int[] array, int k) {
        HHashMap<Integer, Integer> map = new HHashMap<>();
        // subSum : freq
        map.put(0, 1); // for empty sub array
        int arrCount = 0;
        int sum = 0;
        for (int i : array) {
            sum += i;
            if (map.containsKey(sum - k)) { // if sum - k exists
                arrCount += map.get(sum-k); // add its frequency
            }
            if (map.containsKey(sum)) { // is sum exists
                map.put(sum, map.get(sum) + 1); // update frequency
            } else {
                map.put(sum, 1); // add with a new f = 1
            }
        }
        return arrCount;
    }


    public static void main(String[] args) {
        /*
         * Q1.
         * Given an integer array of size n
         * Find elements that appear more than n/3 times
         */
        int nums[] = { 1, 1, 2, 2, 2, 1, 1, 2, 7, 2, 1, 7 }; // n = 12 => n/3 = 4
        // int nums[] = { 6, 9}; // n = 2 => 2/3 = 0
        // f(6) = 1; f(9) = 1; => Answer: 6, 9
        /*
         * Solution 1(a)
         * If we solve by simple loop
         * We need 2 loops => O(n^2) time complexity
         */
        // HashSet<Integer> set = new HashSet<>();
        // int freq;
        // for (int val : nums) {
        // freq = 0;
        // for (int i : nums) {
        // if (i == val) {
        // freq++;
        // }
        // }
        // if (freq > nums.length / 3) {
        // set.add(val);
        // }
        // }
        // System.out.println(set);
        /*
         * Solution 1(b) By HashMap
         * Store num : frequency in a HashMap
         * We need 1 loop => O(n) time complexity
         */
        majorityElement(nums);
        /*------------------------------------------------------ */
        /*
         * Q2. Get size of Union of 2 arrays
         */
        int[] arr1 = { 1, 2, 3, 4, 5 };
        int[] arr2 = { 3, 4, 6, 9 };
        // size of union = 7 because 2 elements are repeated and ignored
        System.out.println("Size of Union: " + unionSize(arr1, arr2));
        /*------------------------------------------------------ */
        /*
         * Q3: Intersection of 2 arrays
         */
        System.out.print("Intersection : ");
        intersection(arr1, arr2);

        /*------------------------------------------------------ */
        /*
         * Q4: Itenary from tickets
         * Given some ticket paths that tell its path goes from where to where
         * source : destination
         * we have to calculate overall path that will be followed if all tickets are
         * used
         * 
         * Example:
         * 
         * ---------Given ticket paths as HashMap---------
         * [key] --> [value]
         * Multan --> Lahore
         * Murree --> Karachi
         * Islamabad --> Multan
         * Karachi --> Islamabad
         * 
         * Some key info:
         * - No 2 destinations from 1 source
         * - There is no loop in path
         * 
         * ------------Overall Path------------
         * Murree --> Karachi --> Islamabad --> Multan --> Lahore
         * 
         * ---------------Solution----------
         * [Main Hint] : Find the sarting point i.e, that is in keys but not in value
         */
        HHashMap<String, String> paths = new HHashMap<>();
        paths.put("Multan", "Lahore");
        paths.put("Murree", "Karachi");
        paths.put("Islamabad", "Multan");
        paths.put("Karachi", "Islamabad");
        System.out.println("\n----------Itinearary is below----------\n");
        printItinerary(paths);

        /*------------------------------------------------------ */
        /*
         * Q5: Sub array sum = k
         * Example:
         * Given array {2,2,3,5} and k = 7
         * Sub Arrays = {}, {2}. {4}, {3}, {5}, {2,2}, {2,3}, {2,5}*, {3,5}, {2,2,3}*,
         * {2,2,5}, {2,3,5}
         * {2,5} => 2+5 = 7; {2,2,3} => 2+2+3 = 7; these 2 subrrays' sum = 7
         * => Answer = 2
         */

        /*-----Solution Approach----- 
         * Prefix Sum
         * 
         * Suppose Array:
         * ______________________
         * | 6 | 3 | -1 | 5 | 2 |
         * ----------------------
         *  
         * Prefix Array: => index i contains sum from 0 to index i in array
         * _______________________
         * | 6 | 9 | 8 | 13 | 15 |  
         * -----------------------
         * 
         * Sum from i to j is given by:
         * Sum = prefix(j) - prefix(i-1)
         * 
         * Suppose I need sum from i = 1 to j = 3 in array
         * i.e, 3 + (-1) + 5 = 7
         * 
         * => prefix(j) = prefix(3) = 13
         * => prefix(i-1) = prefix(1-1) = prefix(0) = 6
         * 
         * => Sum = 13 - 6 = 7
         * 
         * -----------Now using the HashMap approach by following prefix-----
         * we know that : subSum(i, j) = subSum(j) - subSum(i-j) 
         *  where subSum(j) means sum of all elements till j
         *                  k = subSum(j) - subSum(i-1)
         *                  7 = subSum(j) - subSum(i-1)
         * Now if we fix any of subSum(j) or subSum(i-1) the other one will be fixed
         *  like if 4 = a + b, and we fix a = 1 then b = 3 is also fixed
         * 
         *                   k = subSum(j) - subSum(i-1)
         *              =>   subSum(j) - k =  subSum(i-1)
         *  K is already known, we need to calculate subSum(j)
         * if subSum(j) - k already exists i.e, subSum(i-1)
         *  it means the sum of particular sub array is equal to k
         * 
         * -----------Algorithm--------------
         * Step 1:
         * - Create a HashMap<Integer, Integer> => subSum : frequency
         * - Put <0,1> initially for empty subarray
         * 
         * Step 2:
         * - If subSum - k already exists in map:
         *      => increment in result count
         * 
         * - If subSum already exists:
         *      => update its frequency
         *      => otherwise add a new pair subSum : 1 (freq)
         * 
         * 
         * 
        */
        int[] array = { 10, -2, 2, -20, 10 };
        int k = -10;
        /*
         * Total 3 sub arrays are there such that there sum = -10
         * 1. {-2, 2, -20, 10}
         * 2. {10, -2, 2, -20}
         * 3. {-20, 10}
         */

        System.out.println("Total Sub Arrays: " + countSubArray(array, k));

    }
}

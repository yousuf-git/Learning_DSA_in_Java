package doublyLL;

public class DoublyLLTesting {
    public static void main(String[] args) {
        HDoublyLL<String> list = new HDoublyLL<>();
        list.add("to");
        list.add("world");
        list.addLast("of");
        list.addLast("DSA");
        list.display();
        System.out.println(list.getSize());
        list.addFirst("WELCOME");
        list.display();
        System.out.println(list.getSize());
        list.delFirst();
        list.display();
        System.out.println(list.getSize());
        list.delLast();
        list.display();
        System.out.println(list.getSize());
        list.addAtIdx(0, "WELCOME");
        list.display();
        System.out.println(list.getSize());
        list.addAtIdx(3, "DSA");
        list.display();
        list.delFromIdx(3);
        list.display();
        // System.out.println(" ⟺");
        list.set(3, "harry");
        // list.newDisplay();
        list.display();

        System.out.println("Value at index 3: " + list.get(3));

        list.reverse();
        list.add("new");
        list.add("node");
        list.display();

        System.out.println( "Middle: " + list.middle(list.getHead()).data);

        
    }
    
}

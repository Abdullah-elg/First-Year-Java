public class PowerSet<T> {
    // initializes the list of linked lists
    private Set<T>[] set;

    // creates all possible combiniations of linked lists with given nodes and adds them to a list
    public PowerSet(T[] elements) {
        int num = (int) Math.pow(2, elements.length);
        int divide = (int) Math.floor(Math.sqrt(num));
        set = new Set[num];
        for(int i = 0; i < num; i++) { // gets all possible combinations of the linked list with given elements
            String binary = Integer.toBinaryString(i);
            while(binary.length() < divide) {
                binary = "0" + binary;
            }
            Set<T> items = new Set<T>();
            for(int j = 0; j < binary.length(); j++) { // turns the possible combinations into a linked list
                if(binary.substring(j, j+1).equals("1")) {
                    items.add(elements[j]);
                }
            }
            set[i] = items;
        }
    }

    // gets the length of the list of linked lists and returns that length
    public int getLength() {
        return set.length;
    }

    // gets the linked list at the specified variable in the list and returns that linked list
    public Set<T> getSet(int i) {
        return set[i];
    }
}

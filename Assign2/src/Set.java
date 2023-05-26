public class Set<T> {
    // initializes the linked list
    private LinearNode<T> setStart;

    // sets the class by assigning the linked list a null node
    public Set() {
        setStart = null;
    }

    // adds a node to the linked list and preserves the old nodes
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element);
        newNode.setNext(setStart);
        setStart = newNode;
    }

    // gets the length of the linked list and returns the length
    public int getLength() {
        int count = 0;
        LinearNode<T> next = setStart;
        while(next != null) {
            next = next.getNext();
            count ++;
        }
        return count;
    }

    // gets the node at the specified index (if it exists) from the linked list and returns it
    public T getElement(int i) {
        LinearNode<T> next = setStart;
        for(int j = 0; j < i; j++) {
            next = next.getNext();
        }
        return next.getElement();
    }

    // checks throught all the nodes of the linked list and finds if it contains the specified element and returns true or false based on if it found the element or not
    public boolean contains(T element) {
        LinearNode<T> next = setStart;
        while(next != null) {
            if(next.getElement() == element) {
                return true;
            } else {
                next = next.getNext();
            }
        }
        return false;
    }

    // turns the linked list into a string that returns all the elements of said linked list
    public String toString() {
        String s = "";
        LinearNode<T> next = setStart;
        while(next != null) {
            s += next.getElement() + " ";
            next = next.getNext();
        }
        return s;
    }
}

/**
 * Class to organize the nodes by priority and put them into a queue
 * @author Abdullah Elganainy
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T> {
    // private instance variables referencing what is contained in the queue
    private DLinkedNode<T> front;
    private DLinkedNode<T> rear;
    private int count;

    /**
     * sets all the instance variables to null and 0 and initializes the object
     */
    public DLPriorityQueue() {
        front = null;
        rear = null;
        count = 0;
    }

    /**
     * adds a node to the queue based on its priority with lowest priority being the front of the queue
     * @param dataItem
     * @param priority
     */
    public void add(T dataItem, double priority) {
        DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority); // creates a newNode with the data item and priority from the parameters
        if(this.isEmpty()) { // if the queue is empty then set the front and rear to the newNode
            front = newNode;
            rear = newNode;
        } else {
            if(newNode.getPriority() <= front.getPriority()) { // if the priority of the newNode is smaller or equal to the front nodes priority then set the newNode to the front 
                front.setPrev(newNode);
                newNode.setNext(front);
                front = newNode;
            } else if(newNode.getPriority() > rear.getPriority()) { // if the priority of the newNode is greater than the rear nodes priority then set the newNode to the rear 
                rear.setNext(newNode);
                newNode.setPrev(rear);
                rear = newNode;
            } else {
                DLinkedNode<T> curr = front; // creates a current node that will be used to loop through the queue
                while(newNode.getPriority() > curr.getPriority()) { // loop through the queue until the newNode is not greater than the current node
                    curr = curr.getNext();
                }
                // inserts the newNode before the current node
                newNode.setNext(curr);
                newNode.setPrev(curr.getPrev());
                curr.getPrev().setNext(newNode);
                curr.setPrev(newNode);
            }
        }
        count++; // adds 1 to the count(length of the queue)
    }

    /**
     * Updates the priority of the node with the given dataItem and sorts it in the queue with its new priority
     * @param dataItem
     * @param newPriority
     * @throws InvalidElementException
     */
    public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
        DLinkedNode<T> curr = front; // creates a current node that will be used to loop through the queue
        while(curr != null && !curr.getDataItem().equals(dataItem)) { // loop through the queue until the the current nodes data item equals to the data item given in the parametere
            curr = curr.getNext();
        }
        if(curr == null) { // if the data item could not be found in the queue then throws an exception that the data item is not in the queue
            throw new InvalidElementException("dataItem not in priority queue");
        }
        curr.setPriority(newPriority); // sets the priority of teh current node to the new priority
        if(count > 1) {
            if(curr == front) { // if the current node is the front of the queue then set the next node to the front of the queue and removes the current node
                front = curr.getNext();
                front.setPrev(null);
            } else if(curr == rear) { // if the current node is the rear of the queue then set the previous node to the rear of the queue and removes the current node
                rear = curr.getPrev();
                rear.setNext(null);
            } else { // otherwise make the surrounding nodes point to eachother and remove the current node
                curr.getPrev().setNext(curr.getNext());
                curr.getNext().setPrev(curr.getPrev());
            }
            count--; //  minus 1 from the count because the node was removed from the queue
            add(dataItem, newPriority); // calls the add method with the data item and new priority to add the nod back in and sort it
        }
    }

    /**
     * removes the front of the queue and returns it
     * @throws EmptyPriorityQueueException
     * @return dataItem of the node with the least priority
     */
    public T removeMin() throws EmptyPriorityQueueException {
        if(this.isEmpty()) { // if the queue is empty then throws an exception that indicates an empty queue
            throw new EmptyPriorityQueueException("The queue is empty");
        }
        T min = front.getDataItem();
        if(count > 1) { // if the queue has more than one node then it removes the front node from the queue
            front = front.getNext();
            front.setPrev(null);
        } else { // otherwise(queue has one node) set the front and rear to null removing the node
            front = null;
            rear = null;
        }
        count--; // minus 1 from the count becaise a node was removed
        return min;
    }

    /**
     * checks if the queue is empty
     * @return boolean true or false
     */
    public boolean isEmpty() {
        if(count == 0) { // if the count is 0 then the queue is empty and returns true
            return true;
        }
        return false;
    }

    /**
     * returns the size(how many items in the queue) of the queue
     * @return count
     */
    public int size() {
        return count;
    }

    /**
     * concatenates all the items in the queue and makes the a String representation
     * @return String representation of the queue
     */
    public String toString() {
        String str = "";
        DLinkedNode<T> curr = front; // creates a current node that will be used to loop through the queue
        while(curr != null) { // loop through all the items in the queue and concatenate them together
            str += curr.getDataItem();
            curr = curr.getNext();
        }
        return str;
    }

    /**
     * returns the rear of the queue
     * @return rear node
     */
    public DLinkedNode<T> getRear() {
        return rear;
    }
}
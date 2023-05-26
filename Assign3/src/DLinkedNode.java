/**
 * Class to create a node and to set the nodes parameters or change them
 * @author Abdullah Elganainy
 */
public class DLinkedNode<T> {
    // private instance variables referencing what is contained in the node
    private T dataItem;
    private double priority;
    private DLinkedNode<T> next;
    private DLinkedNode<T> prev;

    /**
     * Sets the data item and priority to the node
     * @param data
     * @param prio
     */
    public DLinkedNode(T data, double prio) {
        next = null;
        prev = null;
        this.dataItem = data;
        this.priority = prio;
    }

    /**
     * Sets the data item and priority with null and 0 parameters to the node
     */
    public DLinkedNode() {
        this(null, 0);
    }

    /**
     * gets and returns the priority of the node
     * @return priority of the node
     */
    public double getPriority() {
        return priority;
    }

    /**
     * gets and returns the data item of the node
     * @return dataItem of the node
     */
    public T getDataItem() {
        return dataItem;
    }

    /**
     * gets and returns the node that is after the current one
     * @return next node
     */
    public DLinkedNode<T> getNext() {
        return next;
    }

    /**
     * gets and returns the node that is before the current one
     * @return prev node
     */
    public DLinkedNode<T> getPrev() {
        return prev;
    }

    /**
     * sets the dataItem of the node to what is in the parameter
     * @param data
     */
    public void setDataItem(T data) {
        this.dataItem = data;
    }

    /**
     * sets the next node to the node in the parameter
     * @param next
     */
    public void setNext(DLinkedNode<T> next) {
        this.next = next;
    }

    /**
     * Sets the previous node to the node in the parameter
     * @param prev
     */
    public void setPrev(DLinkedNode<T> prev) {
        this.prev = prev;
    }

    /**
     * sets the priority of the node to what is in the parameter
     * @param prio
     */
    public void setPriority(double prio) {
        this.priority = prio;
    }
}
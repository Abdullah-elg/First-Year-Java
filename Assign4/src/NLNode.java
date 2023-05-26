import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> {
    // declares the variables of the node
    private NLNode<T> parent;
    private ListNodes<NLNode<T>> children;
    private T data;

    /**
     * Creates a new NL node with no parameters by initializing the instance variables to null
     */
    public NLNode() {
        parent = null;
        data = null;
        children = new ListNodes<NLNode<T>>();
    }

    /**
     * Creates a new NL node with parameters by initializing the instance variables to their respective values/ parameters
     * @param d
     * @param p
     */
    public NLNode(T d, NLNode<T> p) {
        parent = p;
        data = d;
        children = new ListNodes<NLNode<T>>();
    }

    /**
     * Sets the parent of the node to the specified parameter
     * @param p
     */
    public void setParent(NLNode<T> p) {
        parent = p;
    }

    /**
     * Gets the parent of the node
     * @return the parent of the node
     */
    public NLNode<T> getParent() {
        return parent;
    }

    /**
     * Adds a child to the exisiting node by setting the child parent to this node and adding the child as a child to the parent node
     * @param newChild
     */
    public void addChild(NLNode<T> newChild) {
        newChild.setParent(this);
        children.add(newChild);
    }

    /**
     * gets the children of the parent node
     * @return the children of the node in string format
     */
    public Iterator<NLNode<T>> getChildren() {
        return children.getList();
    }

    /**
     * Gets the children of the parent node in order rof the sort that is provided by the parameter
     * @param sorter
     * @return the sorted list of children
     */
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        return children.sortedList(sorter);
    }

    /**
     * gets the data of the node
     * @return the data of the node
     */
    public T getData() {
        return data;
    }

    /**
     * sets the data to the value provided by the parameter
     * @param d
     */
    public void setData(T d) {
        data = d;
    }
}

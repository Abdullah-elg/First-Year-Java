public class Vector extends Matrix {
    /**
     * Constructs a new Vector with the specified column
     * 
     * @param column
     */
    public Vector(int column) {
        super(1, column);
    }

    /**
     * Constructs a new Vector with the specified column and Array
     * 
     * @param column
     * @param linArr
     */
    public Vector(int column, double[] linArr) {
        super(1, column, linArr);
    }

    /**
     * gets an element based on the given parameters
     * 
     * @param column
     * @return the element at the specified column with row index 0 because its a vector not a matrix
     */
    public double getElement(int column) {
        return getElement(0, column);
    }
}

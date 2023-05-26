public class Matrix {
    // initializes the variables for the matrix
    private int numRows;
    private int numCols;
    private double[][] data;

    /**
     * Constructs a new Matrix with the specified number of rows and columns
     * 
     * @param numRows
     * @param numCols
     */
    public Matrix(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        data = new double[numRows][numCols];
    }

    /**
     * Constructs a new Matrix with the specified number of rows and columns and Array
     * 
     * @param numRows
     * @param numCols
     * @param linArr
     */
    public Matrix(int numRows, int numCols, double[] linArr) {
        this.numRows = numRows;
        this.numCols = numCols;
        data = new double[numRows][numCols];
        int x = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = linArr[x];
                if (x >= linArr.length) {
                    x = 0;
                } else {
                    x++;
                }
            }
        }
    }

    /**
     * gets the number of rows and returns it
     * 
     * @return the number of rows in the matrix
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * gets the number of columns and returns it
     * 
     * @return the number of columns in the matrix
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * gets the matrix in list form and returns it
     * 
     * @return the matrix in list format
     */
    public double[][] getData() {
        return data;
    }

    /**
     * gets an element based on the given the parameters
     * 
     * @param row
     * @param col
     * @return the element that is at the declared row and column
     */
    public double getElement(int row, int col) {
        return data[row][col];
    }

    /**
     * sets an element based on the given the parameters and given value
     * 
     * @param row
     * @param col
     * @param value
     */
    public void setElement(int row, int col, double value) {
        data[row][col] = value;
    }

    /**
     * transposes a matrix meaning swapping the rows with the columns and changing the corresponding elements
     * 
     */
    public void transpose() {
        Matrix trans = new Matrix(numCols, numRows);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                trans.setElement(j, i, data[i][j]);
            }
        }
        this.data = trans.getData();
        this.numRows = trans.getNumRows();
        this.numCols = trans.getNumCols();
    }

    /**
     * multiplies a matrix by a scalar which is declared in the parameter
     * 
     * @param scalar
     * @return a matrix that is multiplied by the parameter
     */
    public Matrix multiply (double scalar) {
        Matrix multiple = new Matrix(numRows, numCols);
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                multiple.data[i][j] = data[i][j] * scalar;
            }
        }
        return multiple;
    }

    /**
     * multiplies a matrix by a another matrix which is declared in the parameter
     * 
     * @param other
     * @return a matrix that is multiplied by another matrix specfied by the parameter
     */
    public Matrix multiply (Matrix other) {
        if (other.getNumRows() != this.getNumCols()) {
            return null;
        }
        Matrix mix = new Matrix(this.numRows, other.getNumCols());

        for (int i = 0; i < this.numRows; i++) {
            for (int j = 0; j < other.getNumCols(); j++) {
                for (int k = 0; k < other.getNumCols(); k++) {
                    mix.data[i][j] += this.data[i][k] * other.data[k][j];
                }
            }
        }
        return mix;
    }

    /**
     * makes a string representation of a matrix with maximum of 8 characters and 3 decimal digits
     * 
     * @return the matrix in string format where it can be viewed
     */
    public String toString() {
        if (data.length == 0) {
            return "Empty matrix";
        }
        String s = new String();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                s += String.format("%8.3f", data[i][j]);
            }
            s += "\n";
        }
        return s;
    }
}

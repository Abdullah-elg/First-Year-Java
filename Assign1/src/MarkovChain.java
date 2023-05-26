public class MarkovChain {
    // Initializes the variables for the chain
    private Vector stateVector;
    private Matrix transitionMatrix;

    /**
     * Constructs a new MarkovChain with the specified state vector and transition matrix
     * 
     * @param stateVector
     * @param transitionMatrix
     */
    public MarkovChain(Vector stateVector, Matrix transitionMatrix) {
        this.stateVector = stateVector;
        this.transitionMatrix = transitionMatrix;
    }

    /**
     * checks wether the transition matrix can be multiplied by itself and the state vector and returns true or false depending on if it can be multiplied or not
     * 
     * @return a boolean indicating whether the chain is valid
     */
    public boolean isValid() {
        if ((transitionMatrix.getNumRows() == transitionMatrix.getNumCols()) && (transitionMatrix.getNumCols() == stateVector.getNumCols())) {
            for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
                double total = 0.0;
                for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
                    total += transitionMatrix.getElement(i, j);
                }
                if (0.99 > total || total > 1.01) {
                    return false;
                }
            }
            double sum = 0.0;
            for (int x = 0; x < stateVector.getNumCols(); x++) {
                sum += stateVector.getElement(x);
            }
            if (0.99 > sum || sum > 1.01) {
                return false;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if the vector and matrix are valid for multiplication and multiplues the matrix by itself and then multiplies the vector by the multiplied matrix
     * 
     * @param numSteps
     * @return the total of the matrices multiplied by eachother
     */
    public Matrix computeProbabilityMatrix(int numSteps) {
        if (isValid() == false || numSteps == 0) {
            return null;
        }
        Matrix multiple = transitionMatrix;
        for (int i = 0; i < numSteps-1; i++) {
            multiple = multiple.multiply(transitionMatrix);
        }
        return stateVector.multiply(multiple);
    }
}

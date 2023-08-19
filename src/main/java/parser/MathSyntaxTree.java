package parser;

public class MathSyntaxTree {


    private Node topTree = new Node();


    public Node getTopTree() {
        return topTree;
    }

    public void setTopTree(Node topTree) {
        this.topTree = topTree;
    }

    public static class Node {
        private Node parent;
        private Node leftChild;
        private Node rightChild;


        private boolean isOperation = false;
        private String operator = "";
        private double number = 0;

        public Node() {
            this.parent = null;
            this.leftChild = null;
            this.rightChild = null;
        }

        public String getOperator() {
            return operator;
        }

        public void setOperator(String operator) {
            this.operator = operator;
        }

        public double getNumber() {
            return number;
        }

        public void setNumber(double number) {
            this.number = number;
        }


        public boolean isOperation() {
            return isOperation;
        }

        public void setOperation(boolean operation) {
            isOperation = operation;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }


    }
}

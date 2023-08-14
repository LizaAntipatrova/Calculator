package parser;

public class MathSyntaxTree {

    Node topTree;

    public MathSyntaxTree() {
        this.topTree = new Node(null, null, null, false);
    }

    public static class Node {
        private Node parent;
        private Node leftChild;
        private Node rightChild;


        private boolean isOperation;
        private String value;

        public Node(Node parent, Node leftChild, Node rightChild, boolean isOperation) {
            this.parent = parent;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
            this.isOperation = isOperation;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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

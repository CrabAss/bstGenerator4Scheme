import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

class binarySearchTree {
    private int index;
    private binarySearchTree leftNode;
    private binarySearchTree rightNode;

    public binarySearchTree(int index) {
        this.index = index;
    }

    public binarySearchTree(int[] indexes) {
        this.index = indexes[0];
        for (int i = 1; i < indexes.length; i++) {
            insertNode(indexes[i]);
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void insertNode(int index) {
        if (index == this.index)
            throw new InvalidParameterException("This index already exists in this binary search tree.");
        if (index < this.index) {
            if (leftNode == null) leftNode = new binarySearchTree(index);
            else leftNode.insertNode(index);
        } else {
            if (rightNode == null) rightNode = new binarySearchTree(index);
            else rightNode.insertNode(index);
        }
    }

    /**
     * @return Binary search tree declaration statement in Scheme syntax
     *         Sample: ( DEFINE T '( 50 ( 36 ( 23 () ) ) 81 ( 72 () ) 94 () ) )
     */
    public String toString() {
        return String.format("( DEFINE T '(%s) )", binarySearchTree.toString(this).replaceAll(" +", " "));
    }

    private static String toString(binarySearchTree node) {
        if (node == null) return "";
        StringBuilder result = new StringBuilder(" ");
        result.append(node.getIndex());
        result.append(" (");
        result.append(binarySearchTree.toString(node.leftNode));
        result.append(") ");
        if (node.rightNode != null) {
            result.append(binarySearchTree.toString(node.rightNode));
            result.append(" ");
        }
        return result.toString();
    }
}

public class Main {

    private static int[] generateRandomArray() {
        final int LENGTH_MIN = 1, LENGTH_MAX = 20;
        final int VALUE_MIN = 1, VALUE_MAX = 100;

        Set<Integer> set = new HashSet<Integer>();
        int arrayLength = ThreadLocalRandom.current().nextInt(LENGTH_MIN, LENGTH_MAX + 1), i = 0;
        int[] result = new int[arrayLength];

        while (set.size() < arrayLength)
            set.add(ThreadLocalRandom.current().nextInt(VALUE_MIN, VALUE_MAX + 1));
        for (Integer num : set)
            result[i++] = num;
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        final int TESTCASE_SIZE = 20;
        for (int i = 0; i < TESTCASE_SIZE; i++) {
            System.out.println(new binarySearchTree(generateRandomArray()).toString() + "\n");
        }
    }
}

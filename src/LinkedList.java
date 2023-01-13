import java.util.ArrayList;
import java.util.List;

public class LinkedList {

    private List<LLNode> visited;

    public LinkedList() {
        this.visited = new ArrayList<>();
    }

    // Adds a node to the visited array which is used for the visualizer
    private void visit(LLNode node) {
        this.visited.add(node);
    }

    /**
     * TODO:
     * Given the head of a singly linked list where
     * class LLNode {
     * LLNode next
     * int val
     * }
     * Traverse the linked list iteratively
     *
     * @param node the LinkedList Node
     */
    public void iterativeTraversal(LLNode node) {
        while (node != null) {
            this.visit(node);
            node = node.getNext();
        }
    }

    /**
     * Given the head of a singly linked list where
     * class LLNode {
     * LLNode next
     * int val
     * }
     * Traverse the linked list recursively
     *
     * @param node the LinkedList Node
     */
    public void recursiveTraversal(LLNode node) {
        if (node == null) {
            return;
        }
        this.visit(node);
        recursiveTraversal(node.getNext());
    }

    /**
     * ____ _ _ _ _ _
     * | _ \ ___ _ __ ___ | |_ ___ __| (_) |_ | |
     * | | | |/ _ \ | '_ \ / _ \| __| / _ \/ _` | | __| | |
     * | |_| | (_) | | | | | (_) | |_ | __/ (_| | | |_ |_|
     * |____/ \___/ |_| |_|\___/ \__| \___|\__,_|_|\__| (_)
     * <p>
     * This is support code for the lab. You may look at it if you are interested,
     * but you will not have to change anything to complete your lab.
     */

    /**
     * Bad encapsulation but for the sake of the visualizer :)
     * 
     * @return the list of nodes in the order they were visited!
     */
    public List<LLNode> getVisited() {
        return this.visited;
    }
}
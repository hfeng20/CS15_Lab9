import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {

  private BSTNode root;
  private Pane pane;
  private List<BSTNode> visited;

  public BinaryTree(BSTNode root, Pane pane) {
    this.root = root;
    this.pane = pane;
    this.visited = new ArrayList<>();
  }

  // Adds a node to the visited array which is used for the visualizer
  private void visit(BSTNode node) {
    this.visited.add(node);
  }

  public void preOrderTraversal(BSTNode node) {
    if (node == null) {
      return;
    }
    this.visit(node);
    preOrderTraversal(node.getLeft());
    preOrderTraversal(node.getRight());
  }

  public void inOrderTraversal(BSTNode node) {
    if (node == null) {
      return;
    }
    preOrderTraversal(node.getLeft());
    this.visit(node);
    preOrderTraversal(node.getRight());
  }

  public void postOrderTraversal(BSTNode node) {
    if (node == null) {
      return;
    }
    preOrderTraversal(node.getLeft());
    preOrderTraversal(node.getRight());
    this.visit(node);
  }

  public boolean binarySearch(BSTNode node, double target) {
    if (node == null) {
      return false;
    }
    this.visit(node);
    if (node.getValue() == target) {
      return true;
    }
    if (node.getValue() < target) {
      return binarySearch(node.getRight(), target);
    }
    return binarySearch(node.getLeft(), target);
  }

  /**
   * ____ _ _ _ _ _
   * | _ \ ___ _ __ ___ | |_ ___ __| (_) |_ | |
   * | | | |/ _ \ | '_ \ / _ \| __| / _ \/ _` | | __| | |
   * | |_| | (_) | | | | | (_) | |_ | __/ (_| | | |_ |_|
   * |____/ \___/ |_| |_|\___/ \__| \___|\__,_|_|\__| (_)
   * This is support code for the lab. You may look at it if you are interested,
   * but you will not have to change anything to complete your lab.
   */

  public BSTNode insert(int newData) {
    // if tree is empty, make first node. No traversal necessary!
    if (this.root == null) {
      this.root = new BSTNode(newData, this.pane, 400, 200); // root’s parent is null
      Label label = new Label(newData + "");
      label.setLayoutY(200 - 3);
      label.setLayoutX(400 - 7);
      this.pane.getChildren().add(label);
      return this.root;
    } else {
      // delegate to Node’s insert() method
      return this.root.insert(newData, 400, 200, 0);
    }
  }

  public List<BSTNode> getVisited() {
    return this.visited;
  }

}
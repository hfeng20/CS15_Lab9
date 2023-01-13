import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 * ____ _ _ _ _ _
 * | _ \ ___ _ __ ___ | |_ ___ __| (_) |_ | |
 * | | | |/ _ \ | '_ \ / _ \| __| / _ \/ _` | | __| | |
 * | |_| | (_) | | | | | (_) | |_ | __/ (_| | | |_ |_|
 * |____/ \___/ |_| |_|\___/ \__| \___|\__,_|_|\__| (_)
 * This is support code for the lab. You may look at it if you are interested,
 * but you will not have to change anything to complete your lab.
 */

public class BSTNode {

  private int value;
  private BSTNode left;
  private BSTNode right;
  private Pane pane;
  private Circle node;

  public BSTNode(int value, Pane pane, int xLoc,
      int yLoc) { // construct a leaf node as default
    this.value = value;
    // child ptrs null for leaf nodes; set for internal nodes when child is created
    this.node = new Circle(xLoc, yLoc, Constants.NODE_RADIUS);
    node.setFill(Color.DEEPSKYBLUE);
    pane.getChildren().add(node);
    Label nodeLabel = new Label(value + "");
    nodeLabel.setLayoutX(xLoc - 3);
    nodeLabel.setLayoutY(yLoc - 7);
    pane.getChildren().add(nodeLabel);
    this.left = null;
    this.right = null;

    this.pane = pane;
  }

  public int getValue() {
    return this.value;
  }

  public BSTNode getRight() {
    return this.right;
  }

  public BSTNode getLeft() {
    return this.left;
  }

  public void setFill(Color color) {
    this.node.setFill(color);
  }

  public BSTNode insert(int newData, int x, int y, int level) { // insert method continued!
    int difference;
    if (level == 0) {
      difference = Constants.NODE_DIFFERENCE * 2;
    } else {
      if (level >= 3) {
        difference = Constants.NODE_DIFFERENCE / 2;

      } else {
        difference = Constants.NODE_DIFFERENCE;

      }
    }
    Line line;
    if (this.value > newData) { // newData should be in left subtree
      if (this.left == null) { // left child is null – we’ve found the place to insert!
        this.left = new BSTNode(newData, this.pane, x - difference, y + Constants.NODE_DIFFERENCE);
        line = new Line(x, y, x - difference, y + Constants.NODE_DIFFERENCE);
        pane.getChildren().add(line);
        line.toBack();
        return this.left;
      } else { // keep traversing down tree
        return this.left.insert(newData, x - (difference), y + Constants.NODE_DIFFERENCE,
            level + 1);
      }
    } else { // newData should be in right subtree
      if (this.right == null) { // right child is null–we’ve found the place to insert!
        this.right = new BSTNode(newData, this.pane, x + difference, y + Constants.NODE_DIFFERENCE);
        line = new Line(x, y, x + difference, y + Constants.NODE_DIFFERENCE);
        pane.getChildren().add(line);
        line.toBack();
        return this.right;
      } else { // keep traversing down tree
        return this.right.insert(newData, x + difference, y + Constants.NODE_DIFFERENCE, level + 1);
      }
    }
  }
}
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.List;

/**
 * ____ _ _ _ _ _
 * | _ \ ___ _ __ ___ | |_ ___ __| (_) |_ | |
 * | | | |/ _ \ | '_ \ / _ \| __| / _ \/ _` | | __| | |
 * | |_| | (_) | | | | | (_) | |_ | __/ (_| | | |_ |_|
 * |____/ \___/ |_| |_|\___/ \__| \___|\__,_|_|\__| (_)
 * This is support code for the lab. You may look at it if you are interested,
 * but you will not have to change anything to complete your lab.
 */

public class LLNode {

  private int val;
  private LLNode next;
  private Circle nodeCircle;
  Label valueLabel;

  public LLNode(int value, LLNode next, double x) {
    this.val = value;
    this.next = next;
    this.nodeCircle = new Circle(x, Constants.LL_CENTER_Y, Constants.NODE_RADIUS);
    this.nodeCircle.setFill(Color.SKYBLUE);
    this.valueLabel = new Label(this.getVal() + "");
    this.valueLabel.setLayoutX(x);
    this.valueLabel.setLayoutY(Constants.LL_CENTER_Y);
  }

  public void addToPane(Pane pane) {
    pane.getChildren().addAll(this.nodeCircle, this.valueLabel);
  }

  public void changeColor() {
    this.nodeCircle.setFill(Color.YELLOW);
  }

  public LLNode getNext() {
    return next;
  }

  public int getVal() {
    return this.val;
  }
}

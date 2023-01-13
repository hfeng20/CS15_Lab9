package lab9;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ____                      _              _ _ _     _
 * |  _ \  ___    _ __   ___ | |_    ___  __| (_) |_  | |
 * | | | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | |
 * | |_| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  |_|
 * |____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__| (_)
 * This is support code for the lab. You may look at it if you are interested,
 * but you will not have to change anything to complete your lab.
 */


public class Visualizer {

  enum Options {
    LL_ITERATIVE,
    LL_RECURSIVE,
    BST_PREORDER,
    BST_INORDER,
    BST_POSTORDER,
    BST_SEARCH
  }

  private Tab linkedListTab;
  private Tab binaryTreeTab;
  private LinkedList linkedList;
  private LLNode llHead;
  private Pane linkedListPane;
  private Pane binTreePane;
  private BinaryTree binTree;
  private BSTNode root;
  private List<Integer> bstNodeVals;


  public Visualizer() {
    this.linkedListTab = new Tab("Linked List");
    this.linkedListTab.setContent(this.createLinkedListContent());
    this.binaryTreeTab = new Tab("Binary Tree");
    this.binaryTreeTab.setContent(this.createBinaryTreeContent());
  }

  private Node createLinkedListContent() {
    BorderPane pane = new BorderPane();
    pane.setPrefSize(Constants.PANEL_W, Constants.PANEL_H);
    this.linkedListPane = new Pane();
    pane.setCenter(this.linkedListPane);
    pane.setBottom(this.createLinkedListControlPanel());
    return pane;
  }

  public Tab getLinkedListTab() {
    return this.linkedListTab;
  }

  public Tab getBinaryTreeTab() {
    return this.binaryTreeTab;
  }

  /*
   * This creates the control Pane with the buttons to control the
   * displayed squares
   */
  private Pane createLinkedListControlPanel() {
    HBox controlPane = new HBox(5);
    controlPane.setAlignment(Pos.CENTER);
    controlPane.setPadding(new Insets(5, 5, 5, 5));
    Button visualizeIterative = new Button("Iterative");
    visualizeIterative.setOnMouseClicked(e -> this.visualizeLLTraversal(Options.LL_ITERATIVE));

    Button visualizeRecursive = new Button("Recursive");
    visualizeRecursive.setOnMouseClicked(e -> this.visualizeLLTraversal(Options.LL_RECURSIVE));
    controlPane.getChildren().addAll(visualizeIterative, visualizeRecursive);

    this.createLinkedList();
    return controlPane;
  }

  private void createLinkedList() {
    this.linkedListPane.getChildren().clear();
    this.linkedList = new LinkedList();
    LLNode head = new LLNode(0, new LLNode(1, new LLNode(2, new LLNode(3,
        new LLNode(4, null, Constants.LL_X_OFFSET + (Constants.LL_X_GAP * 4)),
        Constants.LL_X_OFFSET + (Constants.LL_X_GAP * 3)), Constants.LL_X_OFFSET + (Constants.LL_X_GAP * 2)),
        Constants.LL_X_OFFSET + (Constants.LL_X_GAP * 1)), Constants.LL_X_OFFSET);
    LLNode curr = head;
    while (curr != null) {
      curr.addToPane(this.linkedListPane);
      curr = curr.getNext();
    }
    this.llHead = head;
  }

  private void visualizeLLTraversal(Options opt) {
    this.createLinkedList();
    switch (opt) {
      case LL_ITERATIVE:
        this.linkedList.iterativeTraversal(this.llHead);
        break;
      case LL_RECURSIVE:
        this.linkedList.recursiveTraversal(this.llHead);
        break;
    }

    // Iterates through the linked list graphically
    List<LLNode> visited = this.linkedList.getVisited();
    if (visited.size() == 0) {
      System.out.println("Error: Traversal has not yet been implemented!");
    }
    Iterator<LLNode> it = visited.iterator();
    KeyFrame kf = new KeyFrame(Duration.seconds(0.5), e -> {
      if (it.hasNext()) {
        LLNode node = it.next();
        node.changeColor();
      }
    });
    Timeline timeline = new Timeline(kf);
    timeline.setCycleCount(visited.size());
    timeline.play();
  }


  private Node createBinaryTreeContent() {
    BorderPane pane = new BorderPane();
    pane.setPrefSize(Constants.PANEL_W, Constants.PANEL_H);
    this.binTreePane = new Pane();
    pane.setCenter(this.binTreePane);
    pane.setBottom(this.createBinaryTreeControlPanel());
    return pane;
  }

  private Pane createBinaryTreeControlPanel() {
    HBox controlPane = new HBox(5);
    controlPane.setAlignment(Pos.CENTER);
    controlPane.setPadding(new Insets(5, 5, 5, 5));
    Button visualizePreorder = new Button("Preorder");
    visualizePreorder.setOnMouseClicked(e -> this.visualizeBinTreeTraversal(Options.BST_PREORDER, 0));

    Button visualizeInorder = new Button("Inorder");
    visualizeInorder.setOnMouseClicked(e -> this.visualizeBinTreeTraversal(Options.BST_INORDER, 0));

    Button visualizePostorder = new Button("Postorder");
    visualizePostorder.setOnMouseClicked(e -> this.visualizeBinTreeTraversal(Options.BST_POSTORDER, 0));

    TextField searchTarget = new TextField();
    Button visualizeSearch = new Button("Search");
    visualizeSearch.setOnMouseClicked(e -> this.visualizeBinTreeTraversal(Options.BST_SEARCH, Double.parseDouble(searchTarget.getText())));

    TextField insertValue = new TextField();
    Button insertBtn = new Button("Insert");
    insertBtn.setOnMouseClicked(e -> this.insertBSTNode(Double.parseDouble(insertValue.getText())));

    Button resetBtn = new Button("Reset");
    resetBtn.setOnMouseClicked(e -> {
      this.generateInitialTreeVals();
      this.buildBinaryTree();
    });

    controlPane.getChildren().addAll(
            visualizePreorder, visualizeInorder, visualizePostorder,
            searchTarget, visualizeSearch, insertValue, insertBtn, resetBtn
    );
    this.generateInitialTreeVals();
    this.buildBinaryTree();
    return controlPane;
  }

  /**
   * Calls methods that build binary tree.
   */
  private void buildBinaryTree() {
    this.binTreePane.getChildren().clear();
    this.root =
            new BSTNode(this.bstNodeVals.get(0), this.binTreePane, Constants.PANEL_W / 2, Constants.PANEL_H / 4);
    this.binTree = new BinaryTree(root, this.binTreePane);

    for (int i = 1; i < this.bstNodeVals.size(); i++) {
      this.binTree.insert(this.bstNodeVals.get(i));
    }
  }

  private void generateInitialTreeVals() {
    this.bstNodeVals = new ArrayList<>();
    this.bstNodeVals.add(4);
    this.bstNodeVals.add(10);
    this.bstNodeVals.add(5);
    this.bstNodeVals.add(2);
    this.bstNodeVals.add(3);
    this.bstNodeVals.add(11);
    this.bstNodeVals.add(1);
    this.bstNodeVals.add(25);
    this.bstNodeVals.add(0);
  }

  private void insertBSTNode(double value) {
    if (this.bstNodeVals.contains(value)) {
      System.out.println("Error: " + value + " already exists in the binary search tree!");
      return;
    } else if ((value % 1) != 0) {
      System.out.println("Error: " + value + " only type in whole numbers");
      return;
    }

    this.bstNodeVals.add((int)value);
    this.binTree.insert((int)value);
  }

  private void visualizeBinTreeTraversal(Options opt, double target) {
    this.buildBinaryTree();
    boolean isSearch = false;
    switch(opt) {
      case BST_PREORDER:
        this.binTree.preOrderTraversal(this.root);
        break;
      case BST_INORDER:
        this.binTree.inOrderTraversal(this.root);
        break;
      case BST_POSTORDER:
        this.binTree.postOrderTraversal(this.root);
        break;
      case BST_SEARCH:
        if ((target) % 1 == 0) {
          isSearch = this.binTree.binarySearch(this.root, target);
        } else {
          System.out.println("Error: " + target + " don't search doubles");
        }
        break;
    }
    List<BSTNode> visited = this.binTree.getVisited();
    if (visited.size() == 0) {
      System.out.println("Error: Traversal has not yet been implemented!");
    }
    Iterator<BSTNode> it = visited.iterator();
    boolean finalIsSearch = isSearch;
    KeyFrame kf = new KeyFrame(Duration.seconds(0.5), e -> {
      if (it.hasNext()) {
        BSTNode node = it.next();
        if (!it.hasNext() && finalIsSearch) {
          node.setFill(Color.LIGHTGREEN);
        } else {
          node.setFill(Color.YELLOW);
        }
      }
    });
    Timeline timeline = new Timeline(kf);
    timeline.setCycleCount(visited.size());
    timeline.play();


  }


}

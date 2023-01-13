package lab9;

import javafx.scene.control.TabPane;

/**
 * ____                      _              _ _ _     _
 * |  _ \  ___    _ __   ___ | |_    ___  __| (_) |_  | |
 * | | | |/ _ \  | '_ \ / _ \| __|  / _ \/ _` | | __| | |
 * | |_| | (_) | | | | | (_) | |_  |  __/ (_| | | |_  |_|
 * |____/ \___/  |_| |_|\___/ \__|  \___|\__,_|_|\__| (_)
 * <p>
 * This is support code for the lab. You may look at it if you are interested,
 * but you will not have to change anything to complete your lab.
 */

/*
 * This class organizes the different ArrayVisualizer and ArrayListVisualizer tabs
 */
public class TabOrganizer {
    private TabPane tabPane;

    public TabOrganizer() {
        this.tabPane = new TabPane();
        Visualizer viz = new Visualizer();
        this.tabPane.getTabs().add(viz.getLinkedListTab());
        this.tabPane.getTabs().add(viz.getBinaryTreeTab());
    }

    /*
     * Returns the TabPane
     */
    public TabPane toNode() {
        return this.tabPane;
    }
}
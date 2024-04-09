package org.example.java_lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;

public class Lab2_Targets implements Iterable<Lab2_Target> {
    private ArrayList<Lab2_Target> targets = new ArrayList<>();
    private Pane parent_pane;

    public Lab2_Targets(Pane parent_pane) {
        this.parent_pane = parent_pane;

        Circle target_1 = new Circle();
        target_1.setLayoutX(450);
        target_1.setLayoutY(250);
        target_1.setRadius(30);
        target_1.setFill(Color.DEEPSKYBLUE);
        target_1.setStroke(Color.BLACK);
        parent_pane.getChildren().add(target_1);
        targets.add(new Lab2_Target(target_1, 1, 200, 5));

        Circle target_2 = new Circle();
        target_2.setLayoutX(550);
        target_2.setLayoutY(250);
        target_2.setRadius(15);
        target_2.setFill(Color.DEEPSKYBLUE);
        target_2.setStroke(Color.BLACK);
        parent_pane.getChildren().add(target_2);
        targets.add(new Lab2_Target(target_2, 1, 200, 10));
    }

    public void nextTick() {
        for (Lab2_Target t : this) {
            t.nextTick();
        }
    }

    public void deleteTargets() {
        for (Lab2_Target t : this) {
            parent_pane.getChildren().remove(t.getCircle());
        }
    }

    @Override
    public Iterator<Lab2_Target> iterator() {
        return targets.iterator();
    }
}

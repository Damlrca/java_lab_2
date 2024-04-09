package org.example.java_lab;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Iterator;

public class Lab2_Targets implements Iterable<Circle> {
    private ArrayList<Circle> targets;

    public Lab2_Targets() {

    }

    public void nextTick() {

    }

    @Override
    public Iterator<Circle> iterator() {
        return targets.iterator();
    }
}

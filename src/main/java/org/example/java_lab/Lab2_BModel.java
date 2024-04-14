package org.example.java_lab;

public class Lab2_BModel {
    static private Lab2_Model model = new Lab2_Model();
    static private Object gameStateMutex = new Object();

    static public Lab2_Model getModel() {
        return model;
    }

    public static Object getGameStateMutex() {
        return gameStateMutex;
    }
}

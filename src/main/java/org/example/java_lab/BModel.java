package org.example.java_lab;

public class BModel {
    private static Model model;

    public static Model build() {
        return model;
    }

    public static void create_model(boolean isServer) {
        model = new Model(isServer);
    }
}

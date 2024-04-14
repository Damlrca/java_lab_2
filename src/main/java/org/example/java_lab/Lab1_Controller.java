package org.example.java_lab;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;

public class Lab1_Controller {
    @FXML
    AnchorPane main_pane;

    @FXML
    Text label_1, label_2;
    int count_bullets = 0, count_score = 0;

    void init_score() {
        count_bullets = 0;
        count_score = 0;
    }

    void write_score() {
        label_1.setText("Количество выстрелов: " + count_bullets);
        label_2.setText("Счёт игрока: " + count_score);
    }

    @FXML
    Line line_1, line_2;
    @FXML
    Circle target_1, target_2;
    int pos_t1, pos_t2;
    final int speed_t1 = 5, speed_t2 = 10;

    int get_cord_y(int pos) {
        if (pos <= 250)
            return pos;
        else
            return 500 - pos;
    }

    void init_circles() {
        pos_t1 = 125;
        target_1.setLayoutX(line_1.getLayoutX());
        target_1.setLayoutY(line_1.getLayoutY() + 50 + get_cord_y(pos_t1));
        pos_t2 = 125;
        target_2.setLayoutX(line_2.getLayoutX());
        target_2.setLayoutY(line_2.getLayoutY() + 50 + get_cord_y(pos_t2));
    }

    void move_circles() {
        pos_t1 = (pos_t1 + speed_t1) % 500;
        target_1.setLayoutY(line_1.getLayoutY() + 50 + get_cord_y(pos_t1));
        pos_t2 = (pos_t2 + speed_t2) % 500;
        target_2.setLayoutY(line_2.getLayoutY() + 50 + get_cord_y(pos_t2));
    }

    Thread game_thread = null;
    Boolean game_run = false, game_pause = false;
    Rectangle bullet = null;
    Circle blast = null;

    void init_bullet() {
        // Rectangle bullet:
        // arcHeight="5.0" arcWidth="5.0"
        // height="10.0" width="25.0"
        // layoutX="63.0" layoutY="189.0"
        // fill="#28d622" stroke="BLACK" strokeType="INSIDE"
        bullet = new Rectangle();
        bullet.setArcHeight(5.0);
        bullet.setArcWidth(5.0);
        bullet.setHeight(10.0);
        bullet.setWidth(10.0);
        bullet.setLayoutX(63);
        bullet.setLayoutY(189);
        bullet.setFill(Paint.valueOf("#28d622"));
        bullet.setStroke(Paint.valueOf("BLACK"));
        bullet.setStrokeType(StrokeType.INSIDE);
        main_pane.getChildren().add(bullet);
    }

    void delete_bullet() {
        // Circle blast
        // layoutY="" layoutX=""
        // radius="9.0"
        // fill="#ff7300" stroke="RED" strokeType="INSIDE"
        blast = new Circle();
        blast.setLayoutX(bullet.getLayoutX() + 0.5 * bullet.getWidth());
        blast.setLayoutY(bullet.getLayoutY() + 0.5 * bullet.getHeight());
        blast.setRadius(9.0);
        blast.setFill(Paint.valueOf("#ff7300"));
        blast.setStroke(Paint.valueOf("RED"));
        blast.setStrokeType(StrokeType.INSIDE);
        main_pane.getChildren().add(blast);

        main_pane.getChildren().remove(bullet);
        bullet = null;
    }

    void delete_blast() {
        main_pane.getChildren().remove(blast);
        blast = null;
    }

    @FXML
    void play_click() {
        if (game_thread != null) return;
        game_run = true;
        game_pause = false;
        if (bullet != null) {
            delete_bullet();
        }
        if (blast != null) {
            delete_blast();
        }
        init_circles();
        init_score();
        write_score();
        game_thread = new Thread(() ->
        {
            try {
                while (game_run) {
                    if (game_pause) {
                        synchronized (this) {
                            this.wait();
                        }
                    }
                    Platform.runLater(() -> {
                        if (blast != null) {
                            delete_blast();
                        }
                        move_circles();
                        if (bullet != null) {
                            bullet.setLayoutX(bullet.getLayoutX() + 10);
                            double dx1 = target_1.getLayoutX() - (bullet.getLayoutX() + 0.5 * bullet.getWidth());
                            double dy1 = target_1.getLayoutY() - (bullet.getLayoutY() + 0.5 * bullet.getHeight());
                            double dx2 = target_2.getLayoutX() - (bullet.getLayoutX() + 0.5 * bullet.getWidth());
                            double dy2 = target_2.getLayoutY() - (bullet.getLayoutY() + 0.5 * bullet.getHeight());
                            if (dx1 * dx1 + dy1 * dy1 <= target_1.getRadius() * target_1.getRadius()) {
                                count_score += 1;
                                delete_bullet();
                            } else if (dx2 * dx2 + dy2 * dy2 <= target_2.getRadius() * target_2.getRadius()) {
                                count_score += 2;
                                delete_bullet();
                            } else if (bullet.getLayoutX() >= 490) {
                                delete_bullet();
                            }
                        }
                        write_score();
                    });
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                // throw new RuntimeException(e);
            }
        }
        );
        game_thread.setDaemon(true);
        game_thread.start();
    }

    @FXML
    void stop_click() {
        if (game_thread == null) return;
        game_run = false;
        game_thread.interrupt();
        game_thread = null;
    }

    @FXML
    void fire_click() {
        if (game_run && !game_pause && bullet == null) {
            init_bullet();
            count_bullets++;
            write_score();
        }
    }

    @FXML
    void pause_click() {
        game_pause = true;
    }

    @FXML
    void continue_click() {
        game_pause = false;
        synchronized (this) {
            notifyAll();
        }
    }
}
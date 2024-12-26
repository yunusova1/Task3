package com.example.observers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle; // Импортируем Circle

public class Controller {
    @FXML
    private Label timeLabel;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label animationStatusLabel;
    @FXML
    private Circle componentThreeShape; // Изменено на Circle
    private TimeServer timeServer;
    private ComponentOne componentOne;
    private ComponentTwo componentTwo;
    private ComponentThree componentThree;

    @FXML
    public void initialize() {
        timeServer = new TimeServer();
        componentOne = new ComponentOne(timeLabel);
        componentTwo = new ComponentTwo();
        componentThree = new ComponentThree(componentThreeShape, animationStatusLabel); // Теперь передаем Circle
        timeServer.attach(componentOne);
        timeServer.attach(componentTwo);
        timeServer.attach(componentThree);

        startButton.setOnAction(e -> {
            statusLabel.setText("Статус: Активен");
            statusLabel.setStyle("-fx-text-fill: white;");
            timeServer.start();
        });

        stopButton.setOnAction(e -> {
            statusLabel.setText("Статус: Неактивен");
            statusLabel.setStyle("-fx-text-fill: white;");
            timeServer.stop();
            componentTwo.stopMusic();
            componentThree.stopAnimation();
        });
    }
}

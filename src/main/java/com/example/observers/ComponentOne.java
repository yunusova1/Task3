package com.example.observers;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class ComponentOne implements IObserver {
    private Label timeLabel;

    public ComponentOne(Label label) {
        this.timeLabel = label;
        this.timeLabel.setStyle("-fx-text-fill: white;");
    }
    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;
        Platform.runLater(() -> {
            timeLabel.setText("Текущий таймер: " + timeServer.getState() + " секунд");
        });
    }
}

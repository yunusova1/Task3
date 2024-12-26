package com.example.observers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle; // Импортируем Circle
import javafx.util.Duration;

public class ComponentThree implements IObserver {
    private Timeline colorAnimation;
    private TranslateTransition moveAnimation;
    private Circle animatedCircle; // Изменено на Circle
    private Label animationStatusLabel;

    public ComponentThree(Circle circle, Label animationStatusLabel) { // Параметр изменен на Circle
        this.animatedCircle = circle; // Изменено на Circle
        this.animationStatusLabel = animationStatusLabel;
        createAnimations();
    }

    private void createAnimations() {
        colorAnimation = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> animatedCircle.setFill(Color.PINK)),
                new KeyFrame(Duration.seconds(1), e -> animatedCircle.setFill(Color.PURPLE)),
                new KeyFrame(Duration.seconds(2), e -> animatedCircle.setFill(Color.BLUE)),
                new KeyFrame(Duration.seconds(3), e -> animatedCircle.setFill(Color.RED)),
                new KeyFrame(Duration.seconds(4), e -> animatedCircle.setFill(Color.LIME)),
                new KeyFrame(Duration.seconds(5), e -> animatedCircle.setFill(Color.GREEN)),
                new KeyFrame(Duration.seconds(6), e -> animatedCircle.setFill(Color.YELLOW)),
                new KeyFrame(Duration.seconds(7), e -> animatedCircle.setFill(Color.ORANGE)),
                new KeyFrame(Duration.seconds(8), e -> animatedCircle.setFill(Color.WHITE)),
                new KeyFrame(Duration.seconds(9), e -> animatedCircle.setFill(Color.BLACK)),
                new KeyFrame(Duration.seconds(10), e -> animatedCircle.setFill(Color.PALEGREEN))
        );
        colorAnimation.setCycleCount(Timeline.INDEFINITE);

        moveAnimation = new TranslateTransition(Duration.seconds(10), animatedCircle);
        moveAnimation.setFromX(0);
        moveAnimation.setToX(200);
        moveAnimation.setCycleCount(Timeline.INDEFINITE);
        moveAnimation.setAutoReverse(true);
    }

    @Override
    public void update(Subject subject) {
        TimeServer timeServer = (TimeServer) subject;
        if (timeServer.getState() % 5 == 0) {
            String message = "Анимация перезапущена в " + timeServer.getState() + " секунд";
            Platform.runLater(() -> {
                animationStatusLabel.setText(message);
                animationStatusLabel.setStyle("-fx-text-fill: white;");
            });
            if (colorAnimation.getStatus() != Timeline.Status.RUNNING) {
                colorAnimation.play();
            }
            if (moveAnimation.getStatus() != Timeline.Status.RUNNING) {
                moveAnimation.play();
            }
        }
    }

    public void stopAnimation() {
        if (colorAnimation.getStatus() == Timeline.Status.RUNNING) {
            colorAnimation.stop();
        }
        if (moveAnimation.getStatus() == Timeline.Status.RUNNING) {
            moveAnimation.stop();
            Platform.runLater(() -> {
                animatedCircle.setTranslateX(0); // Устанавливаем положение круга обратно
                animationStatusLabel.setText("Анимация остановлена");
                animationStatusLabel.setStyle("-fx-text-fill: white;");
            });
        }
    }
}

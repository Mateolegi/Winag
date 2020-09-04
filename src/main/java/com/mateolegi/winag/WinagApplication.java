package com.mateolegi.winag;

import com.mateolegi.winag.annotation.Winag;
import com.mateolegi.winag.annotation.Field;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WinagApplication extends Application {

    private static Class<?> source;
    private Object instance;

    private void setValue(java.lang.reflect.Field field, Object newValue) {
        try {
            boolean accessible = field.canAccess(instance);
            field.setAccessible(true);
            field.set(instance, newValue);
            field.setAccessible(accessible);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<java.lang.reflect.Field> getAnnotatedFields() {
        return Stream.of(source.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Field.class))
                .collect(Collectors.toList());
    }

    private void setFieldIntoPane(VBox pane) {
        getAnnotatedFields()
                .forEach(field -> {
                    Field winagField = field.getAnnotation(Field.class);
                    HBox hbox = LayoutFactory.createHBox();
                    Label label = new Label(winagField.label());
                    TextField textField = new TextField();
                    textField.textProperty().addListener((observable, oldValue, newValue) -> setValue(field, newValue));
                    hbox.getChildren().addAll(label, textField);
                    VBox.setMargin(hbox, new Insets(0, 0, 0, 8));
                    pane.getChildren().add(hbox);
                });
    }

    @Override
    public void start(Stage stage) throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        var winag = source.getAnnotation(Winag.class);
        instance = source.getDeclaredConstructor().newInstance();
        var title = winag.title();
        VBox pane = LayoutFactory.createVBox();
        setFieldIntoPane(pane);
        Scene scene = new Scene(pane, winag.width(), winag.height());
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }

    public static void run(Class<?> primarySource, String... args) {
        if (!primarySource.isAnnotationPresent(Winag.class)) {
            throw new IllegalArgumentException("Unable to create window. @Winag is not present.");
        }
        source = primarySource;
        launch(args);
    }
}

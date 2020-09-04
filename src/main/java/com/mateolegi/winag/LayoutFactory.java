package com.mateolegi.winag;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LayoutFactory {

    public static VBox createVBox() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        return vbox;
    }

    public static HBox createHBox() {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(10);
        return hbox;
    }
}

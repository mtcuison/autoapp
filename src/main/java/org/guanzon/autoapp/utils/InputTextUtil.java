/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.guanzon.autoapp.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author User
 */
public class InputTextUtil {

    /*Convert Date to String*/
    public static LocalDate strToDate(String val) {
        DateTimeFormatter date_formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(val, date_formatter);
        return localDate;
    }

    public static void setCapsLockBehavior(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textField.getText() != null) {
                textField.setText(newValue.toUpperCase());
            }
        });
    }

    public static void setCapsLockBehavior(TextArea textArea) {
        textArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textArea.getText() != null) {
                textArea.setText(newValue.toUpperCase());
            }
        });
    }

    public static void addTextLimiter(TextField textField, int maxLength) {
        // Remove any existing listeners to avoid stacking them
        if (textField.getProperties().get("textLimiter") != null) {
            textField.textProperty().removeListener((ChangeListener) textField.getProperties().get("textLimiter"));
        }

        // Create a new listener to limit the text length
        ChangeListener<String> textLimiter = (observable, oldValue, newValue) -> {
            if (newValue.length() > maxLength) {
                textField.setText(oldValue);
            }
        };

        // Add the new listener to the text field
        textField.textProperty().addListener(textLimiter);
        // Store the listener reference for future removal if necessary
        textField.getProperties().put("textLimiter", textLimiter);
    }
}

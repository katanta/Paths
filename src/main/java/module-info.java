module IDATT.GROUP32 {
    requires javafx.controls;
    requires javafx.graphics;
    exports edu.ntnu.mappe32;
    opens edu.ntnu.mappe32.model;
    opens edu.ntnu.mappe32.io;
    opens edu.ntnu.mappe32.controller;
    opens edu.ntnu.mappe32.view;
}
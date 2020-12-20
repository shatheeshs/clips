module com.clips {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;
  requires java.sql;

  opens com.clips.controller to javafx.fxml;
  opens com.clips.model to javafx.base;
  exports com.clips;
}
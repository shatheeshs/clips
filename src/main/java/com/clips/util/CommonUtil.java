package com.clips.util;

import com.clips.App;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class CommonUtil {


  /**
   * System exit
   */
  public static void systemExit() {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        Platform.exit();
        System.exit(0);
      }
    });
  }

  /**
   * System hide
   *
   * @param stage
   */
  public static void stageHide(Stage stage) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        stage.hide();
      }
    });
  }

  /**
   * System show
   *
   * @param stage
   */
  public static void stageShow(Stage stage) {
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        stage.show();
      }
    });
  }

  /**
   * Show about box
   */
  public static void aboutShow(){
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("about.fxml"));
      Parent root1 = (Parent) fxmlLoader.load();
      Stage stage = new Stage();
      stage.initStyle(StageStyle.UTILITY);
      stage.setResizable(false);
      stage.setTitle("About Clips");
      stage.setScene(new Scene(root1));
      stage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Check null or empty string
   * @param str
   * @return
   */
  public static boolean isEmptyOrNullString(String str) {
    if (str == null || str.isEmpty()) {
      return true;
    }
    return false;
  }
}

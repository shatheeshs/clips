package com.clips.controller;

import com.clips.App;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AboutController implements Initializable {

  @FXML
  private ImageView aboutImageView;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    Image image = new Image(App.class.getResourceAsStream("/images/clipsAbout.png"));
    aboutImageView.setImage(image);
  }
}

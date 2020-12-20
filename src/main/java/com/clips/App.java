package com.clips;

import com.clips.presentation.ClipBoardMainFrame;
import com.clips.util.CommonUtil;
import com.clips.util.ConfigUtil;
import com.clips.util.Constants;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Properties;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.UIManager;


/**
 * JavaFX App
 */
public class App extends Application {

  private static Scene scene;
  private PopupMenu systemTrayPopup;
  private SystemTray tray;
  public static Properties clipsProperties;
  @Override
  public void start(Stage stage) throws IOException {

    ImageIcon trayIconImg = null;
    Image appImg = null;
    StringBuilder mainTitle = new StringBuilder("Clips ");
    scene = ClipBoardMainFrame.getInstance(loadFXMLLoader("dashboard"), 1000, 600);
    Platform.setImplicitExit(false);

    try { ;
      clipsProperties = ConfigUtil.loadProperties();
      String version = App.class.getPackage().getImplementationVersion();

      if (clipsProperties.get(Constants.TRAY_ICON_PATH) != null) {
        trayIconImg = new ImageIcon(clipsProperties.get(Constants.TRAY_ICON_PATH).toString());
      }
      if (clipsProperties.get(Constants.APP_ICON_PATH) != null) {
        stage.getIcons().add(new Image(App.class.getResourceAsStream(clipsProperties.get(Constants.APP_ICON_PATH).toString())));
      }
      if (CommonUtil.isEmptyOrNullString(version)) {
        version = "[DEV]";
      }


      mainTitle.append(version);
      if (trayIconImg != null) {
        initClipsTray(stage, trayIconImg);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }


    stage.setTitle(mainTitle.toString());
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Initialize Clips to system tray
   *
   * @param stage
   * @throws AWTException
   */
  private void initClipsTray(Stage stage , ImageIcon imageIcon ) throws AWTException {

    TrayIcon trayIcon = new TrayIcon(imageIcon.getImage());
    systemTrayPopup = new PopupMenu();
    MenuItem showItem = new MenuItem("Show");
    MenuItem exitItem = new MenuItem("Exit");
    systemTrayPopup.add(showItem);
    systemTrayPopup.addSeparator();
    systemTrayPopup.add(exitItem);

    trayIcon.setPopupMenu(systemTrayPopup);
    tray = SystemTray.getSystemTray();
    tray.add(trayIcon);

    showItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CommonUtil.stageShow(stage);
      }
    });

    exitItem.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        CommonUtil.systemExit();
      }
    });

  }

  private static FXMLLoader loadFXMLLoader(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader;
  }

  public static void main(String[] args) {
    launch();
  }
}
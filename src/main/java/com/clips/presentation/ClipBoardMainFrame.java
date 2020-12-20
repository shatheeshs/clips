package com.clips.presentation;

import static javax.swing.JOptionPane.*;

import com.clips.controller.DashBoardController;
import com.clips.listener.ClipBoardListener;
import com.clips.listener.OnCopyListener;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

public class ClipBoardMainFrame extends Scene {

  private static ClipBoardMainFrame clipBoardMainFrameInstance;
  private JList<String> contentList;
  private DefaultListModel<String> contentListModel;
  private ListSelectionModel selectionModel;
  private DashBoardController dashBoardController;
  private OnCopyListener onCopyListener;



  private ClipBoardMainFrame(FXMLLoader fxmlLoader, double v, double v1) throws IOException {
    super(fxmlLoader.load(), v, v1);
    init(fxmlLoader);

    if (!SystemTray.isSupported()) {
      System.out.println("SystemTray is not supported");
      return;
    }

  }

  public static ClipBoardMainFrame getInstance() {
      return clipBoardMainFrameInstance;
  }

  public static ClipBoardMainFrame getInstance( FXMLLoader fxmlLoader, double v, double v1 )
      throws IOException {
    if (clipBoardMainFrameInstance == null) {
      clipBoardMainFrameInstance = new ClipBoardMainFrame(fxmlLoader, v,  v1);
    }
    return clipBoardMainFrameInstance;
  }


  public void init(FXMLLoader fxmlLoader) {

    dashBoardController = fxmlLoader.getController();
    contentListModel = new DefaultListModel<String>();
    contentList = new JList<>(contentListModel);
    onCopyListener = new OnCopyListener();
    selectionModel = contentList.getSelectionModel();
    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    contentListModel.addListDataListener(dashBoardController);
    onCopyListener.setContentListModel(contentListModel);

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        ClipBoardListener clipBoardListener = new ClipBoardListener();
        clipBoardListener.setBaseListener(onCopyListener);
        clipBoardListener.start();
      }
    });

  }
}

package com.clips.controller;


import com.clips.App;
import com.clips.model.ClipBoardData;
import com.clips.util.CommonUtil;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class DashBoardController implements ListDataListener, Initializable {

  @FXML
  private TableView<ClipBoardData> clipBoardData;

  @FXML
  private TableColumn<ClipBoardData, String> clipBoardColumnData;

  @FXML
  private TableColumn<ClipBoardData, String> clipBoardColumnTimeStampData;

  @FXML
  private AnchorPane baseAnchorPane;

  @FXML
  private MenuItem clearMenuItem;

  @FXML
  private MenuItem exitMenuItem;

  @FXML
  private MenuItem aboutMenuItem;




  private ObservableList<ClipBoardData> data;

  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm");


  public DashBoardController() {
    data = FXCollections.observableArrayList();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    clipBoardData.setEditable(true);
    clipBoardData.getSelectionModel().setCellSelectionEnabled(true);
    clipBoardData.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    clipBoardData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    clipBoardData.setItems(data);
    clipBoardColumnData.setCellFactory(TextFieldTableCell.<ClipBoardData>forTableColumn());
    clipBoardColumnTimeStampData.setCellFactory(TextFieldTableCell.<ClipBoardData>forTableColumn());

    clearMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        data.clear();
      }
    });

    exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        CommonUtil.systemExit();
      }
    });

    aboutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent actionEvent) {
        CommonUtil.aboutShow();
      }
    });
  }

  @Override
  public void intervalAdded(ListDataEvent e) {

    try {
      Timestamp currentTime = new Timestamp(System.currentTimeMillis());
      DefaultListModel theModel = (DefaultListModel) e.getSource();
      if (theModel != null && !theModel.isEmpty()) {
        String caughtData = (String) theModel.get(0);
        boolean isDuplicated = data.stream()
            .anyMatch(caught -> caught.getData().equals(caughtData));
        if (!isDuplicated) {
          data.add(new ClipBoardData(caughtData, sdf.format(currentTime)));
        }
      }

    } catch (Throwable ex) {
      ex.printStackTrace();
    }

  }

  @Override
  public void intervalRemoved(ListDataEvent e) {

  }

  @Override
  public void contentsChanged(ListDataEvent e) {

  }

}

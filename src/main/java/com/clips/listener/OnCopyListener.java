package com.clips.listener;

import javax.swing.DefaultListModel;

public class OnCopyListener implements BaseListener{

  private DefaultListModel<String> contentListModel;

  @Override
  public void onCopy(String data) {
    contentListModel.add(0, data);
  }

  /**
   * Setup data transfer for table
   * @param contentListModel
   */
  public void setContentListModel(DefaultListModel<String> contentListModel) {
    this.contentListModel = contentListModel;
  }
}

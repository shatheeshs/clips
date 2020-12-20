package com.clips.model;

public class ClipBoardData {

  private String data;
  private String timeStamp;

  public ClipBoardData(String data,
      String timeStamp) {
    this.data = data;
    this.timeStamp = timeStamp;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }
}

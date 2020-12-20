package com.clips.listener;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import javafx.application.Platform;

public class ClipBoardListener extends Thread implements ClipboardOwner {

  private final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
  private BaseListener baseListener;


  
  @Override
  public void run() {
    Transferable transferable = clipboard.getContents(this);
    regainOwnership(clipboard, transferable);
    while (true) {
    }
  }

  @Override
  public void lostOwnership(Clipboard clipboard, Transferable contents) {

    //Need to run in different thread, since main javafx loop should not be destroyed.
    Platform.runLater(new Runnable() {
      @Override
      public void run() {
        try {
          sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }

       Transferable content = clipboard.getContents(this);
        process(content);
        regainOwnership(clipboard, content);
      }
    });
  }

  /**
   * Caught contents are processed here
   * @param transferable
   */
  private void process(Transferable transferable) {

    try {
      String caughtData = (String) transferable.getTransferData(DataFlavor.stringFlavor);

      if (baseListener != null) {
        baseListener.onCopy(caughtData);
      }
    } catch (Exception e) {
      System.out.println("[Unsupported Format]");
    }
  }

  /**
   * Regain the clipboard ownership
   * @param clipboard
   * @param transferable
   */
  private void regainOwnership(Clipboard clipboard, Transferable transferable) {
    clipboard.setContents(transferable, this);
  }

  /**
   * Setup onCopy listener
   * @param baseListener
   */
  public void setBaseListener(BaseListener baseListener) {
    this.baseListener = baseListener;
  }

}

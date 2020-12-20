package com.clips.util;

import com.clips.App;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

  public static Properties loadProperties() {

    InputStream inputStream;
    Properties prop = new Properties();
    try {
      String propFileName = "config.properties";

      inputStream = App.class.getClassLoader().getResourceAsStream(propFileName);

      if (inputStream != null) {
        prop.load(inputStream);
      } else {
        throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return prop;
  }
}

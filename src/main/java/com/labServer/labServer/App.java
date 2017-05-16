package com.labServer.labServer;

import com.labServer.entity.LabDisprobeNumber;
import com.labServer.manager.LabDisplayParamterManager;
import com.labServer.manager.LabDisprobeNumberManager;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {

    LabDisprobeNumberManager disprobeNumberManager = new LabDisprobeNumberManager();
    disprobeNumberManager.getDisprobeNumberByInputProbNum("8AD0000101");;
  }
}

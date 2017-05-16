package com.labServer.entity;

import java.util.Date;

public class LabDisprobeNumber {

  
  
  private Integer id;
  private String inputProbeNumber;
  private String displayProbeNumber;
  private Date create_time;
  private String tab_inputName;
  private String tab_displayName;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public String getInputProbeNumber() {
    return inputProbeNumber;
  }
  public void setInputProbeNumber(String inputProbeNumber) {
    this.inputProbeNumber = inputProbeNumber;
  }
  public String getDisplayProbeNumber() {
    return displayProbeNumber;
  }
  public void setDisplayProbeNumber(String displayProbeNumber) {
    this.displayProbeNumber = displayProbeNumber;
  }
  public Date getCreate_time() {
    return create_time;
  }
  public void setCreate_time(Date create_time) {
    this.create_time = create_time;
  }
  public String getTab_inputName() {
    return tab_inputName;
  }
  public void setTab_inputName(String tab_inputName) {
    this.tab_inputName = tab_inputName;
  }
  public String getTab_displayName() {
    return tab_displayName;
  }
  public void setTab_displayName(String tab_displayName) {
    this.tab_displayName = tab_displayName;
  }
  @Override
  public String toString() {
    return "LabDisprobenumber [inputProbeNumber=" + inputProbeNumber + ", displayProbeNumber="
        + displayProbeNumber + ", create_time=" + create_time + ", tab_inputName=" + tab_inputName
        + ", tab_displayName=" + tab_displayName + "]";
  }
  
  
  
  
  
  
}

package com.labServer.entity;

import java.util.Date;

public class LabInputParamter {

  
  private Integer id;
  private String inputProbeNumber;
  private Date createdOn;
  private Double inputTemperature;
  private Double inputHumidity;
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
  public Date getCreatedOn() {
    return createdOn;
  }
  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }
  public Double getInputTemperature() {
    return inputTemperature;
  }
  public void setInputTemperature(Double inputTemperature) {
    this.inputTemperature = inputTemperature;
  }
  public Double getInputHumidity() {
    return inputHumidity;
  }
  public void setInputHumidity(Double inputHumidity) {
    this.inputHumidity = inputHumidity;
  }
  @Override
  public String toString() {
    return "LabInputparamter [inputProbeNumber=" + inputProbeNumber + ", createdOn=" + createdOn
        + ", inputTemperature=" + inputTemperature + ", inputHumidity=" + inputHumidity + "]";
  }
  
  
  
  
  
  
  
  
  
  
}

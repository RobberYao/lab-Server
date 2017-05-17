package com.labServer.entity;

import java.util.Date;

public class LabDisplayParamter {

  private Integer id;
  private String inputProbeNumber;
  private String disProbeNumber;
  private Date createdOn;
  private Double disTemperature;
  private Double disHumidity;

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

  public String getDisProbeNumber() {
    return disProbeNumber;
  }

  public void setDisProbeNumber(String disProbeNumber) {
    this.disProbeNumber = disProbeNumber;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Double getDisTemperature() {
    return disTemperature;
  }

  public void setDisTemperature(Double disTemperature) {
    this.disTemperature = disTemperature;
  }

  public Double getDisHumidity() {
    return disHumidity;
  }

  public void setDisHumidity(Double disHumidity) {
    this.disHumidity = disHumidity;
  }

  @Override
  public String toString() {
    return "LabDisplayParamter [inputProbeNumber=" + inputProbeNumber + ", disProbeNumber="
        + disProbeNumber + ", createdOn=" + createdOn + ", disTemperature=" + disTemperature
        + ", disHumidity=" + disHumidity + "]";
  }



}

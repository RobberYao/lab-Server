package com.labServer.entity;

import java.util.Date;

public class LabModify {

  private Integer id;
  private String inputProbeNumber;
  private String disProbeNumber;
  private Date modifyOn;
  private Date createdOn;
  private Date stopEnd;
  private String modifyParamter;
  private Integer modifyNumber;
  private String Status;
  private String name;

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

  public Date getModifyOn() {
    return modifyOn;
  }

  public void setModifyOn(Date modifyOn) {
    this.modifyOn = modifyOn;
  }

  public Date getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Date createdOn) {
    this.createdOn = createdOn;
  }

  public Date getStopEnd() {
    return stopEnd;
  }

  public void setStopEnd(Date stopEnd) {
    this.stopEnd = stopEnd;
  }

  public String getModifyParamter() {
    return modifyParamter;
  }

  public void setModifyParamter(String modifyParamter) {
    this.modifyParamter = modifyParamter;
  }

  public Integer getModifyNumber() {
    return modifyNumber;
  }

  public void setModifyNumber(Integer modifyNumber) {
    this.modifyNumber = modifyNumber;
  }

  public String getStatus() {
    return Status;
  }

  public void setStatus(String status) {
    Status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "LabModify [id=" + id + ", inputProbeNumber=" + inputProbeNumber + ", disProbeNumber="
        + disProbeNumber + ", modifyOn=" + modifyOn + ", createdOn=" + createdOn + ", stopEnd="
        + stopEnd + ", modifyParamter=" + modifyParamter + ", modifyNumber=" + modifyNumber
        + ", Status=" + Status + ", name=" + name + "]";
  }



}
/**
 * -----------------------------------------------------------------------------
 * Company: Janssen Pharmaceuticals, Inc.
 * -----------------------------------------------------------------------------
 *
 * This file contains trade secrets of Janssen Pharmaceuticals, Inc. No part may
 * be reproduced or transmitted in any form by any means or for any purpose
 * without the express written permission of Janssen Pharmaceuticals, Inc.
 *
 * @version 1.0.0-SNAPSHOT
 *
 * Copyright: (C) 2013
 */
package com.referadr.practice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

//The Abstract Entity extend by other DB Entity for common fields in DB
/**
 * provide getter and setter for AbstractEntity.
 * 
 * @author nitin.uikey.
 * @version 1.0, 13 April, 2013.
 */
@MappedSuperclass
public abstract class AbstractEntity {

  /**
   * Date of creation.
   */
  @Column(name = "CREATION_DATE")
  private Date creationDate =new Date();
  /**
   * Date of updation.
   * */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate =new Date();;

  @Column(name = "CREATED_BY")
  private String createdBy="SYSTEM";
  /**
   * Date of updation.
   * */
  @Column(name = "UPDATED_BY")
  private String updatedBy="SYSTEM";

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = new Date();
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = new Date();
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = "SYSTEM";
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = "SYSTEM";
  }

}
package com.chinasofti.rcloud.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ApplicationOrderEntity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.order_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String orderId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.order_number
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String orderNumber;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.buyer_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String buyerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.application_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String applicationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.order_status
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Integer orderStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.end_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.created_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Date createdTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.provider_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String providerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.maintenance_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private BigDecimal maintenanceCosts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.mark_delete
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Integer markDelete;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.pay_pattern
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Integer payPattern;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.sub_domain
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String subDomain;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.cancel_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Date cancelTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.accounting_tag
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Integer accountingTag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.deploy_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Date deployTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.deploy_type
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private Integer deployType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.total_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private BigDecimal totalCosts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table portal_application_order
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.order_id
     *
     * @return the value of portal_application_order.order_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.order_id
     *
     * @param orderId the value for portal_application_order.order_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.order_number
     *
     * @return the value of portal_application_order.order_number
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.order_number
     *
     * @param orderNumber the value for portal_application_order.order_number
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber == null ? null : orderNumber.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.buyer_id
     *
     * @return the value of portal_application_order.buyer_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getBuyerId() {
        return buyerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.buyer_id
     *
     * @param buyerId the value for portal_application_order.buyer_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId == null ? null : buyerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.application_id
     *
     * @return the value of portal_application_order.application_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.application_id
     *
     * @param applicationId the value for portal_application_order.application_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId == null ? null : applicationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.order_status
     *
     * @return the value of portal_application_order.order_status
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.order_status
     *
     * @param orderStatus the value for portal_application_order.order_status
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.end_time
     *
     * @return the value of portal_application_order.end_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.end_time
     *
     * @param endTime the value for portal_application_order.end_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.created_time
     *
     * @return the value of portal_application_order.created_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.created_time
     *
     * @param createdTime the value for portal_application_order.created_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.provider_id
     *
     * @return the value of portal_application_order.provider_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getProviderId() {
        return providerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.provider_id
     *
     * @param providerId the value for portal_application_order.provider_id
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.maintenance_costs
     *
     * @return the value of portal_application_order.maintenance_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public BigDecimal getMaintenanceCosts() {
        return maintenanceCosts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.maintenance_costs
     *
     * @param maintenanceCosts the value for portal_application_order.maintenance_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setMaintenanceCosts(BigDecimal maintenanceCosts) {
        this.maintenanceCosts = maintenanceCosts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.mark_delete
     *
     * @return the value of portal_application_order.mark_delete
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Integer getMarkDelete() {
        return markDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.mark_delete
     *
     * @param markDelete the value for portal_application_order.mark_delete
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setMarkDelete(Integer markDelete) {
        this.markDelete = markDelete;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.pay_pattern
     *
     * @return the value of portal_application_order.pay_pattern
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Integer getPayPattern() {
        return payPattern;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.pay_pattern
     *
     * @param payPattern the value for portal_application_order.pay_pattern
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setPayPattern(Integer payPattern) {
        this.payPattern = payPattern;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.sub_domain
     *
     * @return the value of portal_application_order.sub_domain
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getSubDomain() {
        return subDomain;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.sub_domain
     *
     * @param subDomain the value for portal_application_order.sub_domain
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain == null ? null : subDomain.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.cancel_time
     *
     * @return the value of portal_application_order.cancel_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Date getCancelTime() {
        return cancelTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.cancel_time
     *
     * @param cancelTime the value for portal_application_order.cancel_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.accounting_tag
     *
     * @return the value of portal_application_order.accounting_tag
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Integer getAccountingTag() {
        return accountingTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.accounting_tag
     *
     * @param accountingTag the value for portal_application_order.accounting_tag
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setAccountingTag(Integer accountingTag) {
        this.accountingTag = accountingTag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.deploy_time
     *
     * @return the value of portal_application_order.deploy_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Date getDeployTime() {
        return deployTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.deploy_time
     *
     * @param deployTime the value for portal_application_order.deploy_time
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setDeployTime(Date deployTime) {
        this.deployTime = deployTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.deploy_type
     *
     * @return the value of portal_application_order.deploy_type
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Integer getDeployType() {
        return deployType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.deploy_type
     *
     * @param deployType the value for portal_application_order.deploy_type
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setDeployType(Integer deployType) {
        this.deployType = deployType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.total_costs
     *
     * @return the value of portal_application_order.total_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public BigDecimal getTotalCosts() {
        return totalCosts;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.total_costs
     *
     * @param totalCosts the value for portal_application_order.total_costs
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setTotalCosts(BigDecimal totalCosts) {
        this.totalCosts = totalCosts;
    }
}
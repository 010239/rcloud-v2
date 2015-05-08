package com.chinasofti.rcloud.domain;

import java.io.Serializable;

public class ApplicationOrderEntityWithBLOBs extends ApplicationOrderEntity implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.order_description
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String orderDescription;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column portal_application_order.cancel_reason
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private String cancelReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table portal_application_order
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.order_description
     *
     * @return the value of portal_application_order.order_description
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getOrderDescription() {
        return orderDescription;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.order_description
     *
     * @param orderDescription the value for portal_application_order.order_description
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription == null ? null : orderDescription.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column portal_application_order.cancel_reason
     *
     * @return the value of portal_application_order.cancel_reason
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getCancelReason() {
        return cancelReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column portal_application_order.cancel_reason
     *
     * @param cancelReason the value for portal_application_order.cancel_reason
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }
}
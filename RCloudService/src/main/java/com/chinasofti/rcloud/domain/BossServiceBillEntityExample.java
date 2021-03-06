package com.chinasofti.rcloud.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BossServiceBillEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public BossServiceBillEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andServiceBillIdIsNull() {
            addCriterion("service_bill_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdIsNotNull() {
            addCriterion("service_bill_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdEqualTo(String value) {
            addCriterion("service_bill_id =", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdNotEqualTo(String value) {
            addCriterion("service_bill_id <>", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdGreaterThan(String value) {
            addCriterion("service_bill_id >", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdGreaterThanOrEqualTo(String value) {
            addCriterion("service_bill_id >=", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdLessThan(String value) {
            addCriterion("service_bill_id <", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdLessThanOrEqualTo(String value) {
            addCriterion("service_bill_id <=", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdLike(String value) {
            addCriterion("service_bill_id like", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdNotLike(String value) {
            addCriterion("service_bill_id not like", value, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdIn(List<String> values) {
            addCriterion("service_bill_id in", values, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdNotIn(List<String> values) {
            addCriterion("service_bill_id not in", values, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdBetween(String value1, String value2) {
            addCriterion("service_bill_id between", value1, value2, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andServiceBillIdNotBetween(String value1, String value2) {
            addCriterion("service_bill_id not between", value1, value2, "serviceBillId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andTotalChargeIsNull() {
            addCriterion("total_charge is null");
            return (Criteria) this;
        }

        public Criteria andTotalChargeIsNotNull() {
            addCriterion("total_charge is not null");
            return (Criteria) this;
        }

        public Criteria andTotalChargeEqualTo(BigDecimal value) {
            addCriterion("total_charge =", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeNotEqualTo(BigDecimal value) {
            addCriterion("total_charge <>", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeGreaterThan(BigDecimal value) {
            addCriterion("total_charge >", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_charge >=", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeLessThan(BigDecimal value) {
            addCriterion("total_charge <", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_charge <=", value, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeIn(List<BigDecimal> values) {
            addCriterion("total_charge in", values, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeNotIn(List<BigDecimal> values) {
            addCriterion("total_charge not in", values, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_charge between", value1, value2, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andTotalChargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_charge not between", value1, value2, "totalCharge");
            return (Criteria) this;
        }

        public Criteria andAccountStartIsNull() {
            addCriterion("account_start is null");
            return (Criteria) this;
        }

        public Criteria andAccountStartIsNotNull() {
            addCriterion("account_start is not null");
            return (Criteria) this;
        }

        public Criteria andAccountStartEqualTo(Date value) {
            addCriterion("account_start =", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartNotEqualTo(Date value) {
            addCriterion("account_start <>", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartGreaterThan(Date value) {
            addCriterion("account_start >", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartGreaterThanOrEqualTo(Date value) {
            addCriterion("account_start >=", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartLessThan(Date value) {
            addCriterion("account_start <", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartLessThanOrEqualTo(Date value) {
            addCriterion("account_start <=", value, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartIn(List<Date> values) {
            addCriterion("account_start in", values, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartNotIn(List<Date> values) {
            addCriterion("account_start not in", values, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartBetween(Date value1, Date value2) {
            addCriterion("account_start between", value1, value2, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountStartNotBetween(Date value1, Date value2) {
            addCriterion("account_start not between", value1, value2, "accountStart");
            return (Criteria) this;
        }

        public Criteria andAccountEndIsNull() {
            addCriterion("account_end is null");
            return (Criteria) this;
        }

        public Criteria andAccountEndIsNotNull() {
            addCriterion("account_end is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEndEqualTo(Date value) {
            addCriterion("account_end =", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndNotEqualTo(Date value) {
            addCriterion("account_end <>", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndGreaterThan(Date value) {
            addCriterion("account_end >", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndGreaterThanOrEqualTo(Date value) {
            addCriterion("account_end >=", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndLessThan(Date value) {
            addCriterion("account_end <", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndLessThanOrEqualTo(Date value) {
            addCriterion("account_end <=", value, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndIn(List<Date> values) {
            addCriterion("account_end in", values, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndNotIn(List<Date> values) {
            addCriterion("account_end not in", values, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndBetween(Date value1, Date value2) {
            addCriterion("account_end between", value1, value2, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andAccountEndNotBetween(Date value1, Date value2) {
            addCriterion("account_end not between", value1, value2, "accountEnd");
            return (Criteria) this;
        }

        public Criteria andChargeMonthIsNull() {
            addCriterion("charge_month is null");
            return (Criteria) this;
        }

        public Criteria andChargeMonthIsNotNull() {
            addCriterion("charge_month is not null");
            return (Criteria) this;
        }

        public Criteria andChargeMonthEqualTo(Integer value) {
            addCriterion("charge_month =", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthNotEqualTo(Integer value) {
            addCriterion("charge_month <>", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthGreaterThan(Integer value) {
            addCriterion("charge_month >", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_month >=", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthLessThan(Integer value) {
            addCriterion("charge_month <", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthLessThanOrEqualTo(Integer value) {
            addCriterion("charge_month <=", value, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthIn(List<Integer> values) {
            addCriterion("charge_month in", values, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthNotIn(List<Integer> values) {
            addCriterion("charge_month not in", values, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthBetween(Integer value1, Integer value2) {
            addCriterion("charge_month between", value1, value2, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeMonthNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_month not between", value1, value2, "chargeMonth");
            return (Criteria) this;
        }

        public Criteria andChargeYearIsNull() {
            addCriterion("charge_year is null");
            return (Criteria) this;
        }

        public Criteria andChargeYearIsNotNull() {
            addCriterion("charge_year is not null");
            return (Criteria) this;
        }

        public Criteria andChargeYearEqualTo(Integer value) {
            addCriterion("charge_year =", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearNotEqualTo(Integer value) {
            addCriterion("charge_year <>", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearGreaterThan(Integer value) {
            addCriterion("charge_year >", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("charge_year >=", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearLessThan(Integer value) {
            addCriterion("charge_year <", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearLessThanOrEqualTo(Integer value) {
            addCriterion("charge_year <=", value, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearIn(List<Integer> values) {
            addCriterion("charge_year in", values, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearNotIn(List<Integer> values) {
            addCriterion("charge_year not in", values, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearBetween(Integer value1, Integer value2) {
            addCriterion("charge_year between", value1, value2, "chargeYear");
            return (Criteria) this;
        }

        public Criteria andChargeYearNotBetween(Integer value1, Integer value2) {
            addCriterion("charge_year not between", value1, value2, "chargeYear");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table boss_service_bill
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 19 10:09:58 CST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table boss_service_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
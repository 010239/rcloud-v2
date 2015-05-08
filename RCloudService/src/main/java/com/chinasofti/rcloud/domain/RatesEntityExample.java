package com.chinasofti.rcloud.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RatesEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public RatesEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
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
     * This method corresponds to the database table boss_rates
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
     * This method corresponds to the database table boss_rates
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_rates
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
     * This class corresponds to the database table boss_rates
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

        public Criteria andRateIdIsNull() {
            addCriterion("rate_id is null");
            return (Criteria) this;
        }

        public Criteria andRateIdIsNotNull() {
            addCriterion("rate_id is not null");
            return (Criteria) this;
        }

        public Criteria andRateIdEqualTo(String value) {
            addCriterion("rate_id =", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdNotEqualTo(String value) {
            addCriterion("rate_id <>", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdGreaterThan(String value) {
            addCriterion("rate_id >", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdGreaterThanOrEqualTo(String value) {
            addCriterion("rate_id >=", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdLessThan(String value) {
            addCriterion("rate_id <", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdLessThanOrEqualTo(String value) {
            addCriterion("rate_id <=", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdLike(String value) {
            addCriterion("rate_id like", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdNotLike(String value) {
            addCriterion("rate_id not like", value, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdIn(List<String> values) {
            addCriterion("rate_id in", values, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdNotIn(List<String> values) {
            addCriterion("rate_id not in", values, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdBetween(String value1, String value2) {
            addCriterion("rate_id between", value1, value2, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateIdNotBetween(String value1, String value2) {
            addCriterion("rate_id not between", value1, value2, "rateId");
            return (Criteria) this;
        }

        public Criteria andRateNameIsNull() {
            addCriterion("rate_name is null");
            return (Criteria) this;
        }

        public Criteria andRateNameIsNotNull() {
            addCriterion("rate_name is not null");
            return (Criteria) this;
        }

        public Criteria andRateNameEqualTo(String value) {
            addCriterion("rate_name =", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotEqualTo(String value) {
            addCriterion("rate_name <>", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameGreaterThan(String value) {
            addCriterion("rate_name >", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameGreaterThanOrEqualTo(String value) {
            addCriterion("rate_name >=", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLessThan(String value) {
            addCriterion("rate_name <", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLessThanOrEqualTo(String value) {
            addCriterion("rate_name <=", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameLike(String value) {
            addCriterion("rate_name like", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotLike(String value) {
            addCriterion("rate_name not like", value, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameIn(List<String> values) {
            addCriterion("rate_name in", values, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotIn(List<String> values) {
            addCriterion("rate_name not in", values, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameBetween(String value1, String value2) {
            addCriterion("rate_name between", value1, value2, "rateName");
            return (Criteria) this;
        }

        public Criteria andRateNameNotBetween(String value1, String value2) {
            addCriterion("rate_name not between", value1, value2, "rateName");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("service_id is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("service_id is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("service_id =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("service_id <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("service_id >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("service_id >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("service_id <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("service_id <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("service_id like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("service_id not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("service_id in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("service_id not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("service_id between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("service_id not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andMinimumIsNull() {
            addCriterion("minimum is null");
            return (Criteria) this;
        }

        public Criteria andMinimumIsNotNull() {
            addCriterion("minimum is not null");
            return (Criteria) this;
        }

        public Criteria andMinimumEqualTo(BigDecimal value) {
            addCriterion("minimum =", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotEqualTo(BigDecimal value) {
            addCriterion("minimum <>", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThan(BigDecimal value) {
            addCriterion("minimum >", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("minimum >=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThan(BigDecimal value) {
            addCriterion("minimum <", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("minimum <=", value, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumIn(List<BigDecimal> values) {
            addCriterion("minimum in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotIn(List<BigDecimal> values) {
            addCriterion("minimum not in", values, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minimum between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andMinimumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("minimum not between", value1, value2, "minimum");
            return (Criteria) this;
        }

        public Criteria andMaximumIsNull() {
            addCriterion("maximum is null");
            return (Criteria) this;
        }

        public Criteria andMaximumIsNotNull() {
            addCriterion("maximum is not null");
            return (Criteria) this;
        }

        public Criteria andMaximumEqualTo(BigDecimal value) {
            addCriterion("maximum =", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumNotEqualTo(BigDecimal value) {
            addCriterion("maximum <>", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumGreaterThan(BigDecimal value) {
            addCriterion("maximum >", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("maximum >=", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumLessThan(BigDecimal value) {
            addCriterion("maximum <", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("maximum <=", value, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumIn(List<BigDecimal> values) {
            addCriterion("maximum in", values, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumNotIn(List<BigDecimal> values) {
            addCriterion("maximum not in", values, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maximum between", value1, value2, "maximum");
            return (Criteria) this;
        }

        public Criteria andMaximumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("maximum not between", value1, value2, "maximum");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionIsNull() {
            addCriterion("rate_precision is null");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionIsNotNull() {
            addCriterion("rate_precision is not null");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionEqualTo(Integer value) {
            addCriterion("rate_precision =", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionNotEqualTo(Integer value) {
            addCriterion("rate_precision <>", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionGreaterThan(Integer value) {
            addCriterion("rate_precision >", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_precision >=", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionLessThan(Integer value) {
            addCriterion("rate_precision <", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionLessThanOrEqualTo(Integer value) {
            addCriterion("rate_precision <=", value, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionIn(List<Integer> values) {
            addCriterion("rate_precision in", values, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionNotIn(List<Integer> values) {
            addCriterion("rate_precision not in", values, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionBetween(Integer value1, Integer value2) {
            addCriterion("rate_precision between", value1, value2, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRatePrecisionNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_precision not between", value1, value2, "ratePrecision");
            return (Criteria) this;
        }

        public Criteria andRateRoundIsNull() {
            addCriterion("rate_round is null");
            return (Criteria) this;
        }

        public Criteria andRateRoundIsNotNull() {
            addCriterion("rate_round is not null");
            return (Criteria) this;
        }

        public Criteria andRateRoundEqualTo(Integer value) {
            addCriterion("rate_round =", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundNotEqualTo(Integer value) {
            addCriterion("rate_round <>", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundGreaterThan(Integer value) {
            addCriterion("rate_round >", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundGreaterThanOrEqualTo(Integer value) {
            addCriterion("rate_round >=", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundLessThan(Integer value) {
            addCriterion("rate_round <", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundLessThanOrEqualTo(Integer value) {
            addCriterion("rate_round <=", value, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundIn(List<Integer> values) {
            addCriterion("rate_round in", values, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundNotIn(List<Integer> values) {
            addCriterion("rate_round not in", values, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundBetween(Integer value1, Integer value2) {
            addCriterion("rate_round between", value1, value2, "rateRound");
            return (Criteria) this;
        }

        public Criteria andRateRoundNotBetween(Integer value1, Integer value2) {
            addCriterion("rate_round not between", value1, value2, "rateRound");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andRuleIsNull() {
            addCriterion("rule is null");
            return (Criteria) this;
        }

        public Criteria andRuleIsNotNull() {
            addCriterion("rule is not null");
            return (Criteria) this;
        }

        public Criteria andRuleEqualTo(Integer value) {
            addCriterion("rule =", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotEqualTo(Integer value) {
            addCriterion("rule <>", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThan(Integer value) {
            addCriterion("rule >", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule >=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThan(Integer value) {
            addCriterion("rule <", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleLessThanOrEqualTo(Integer value) {
            addCriterion("rule <=", value, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleIn(List<Integer> values) {
            addCriterion("rule in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotIn(List<Integer> values) {
            addCriterion("rule not in", values, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleBetween(Integer value1, Integer value2) {
            addCriterion("rule between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andRuleNotBetween(Integer value1, Integer value2) {
            addCriterion("rule not between", value1, value2, "rule");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNull() {
            addCriterion("modify_user is null");
            return (Criteria) this;
        }

        public Criteria andModifyUserIsNotNull() {
            addCriterion("modify_user is not null");
            return (Criteria) this;
        }

        public Criteria andModifyUserEqualTo(String value) {
            addCriterion("modify_user =", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotEqualTo(String value) {
            addCriterion("modify_user <>", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThan(String value) {
            addCriterion("modify_user >", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserGreaterThanOrEqualTo(String value) {
            addCriterion("modify_user >=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThan(String value) {
            addCriterion("modify_user <", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLessThanOrEqualTo(String value) {
            addCriterion("modify_user <=", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserLike(String value) {
            addCriterion("modify_user like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotLike(String value) {
            addCriterion("modify_user not like", value, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserIn(List<String> values) {
            addCriterion("modify_user in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotIn(List<String> values) {
            addCriterion("modify_user not in", values, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserBetween(String value1, String value2) {
            addCriterion("modify_user between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyUserNotBetween(String value1, String value2) {
            addCriterion("modify_user not between", value1, value2, "modifyUser");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table boss_rates
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
     * This class corresponds to the database table boss_rates
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
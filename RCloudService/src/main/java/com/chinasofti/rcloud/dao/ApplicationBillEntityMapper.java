package com.chinasofti.rcloud.dao;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ApplicationBillEntity;
import com.chinasofti.rcloud.domain.ApplicationBillEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ApplicationBillEntityMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int countByExample(ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByExample(ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByPrimaryKey(String billId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insert(ApplicationBillEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insertSelective(ApplicationBillEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ApplicationBillEntity> selectByExampleWithBLOBs(ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ApplicationBillEntity> selectByExample(ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    ApplicationBillEntity selectByPrimaryKey(String billId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleSelective(@Param("record") ApplicationBillEntity record, @Param("example") ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleWithBLOBs(@Param("record") ApplicationBillEntity record, @Param("example") ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExample(@Param("record") ApplicationBillEntity record, @Param("example") ApplicationBillEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeySelective(ApplicationBillEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(ApplicationBillEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table portal_application_bill
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKey(ApplicationBillEntity record);
}
package com.chinasofti.rcloud.dao;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ServiceEntity;
import com.chinasofti.rcloud.domain.ServiceEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceEntityMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int countByExample(ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByExample(ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByPrimaryKey(String serviceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insert(ServiceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insertSelective(ServiceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ServiceEntity> selectByExampleWithBLOBs(ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ServiceEntity> selectByExample(ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    ServiceEntity selectByPrimaryKey(String serviceId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleSelective(@Param("record") ServiceEntity record, @Param("example") ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleWithBLOBs(@Param("record") ServiceEntity record, @Param("example") ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExample(@Param("record") ServiceEntity record, @Param("example") ServiceEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeySelective(ServiceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(ServiceEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKey(ServiceEntity record);
}
package com.chinasofti.rcloud.dao;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ServiceRportalEntity;
import com.chinasofti.rcloud.domain.ServiceRportalEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ServiceRportalEntityMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int countByExample(ServiceRportalEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByExample(ServiceRportalEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insert(ServiceRportalEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insertSelective(ServiceRportalEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ServiceRportalEntity> selectByExample(ServiceRportalEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    ServiceRportalEntity selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleSelective(@Param("record") ServiceRportalEntity record, @Param("example") ServiceRportalEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExample(@Param("record") ServiceRportalEntity record, @Param("example") ServiceRportalEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeySelective(ServiceRportalEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_service_rportal
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKey(ServiceRportalEntity record);
}
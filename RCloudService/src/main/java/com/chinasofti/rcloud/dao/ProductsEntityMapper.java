package com.chinasofti.rcloud.dao;

import com.chinasofti.rcloud.common.dao.BaseDao;
import com.chinasofti.rcloud.domain.ProductsEntity;
import com.chinasofti.rcloud.domain.ProductsEntityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductsEntityMapper extends BaseDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int countByExample(ProductsEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByExample(ProductsEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int deleteByPrimaryKey(String prodId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insert(ProductsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int insertSelective(ProductsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    List<ProductsEntity> selectByExample(ProductsEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    ProductsEntity selectByPrimaryKey(String prodId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExampleSelective(@Param("record") ProductsEntity record, @Param("example") ProductsEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByExample(@Param("record") ProductsEntity record, @Param("example") ProductsEntityExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKeySelective(ProductsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table boss_products
     *
     * @mbggenerated Mon Jan 19 10:09:58 CST 2015
     */
    int updateByPrimaryKey(ProductsEntity record);
}
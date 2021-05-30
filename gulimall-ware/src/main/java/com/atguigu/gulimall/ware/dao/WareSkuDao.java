package com.atguigu.gulimall.ware.dao;

import com.atguigu.gulimall.ware.entity.WareSkuEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品库存
 * 
 * @author qichunhui
 * @email sunlightcs@gmail.com
 * @date 2021-04-04 17:01:18
 */
@Mapper
public interface WareSkuDao extends BaseMapper<WareSkuEntity> {
	
}

package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author qichunhui
 * @email sunlightcs@gmail.com
 * @date 2021-04-04 16:47:22
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}

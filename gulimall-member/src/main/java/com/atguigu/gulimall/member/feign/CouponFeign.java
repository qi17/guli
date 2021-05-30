package com.atguigu.gulimall.member.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author 齐春晖
 * @date Created in 16:22 2021/5/2
 * @description
 */
@FeignClient("gulimall-coupon")
public interface CouponFeign {
    @RequestMapping("coupon/coupon/get/coupon")
    public  R getCoupon();

}

package com.atguigu.gulimall.product.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
            new Query<CategoryEntity>().getPage(params),
            new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> lisWithTree() {
        //获取所有分类的集合
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);
        //查找所有的父分类(parentCid=0)
        List<CategoryEntity> collect = categoryEntityList.stream()
            .filter((e) ->
                        e.getParentCid() == 0)
            .map(e -> {
                e.setChildren(getChildren(e, categoryEntityList));
                return e;
            })
            .sorted((m1, m2) -> {
                return (m1.getSort()==null?0:m1.getSort())- (m2.getSort()==null?0:m2.getSort());
            })
            .collect(Collectors.toList());

        return collect;
    }

    /**
     * 返回所有的子菜单
     */
    private List<CategoryEntity> getChildren(CategoryEntity root, List<CategoryEntity> resultList) {
        List<CategoryEntity> collect = resultList.stream()
            .filter(categoryEntity ->
                //从所有分类的集合的父id中过滤出所有与当前分类id相同的，说明其就是我们当前的父id下的子id
                        categoryEntity.getParentCid() == root.getCatId())
            .map(categoryEntity -> {
                categoryEntity.setChildren(getChildren(categoryEntity, resultList));
                return categoryEntity;
            })
            .sorted((m1, m2) -> {
                return (m1.getSort()==null?0:m1.getSort())- (m2.getSort()==null?0:m2.getSort());
            })
            .collect(Collectors.toList());
        return collect;

    }
}


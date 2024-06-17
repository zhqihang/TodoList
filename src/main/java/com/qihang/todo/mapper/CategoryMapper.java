package com.qihang.todo.mapper;

import com.qihang.todo.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/08
 * @Project: TodoList
 * @Description: 清单 Mapper接口
 */

@Mapper
public interface CategoryMapper {

    /**
     * 查询所有的清单
     */
    List<Category> getAllCategory();

    /**
     * 添加一个清单分类
     * */
    void addCategory(Category category);

    /**
     * 根据id删除一个清单分类
     * */
    void deleteCategoryById(int id);

    /**
     * 对清单分类的名称进行修改
     * */
    void updateCategory(Category category);

}

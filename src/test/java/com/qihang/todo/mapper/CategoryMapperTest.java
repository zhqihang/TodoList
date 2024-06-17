package com.qihang.todo.mapper;

import com.qihang.todo.entity.Category;
import com.qihang.todo.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/08
 * @Project: TodoList
 * @Description: ...
 */

@SpringBootTest
class CategoryMapperTest {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    /**
     * 查询所有清单
     */
    @Test
    void getAllCategory(){
        List<Category> allCategory = categoryMapper.getAllCategory();
        allCategory.stream().forEach(System.out::println);
    }

    /**
     * 新增清单
     */
    @Test
    void addCategory() {
        Category category = new Category();
        category.setId(2);
        category.setName("论文写作");
        category.setCreateTime(LocalDateTime.now());
        categoryMapper.addCategory(category);
    }

    /**
     * 删除清单
     */
    @Test
    void deleteCategoryById() {
        categoryMapper.deleteCategoryById(87);
    }

    /**
     * 更新清单
     */
    @Test
    void updateCategory() {
        Category category = new Category();
        category.setId(2);
        category.setName("SpringBoot学习");
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateCategory(category);
    }
}

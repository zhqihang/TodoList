package com.qihang.todo.controller;

import com.qihang.todo.entity.Category;
import com.qihang.todo.entity.CategoryExample;
import com.qihang.todo.entity.Task;
import com.qihang.todo.entity.TaskExample;
import com.qihang.todo.mapper.CategoryMapper;
import com.qihang.todo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/21
 * @Project: TodoList
 * @Description: 基本控制层
 */

@RestController
public class CommonController {

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TaskMapper taskMapper;

    @RequestMapping("/")
    public String index(Model model) {
        // 获取所有分类
        List<Category> categories = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        categories = categoryMapper.selectByExample(categoryExample);
        model.addAttribute("categoryLists", categories);

        // 找到今天的分类
        CategoryExample.Criteria exampleCriteria = categoryExample.createCriteria();
        exampleCriteria.andNameEqualTo("今天");
        List<Category> categories1 = categoryMapper.selectByExample(categoryExample);
        List<Integer> todayCategoryID = new ArrayList<>();
        for (Category category : categories1) {
            todayCategoryID.add(category.getId());
        }

        // 默认显示今天这个分类下的清单
        List<Task> taskListForToday = new ArrayList<>();
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        if (todayCategoryID.size() > 0) {
            criteria.andCategoryIdIn(todayCategoryID);
            taskListForToday = taskMapper.selectByExample(taskExample);
        } else {
            taskListForToday = null;
        }
        model.addAttribute("taskList", taskListForToday);
        return "index";
    }
}

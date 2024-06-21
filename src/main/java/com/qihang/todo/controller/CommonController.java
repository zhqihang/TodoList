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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/21
 * @Project: TodoList
 * @Description: 基本控制层
 */

@Controller
public class CommonController {
    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    TaskMapper taskMapper;

    @GetMapping("/category/{cid}")
    public String getTaskByCategoryid(@PathVariable(value = "cid", required = true) int id, Model model,
                                      HttpSession session) {
        if (session.getAttribute("categoryLists") == null) {
            session.setAttribute("categoryLists", getCategoryByIdOrName(null, null));
        }
        // 显示这个分类下的清单
        List<Task> taskListByCId = null;
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        if (id != 0) {
            criteria.andCategoryIdEqualTo(id);
            criteria.andRunEqualTo((byte)0);
            taskListByCId = taskMapper.selectByExample(taskExample);
            model.addAttribute("currentCategory", getCategoryByIdOrName(id, null).get(0));
        }
        model.addAttribute("taskList", taskListByCId);
        return "index";
    }

    @RequestMapping("/")
    public String index(Model model, HttpServletRequest request) {
        // 得到所有的分类
        List<Category> categoryLists = getCategoryByIdOrName(null, null);
        HttpSession session = request.getSession();
        session.setAttribute("categoryLists", categoryLists);
        List<Category> todayLists = getCategoryByIdOrName(null, "今天");
        List<Integer> todayCategoryID = new ArrayList<>();
        for (Category category : todayLists) {
            todayCategoryID.add(category.getId());
        }

        // 默认是显示今天这个分类下的清单
        List<Task> taskListForToday = new ArrayList<>();
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        if (todayCategoryID.size() > 0) {
            criteria.andCategoryIdIn(todayCategoryID);
            criteria.andRunEqualTo((byte)0);
            taskListForToday = taskMapper.selectByExample(taskExample);
        } else {
            taskListForToday = null;
        }

        Category category = todayLists.get(0);
        model.addAttribute("currentCategory", category);
        model.addAttribute("taskList", taskListForToday);
        return "index";
    }

    private List<Category> getCategoryByIdOrName(Integer id, String name) {
        List<Category> categoryLists = new ArrayList<>();
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        if (name != null && name != "") {
            criteria.andNameEqualTo(name);
        }
        if (id != null && id != 0) {
            criteria.andIdEqualTo(id);
        }
        categoryLists = categoryMapper.selectByExample(categoryExample);
        return categoryLists;
    }
}
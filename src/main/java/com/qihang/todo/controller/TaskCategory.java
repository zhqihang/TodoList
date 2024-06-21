package com.qihang.todo.controller;

import com.qihang.todo.common.api.Result;
import com.qihang.todo.entity.Task;
import com.qihang.todo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhqihang
 * @Date: 2024/06/21
 * @Project: TodoList
 * @Description: ...
 */
@Controller
@RequestMapping("/task")
public class TaskCategory {
    @Autowired
    TaskMapper taskMapper;

    @ResponseBody
    @PutMapping({"/update", "/updateTask"})
    public Result updateTask(Integer id, String name, String description) {
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        int i = taskMapper.updateByPrimaryKeySelective(task);
        return i != 0 ? Result.success(null) : Result.failed();
    }

    @ResponseBody
    @DeleteMapping("/del/{id}")
    public HashMap<String, Object> delTaskById(@PathVariable("id") int id) {
        Task record = new Task();
        record.setId(id);
        record.setRun((byte)1);
        int i = taskMapper.updateByPrimaryKeySelective(record);
        String res = i != 0 ? "删除成功" : "删除失败";
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", res);
        return map;
    }

    @ResponseBody
    @PostMapping("/add")
    public HashMap<String, Object> addTask(@RequestParam Map<String, Object> params) {
        Task task = new Task();
        task.setCreateTime(LocalDateTime.now());
        task.setRun((byte)0);
        String categoryId = params.get("categoryId").toString();
        task.setCategoryId(Integer.valueOf(categoryId));
        task.setName((String)params.get("name"));
        task.setDescription((String)params.get("description"));
        int i = taskMapper.insertSelective(task);
        String res = i != 0 ? "添加成功" : "添加失败";
        HashMap<String, Object> map = new HashMap<>();
        map.put("data", res);
        return map;
    }

}
package com.qihang.todo.controller;

import com.qihang.todo.common.api.Result;
import com.qihang.todo.entity.Category;
import com.qihang.todo.entity.TaskExample;
import com.qihang.todo.mapper.CategoryMapper;
import com.qihang.todo.mapper.TaskMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/08
 * @Project: TodoList
 * @Description: 清单控制层
 *
 */
@Api(tags = "清单信息管理")
@RequestMapping("/category")
@RestController
public class CategoryController {
    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TaskMapper taskMapper;

    @ApiOperation("查询所有的清单")
    @GetMapping("/getAllCategory")
    Result<List<Category>> getAllCategory() {
        List<Category> categories = categoryMapper.selectByExample(null);
        return Result.success(categories);
    }

    @PostMapping("/add")
    @ApiOperation("添加一个清单分类")
    @ApiResponse(code = 1, message = "添加成功")
    @ApiImplicitParam(name = "category", required = true, paramType = "query")
    Result addCategory(@ApiParam("添加的清单") @RequestBody Category category) {
        int i = categoryMapper.insertSelective(category);
        return i != 0 ? Result.success(null) : Result.failed();
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("根据id删除一个清单分类")
    @ApiImplicitParam(name = "id", value = "清单ID", required = true, paramType = "path")
    @ApiResponses({@ApiResponse(code = 0, message = "删除记录不存在"), @ApiResponse(code = 1, message = "删除成功"),})
    Result deleteCategoryById(@PathVariable("id") int id, HttpSession session) {
        // 删除分类下的所有任务
        TaskExample taskExample = new TaskExample();
        TaskExample.Criteria criteria = taskExample.createCriteria();
        criteria.andCategoryIdEqualTo(id);
        taskMapper.deleteByExample(taskExample);
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i != 0) {
            session.setAttribute("categoryLists", null);
        }
        return i != 0 ? Result.success(null) : Result.failed();
    }

    @PutMapping("/updateCategory")
    @ApiOperation("更新清单")
    @ApiResponse(code = 1, message = "修改成功")
    @ApiImplicitParam(name = "category", value = "清单对象", required = true, paramType = "query")
    Result updateCategory(@RequestBody Category category) {
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        return i != 0 ? Result.success(null) : Result.failed();
    }
}
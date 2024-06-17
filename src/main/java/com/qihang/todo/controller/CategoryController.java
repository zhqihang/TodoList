package com.qihang.todo.controller;

import com.qihang.todo.entity.Category;
import com.qihang.todo.mapper.CategoryMapper;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhqihang
 * @Date: 2024/06/08
 * @Project: TodoList
 * @Description: 清单控制层
 *
 */
@Api(tags = "清单管理")
@RequestMapping("/category")
@RestController
public class CategoryController {

    @Autowired(required = false)
    private CategoryMapper categoryMapper;

    @ApiOperation("查询所有清单")
    @GetMapping("/getAllCategory")
    List<Category> getAllCategory(){
        List<Category> categories = categoryMapper.selectByExample(null);
        return categories;
    }

    @ApiOperation("添加一个清单分类")
    @PostMapping("/addCategory")
    @ApiResponse(code = 1, message = "添加成功")
    @ApiImplicitParam(name = "category", required = true, paramType = "query")
    int addCategory(Category category){
        return categoryMapper.insertSelective(category);
    }

    @ApiOperation("根据id删除清单分类")
    @DeleteMapping("/deleteCategoryById/{id}")
    @ApiImplicitParam(name = "id", value = "清单ID", required = true, paramType = "path")
    @ApiResponses({
            @ApiResponse(code = 0, message = "删除失败"),
            @ApiResponse(code = 1, message = "删除成功")
    })
    int deleteCategoryById(int id){
        return categoryMapper.deleteByPrimaryKey(id);
    }


    @ApiOperation("更新清单")
    @PutMapping("/updateCategory")
    @ApiResponse(code = 1, message = "修改成功")
    @ApiImplicitParam(name = "category", value = "清单对象", required = true, paramType = "query")
    int updateCategory(Category category){
        return categoryMapper.updateByPrimaryKeySelective(category);
    }

}

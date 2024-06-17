package com.qihang.todo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author: zhqihang
 * @Date: 2024/06/08
 * @Project: TodoList
 * @Description: 清单实体类
 */

@Data // lombok 注解
// 构造函数
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private int id;
    private int userId;
    private String name; //清单名称
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private int run;

}

package com.qihang.todo.mapper;

import com.qihang.todo.entity.TaskLabel;
import com.qihang.todo.entity.TaskLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskLabelMapper {
    long countByExample(TaskLabelExample example);

    int deleteByExample(TaskLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskLabel record);

    int insertSelective(TaskLabel record);

    List<TaskLabel> selectByExample(TaskLabelExample example);

    TaskLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskLabel record, @Param("example") TaskLabelExample example);

    int updateByExample(@Param("record") TaskLabel record, @Param("example") TaskLabelExample example);

    int updateByPrimaryKeySelective(TaskLabel record);

    int updateByPrimaryKey(TaskLabel record);
}
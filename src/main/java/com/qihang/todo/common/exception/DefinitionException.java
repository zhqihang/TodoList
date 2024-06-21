package com.qihang.todo.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class  DefinitionException  extends RuntimeException{
    private Integer errorCode;
    private String errorMessage;
}

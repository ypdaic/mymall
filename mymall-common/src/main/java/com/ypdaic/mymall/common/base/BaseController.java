package com.ypdaic.mymall.common.base;

import com.ypdaic.mymall.common.enums.FailureEnum;
import com.ypdaic.mymall.common.result.Result;
import com.ypdaic.mymall.common.result.ResultUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class BaseController {

    /**
     * 返回参数错误
     *
     * @param result
     * @return
     */
    protected Result paramError(BindingResult result) {
        StringBuffer sb = new StringBuffer();
        for (ObjectError error : result.getAllErrors()) {
            sb.append(error.getDefaultMessage());
        }
        return ResultUtil.failure(FailureEnum.PARAMETER_FAILURE, sb.toString());
    }

}

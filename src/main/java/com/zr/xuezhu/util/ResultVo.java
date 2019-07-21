package com.zr.xuezhu.util;

import lombok.Data;
import lombok.extern.java.Log;

@Data
@Log
public class ResultVo<T> {
    private Boolean success;
    //是否已经确认通过，是否审核通过，如果确认通过直接跳转到最终的页面。如果审核通过跳转到签约页面，
    private Integer isShenHe;
    private String errorMessage;
    private T data;
    private Boolean loginStatus;

    public static<T> ResultVo success(T data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setData(data);
        return resultVo;
    }

    public static<T> ResultVo success() {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(true);
        resultVo.setLoginStatus(true);
        return resultVo;
    }

    public static ResultVo error(String errorMessage) {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setErrorMessage(errorMessage);
        return resultVo;
    }
    public static ResultVo error() {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        return resultVo;
    }

    public static ResultVo notLogin(String errorMessage) {
        ResultVo resultVo = new ResultVo();
        resultVo.setSuccess(false);
        resultVo.setLoginStatus(false);
        resultVo.setErrorMessage(errorMessage);
        return resultVo;
    }
}

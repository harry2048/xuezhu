package com.zr.xuezhu.paydivide.service;

import com.zr.xuezhu.paydivide.pojo.PayDivideShowVo;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZLFamily on 2019/7/3.
 */
public interface PayDivideService {


    ResultVo<PayDivideShowVo> queryPage(HttpServletRequest req);

    ResultVo xueZhuFenQiAdd(PayDivideShowVo payDivideShowVo, HttpServletRequest req);
}

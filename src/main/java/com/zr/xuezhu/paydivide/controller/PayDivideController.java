package com.zr.xuezhu.paydivide.controller;

import com.zr.xuezhu.paydivide.pojo.PayDivideShowVo;
import com.zr.xuezhu.paydivide.service.PayDivideService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 雪竹分期接口
 */
@RestController
@CrossOrigin
public class PayDivideController {
    @Autowired
    private PayDivideService payDivideService;

    /**
     * 雪竹分期详情页面展示
     */
    @GetMapping("/xueZhuFenQi")
    public ResultVo<PayDivideShowVo>queryPage(HttpServletRequest req){
        return payDivideService.queryPage(req);
    }


    /**
     * 个人分期信息入库
     * @param payDivideShowVo
     * @param req
     * @return
     */
    @PostMapping("/xueZhuFenQiAdd")
    public ResultVo xueZhuFenQiAdd(@RequestBody @Valid PayDivideShowVo payDivideShowVo, BindingResult bindingResult, HttpServletRequest req) {
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }

        return payDivideService.xueZhuFenQiAdd(payDivideShowVo,req);
    }


}

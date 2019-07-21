package com.zr.xuezhu.paymentinformation.controller;

import com.zr.xuezhu.paymentinformation.pojo.PaymentInformationAddVo;
import com.zr.xuezhu.paymentinformation.service.PaymentInformationService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 添加缴费信息接口
 * Created by 王杰 on 2019/7/3.
 */
@RestController
@CrossOrigin
public class PaymentInformationController {
    @Autowired
    private PaymentInformationService paymentInformationService;

    /**
     * 添加或修改分期缴费申请信息
     * @param paymentInformationAddVo
     * @param request
     * @param bindingResult
     * @return
     */
    @PostMapping("/installmentApply")
    public ResultVo installmentApply(@RequestBody @Valid PaymentInformationAddVo paymentInformationAddVo, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return paymentInformationService.installmentApply(paymentInformationAddVo,request);
    }

    /**
     * 课程下拉框
     * @return
     */
    @GetMapping("/kechengDropdownBox")
    public ResultVo kechengDropdownBox(){
        //选择课程，提供下拉内容，查询所有
        return paymentInformationService.kechengDropdownBox();
    }

    /**
     * 还款周期下拉框
     * @return
     */
    @GetMapping("/huankuanDropdownBox")
    public ResultVo huankuanDropdownBox(){
        //选择还款周期，提供下拉内容，查询所有
        return paymentInformationService.huankuanDropdownBox();
    }

    /**
     * 获取数据缓冲中的上一页的数据
     * @return
     */
    @GetMapping("/getFqsq")
    public ResultVo getFqsq(HttpServletRequest request){
        return paymentInformationService.getFqsq(request);
    }
}

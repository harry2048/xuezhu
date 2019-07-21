package com.zr.xuezhu.userrepay.controller;

import com.zr.xuezhu.userrepay.meiju.StatusEnum;
import com.zr.xuezhu.userrepay.pojo.UserRepayAddVo;
import com.zr.xuezhu.userrepay.pojo.XiaLaVo;
import com.zr.xuezhu.userrepay.service.UserRepayService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
还款银行登记
 */
@RestController
@CrossOrigin
public class UserRepayController {
    @Autowired
    private UserRepayService userRepayService;

    /**
     * 开户银行下拉框
     */
    @GetMapping("/kaihuhangDropdownBox")
    public ResultVo<List<XiaLaVo>> queryXiaLa(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        for (int i = 0; i < StatusEnum.values().length; i++) {
            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setKey(i);
            xiaLaVo.setValue(StatusEnum.getName(i));
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }

    /**
     * 还款银行登记
     * @param userRepayAddVo
     * @param bindingResult
     * @return
     */
    @PostMapping("/repaymentBank")
    public ResultVo addUserRepay(@RequestBody @Valid UserRepayAddVo userRepayAddVo, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return userRepayService.addUserRepay(userRepayAddVo,request);
    }
    /**
     * 获取数据缓冲中的上一页的数据
     * @return
     */
    @GetMapping("/huankuan_dengji")
    public ResultVo getHuankuan_dengji(HttpServletRequest request){
        return userRepayService.getHuankuan_dengji(request);
    }
}

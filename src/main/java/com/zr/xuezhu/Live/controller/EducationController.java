package com.zr.xuezhu.Live.controller;

import com.zr.xuezhu.Live.bean.EducationAddVo;
import com.zr.xuezhu.Live.bean.JuZhuAddVo;
import com.zr.xuezhu.Live.bean.XiaLaVo;
import com.zr.xuezhu.Live.enumall.XueLiEnum;
import com.zr.xuezhu.Live.enumall.ZhiYeEnum;
import com.zr.xuezhu.Live.service.EducationService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/***
 * 教育情况接口
 */
@RestController
@CrossOrigin
public class EducationController {

    @Autowired
    private EducationService educationService;
    /**
     * 教育情况接口
     */
    @PostMapping("/education")
    public ResultVo add(@RequestBody @Valid EducationAddVo educationAddVo, BindingResult bindingResult,HttpServletRequest request){
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return educationService.add(educationAddVo,request);
    }
    /**
     * 职业下拉框
     */
    @GetMapping("/zhiyeDropdownBox")
    public ResultVo<List<XiaLaVo>> queryZhiYe(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        ZhiYeEnum[] arr = ZhiYeEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(arr[i].getOccupationValue());
            xiaLaVo.setName(arr[i].getOccupationName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }
    /**
     * 学历下拉框
     */
    @GetMapping("/xueliDropdownBox")
    public ResultVo<List<XiaLaVo>> queryXueLi(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        XueLiEnum[] arr = XueLiEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(arr[i].getEducationValue());
            xiaLaVo.setName(arr[i].getEducationName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }

    /**
     * 获取数据缓冲中的上一页的数据
     * @return
     */
    @GetMapping("/getEducation")
    public ResultVo getEducation(HttpServletRequest request){
        return educationService.getEducation(request);
    }
}

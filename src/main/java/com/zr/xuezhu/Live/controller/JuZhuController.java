package com.zr.xuezhu.Live.controller;

import com.zr.xuezhu.Live.bean.JuZhuAddVo;
import com.zr.xuezhu.Live.bean.XiaLaVo;
import com.zr.xuezhu.Live.enumall.HuiYinEnum;
import com.zr.xuezhu.Live.enumall.JuZhuEnum;
import com.zr.xuezhu.Live.enumall.NianXianEnum;
import com.zr.xuezhu.Live.service.JuZhuService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 居住信息接口
 */
@RestController
@CrossOrigin
public class JuZhuController {

    @Autowired
    private JuZhuService juZhuService;
       /**
     * 居住信息添加主接口
     */
    @PostMapping("userLive")
    public ResultVo add(@RequestBody @Valid JuZhuAddVo juZhuAddVo, BindingResult bindingResult,HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return juZhuService.add(juZhuAddVo,request);
    }


    /**
     * 婚姻状态下拉框
     */
    @GetMapping("/hunyinDropdownBox")
    public ResultVo<List<XiaLaVo>> queryHuiYin(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        HuiYinEnum[] arr = HuiYinEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(arr[i].getMaritalStatusValue());
            xiaLaVo.setName(arr[i].getMaritalStatusName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }
    /**
     * 居住状态下拉框
     */
    @GetMapping("/juzhuDropdownBox")
    public ResultVo<List<XiaLaVo>> queryJuZhu(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        JuZhuEnum[] arr = JuZhuEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo  xiaLaVo= new XiaLaVo();
            xiaLaVo.setId(arr[i].getLiveStatusValue());
            xiaLaVo.setName(arr[i].getLiveStatusName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }
    /**
     * 年限下拉框
     */
    @GetMapping("/nianxianDropdownBox")
    public ResultVo<List<XiaLaVo>> queryNianXian(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        NianXianEnum[] arr = NianXianEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(arr[i].getLiveYearValue());
            xiaLaVo.setName(arr[i].getLiveYearName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }


    /**
     * 获取数据缓冲中的上一页的数据
     * @return
     */
    @GetMapping("/getLive")
    public ResultVo getLive(HttpServletRequest request){
        return juZhuService.getLive(request);
    }
}

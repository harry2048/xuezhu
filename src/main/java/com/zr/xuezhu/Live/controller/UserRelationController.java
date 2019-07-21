package com.zr.xuezhu.Live.controller;

import com.zr.xuezhu.Live.bean.UserRelationAddVo;
import com.zr.xuezhu.Live.bean.XiaLaVo;
import com.zr.xuezhu.Live.enumall.RelationEnum;
import com.zr.xuezhu.Live.service.UserRelationService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 联系人接口
 */
@RestController
@CrossOrigin
public class UserRelationController {

    @Autowired
    private UserRelationService userRelationService;

    /**
     * 联系人信息主接口
     */
    @PostMapping("/contacts")
    public ResultVo add(@RequestBody @Valid UserRelationAddVo userRelationAddVo, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return ResultVo.error(bindingResult.getFieldError().getDefaultMessage());
        }
        return userRelationService.add(userRelationAddVo,request);
    }
    /**
     * 和我的关系下拉框
     */
    @GetMapping("/withguanxiDropdownBox")
    public ResultVo<List<XiaLaVo>> queryRelation(){
        List<XiaLaVo> xiaLaVoList = new ArrayList<>();
        RelationEnum[] arr = RelationEnum.values();
        for (int i = 0; i < arr.length; i++){

            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(arr[i].getRelationsWithMeValue());
            xiaLaVo.setName(arr[i].getRelationsWithMeName());
            xiaLaVoList.add(xiaLaVo);
        }
        return ResultVo.success(xiaLaVoList);
    }

    /**
     * 获取数据缓冲中的上一页的数据
     * @return
     */
    @GetMapping("/getContacts")
    public ResultVo getContacts(HttpServletRequest request){
        return userRelationService.getContacts(request);
    }
}

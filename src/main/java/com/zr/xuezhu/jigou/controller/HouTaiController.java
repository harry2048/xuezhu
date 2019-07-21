package com.zr.xuezhu.jigou.controller;

import com.zr.xuezhu.jigou.pojo.HouTaiSelectVo;
import com.zr.xuezhu.jigou.service.HouTaiService;
import com.zr.xuezhu.jigou.util.AllRecords;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 后台机构接口
 */
@CrossOrigin
@RestController
public class HouTaiController {

    @Autowired
    private HouTaiService houTaiService;
    /**
     * 分页查询接口
     */
    @PostMapping("/queryHouTaiAll")
    public ResultVo<AllRecords> queryHouTaiAll(@RequestBody HouTaiSelectVo houTaiSelectVo){

        return houTaiService.queryHouTaiAll(houTaiSelectVo);
    }
    /**
     * 根据id查看详情接口
     */
    @GetMapping("/queryHouTaiById")
    public ResultVo queryHouTaiById(@RequestParam("id") Integer id){

        return houTaiService.queryHouTaiById(id);
    }
    /**
     * 根据id查看修改状态为确认状态
     */
    @GetMapping("/updateSureById")
    public ResultVo updateSureById(@RequestParam("id") Integer id, HttpServletRequest request){

        return houTaiService.updateSureById(id,request);
    }
    /**
     * 根据id查看修改状态为取消状态
     */
    @GetMapping("/updateQuXiaoById")
    public ResultVo updateQuXiaoById(@RequestParam("id") Integer id, HttpServletRequest request){

        return houTaiService.updateQuXiaoById(id,request);
    }
}

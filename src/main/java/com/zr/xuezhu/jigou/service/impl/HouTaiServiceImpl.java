package com.zr.xuezhu.jigou.service.impl;

import com.zr.xuezhu.jigou.mapper.HouTaiMapper;
import com.zr.xuezhu.jigou.pojo.HouTaiSelectVo;
import com.zr.xuezhu.jigou.service.HouTaiService;
import com.zr.xuezhu.jigou.util.AllRecords;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import com.zr.xuezhu.util.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Wjerry on 2019/7/9.
 */
@Service
public class HouTaiServiceImpl implements HouTaiService {

    @Autowired
    private HouTaiMapper houTaiMapper;
    @Override
    public ResultVo<AllRecords> queryHouTaiAll(HouTaiSelectVo houTaiSelectVo) {
        //查询数据
        List<PayDivide> houTaisList = houTaiMapper.queryPage(houTaiSelectVo);
        for (PayDivide payDivide:houTaisList) {
            payDivide.setStatusName(StatusEnum.getName(payDivide.getStatus()));
        }
        //查询数量
        int count =  houTaiMapper.queryCount(houTaiSelectVo);
        //把当前页、每页大小、总页数、总条数、数据统一放入到AllRecords返回
        AllRecords allRecords = new AllRecords();
        allRecords.setPageIndex(houTaiSelectVo.getPageIndex());
        allRecords.setPageSize(houTaiSelectVo.getPageSize());
        allRecords.setTotalNumber(count);
        allRecords.resetTotalNumber(count);
        allRecords.setDataList(houTaisList);
        return ResultVo.success(allRecords);
    }

    @Override
    public ResultVo queryHouTaiById(Integer id) {

        PayDivide payDivide = houTaiMapper.queryById(id);
        if (payDivide==null){
            return ResultVo.error(ConsUtil.NOTEXIST);
        }
        payDivide.setStatusName(StatusEnum.getName(payDivide.getStatus()));
        return ResultVo.success(payDivide);
    }

    @Override
    @Transactional
    public ResultVo updateSureById(Integer id, HttpServletRequest request) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        PayDivide payDivideById = houTaiMapper.queryById(id);
        if(payDivideById==null) {
            return ResultVo.error(ConsUtil.NOTEXIST);
        }

        //给数据库中的创建时间和修改时间等信息赋值
        PayDivide payDivide = new PayDivide();
        Date nowDate = new Date();
        payDivide.setId(id);
        payDivide.setUpdateTime(nowDate);
        payDivide.setUpdateName(userLogin.getUpdateName());
        //把左边对象的数据赋值给右边对象的数据
        //注意必须相同参数名称和相同参数类型才能自动进行赋值
        houTaiMapper.updateStatus(payDivide);
        return ResultVo.success("已确认");
    }

    @Override
    public ResultVo updateQuXiaoById(Integer id, HttpServletRequest request) {
        UserLogin userLogin = (UserLogin) request.getSession().getAttribute(ConsUtil.USER);
        PayDivide payDivideById = houTaiMapper.queryById(id);
        if(payDivideById==null) {
            return ResultVo.error(ConsUtil.NOTEXIST);
        }
        //给数据库中的创建时间和修改时间等信息赋值
        PayDivide payDivide = new PayDivide();
        Date nowDate = new Date();
        payDivide.setId(id);
        payDivide.setUpdateTime(nowDate);
        payDivide.setUpdateName(userLogin.getUpdateName());
        //把左边对象的数据赋值给右边对象的数据
        //注意必须相同参数名称和相同参数类型才能自动进行赋值
        houTaiMapper.updateStatus2(payDivide);
        return ResultVo.success("已取消");
    }

}

package com.zr.xuezhu.jigou.service;

import com.zr.xuezhu.jigou.pojo.HouTaiSelectVo;
import com.zr.xuezhu.jigou.util.AllRecords;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wjerry on 2019/7/9.
 * Created by henghengjiji on 2019/7/9.

 */
public interface HouTaiService {
    ResultVo<AllRecords> queryHouTaiAll(HouTaiSelectVo houTaiSelectVo);

    ResultVo queryHouTaiById(Integer id);

    ResultVo updateSureById(Integer id, HttpServletRequest request);

    ResultVo updateQuXiaoById(Integer id, HttpServletRequest request);
}

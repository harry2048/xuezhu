package com.zr.xuezhu.Live.service.ImpI;

import com.zr.xuezhu.Live.bean.Education;
import com.zr.xuezhu.Live.bean.EducationAddVo;
import com.zr.xuezhu.Live.bean.EducationVo;
import com.zr.xuezhu.Live.enumall.XueLiEnum;
import com.zr.xuezhu.Live.enumall.ZhiYeEnum;
import com.zr.xuezhu.Live.mapper.EducationMapper;
import com.zr.xuezhu.Live.service.EducationService;
import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
@Service
public class EducationServiceImpI implements EducationService {
    @Autowired
    private EducationMapper educationMapper;
    @Autowired
    private PayDivideMapper payDivideMapper;

    @Override
    @Transactional
    public ResultVo add(EducationAddVo educationAddVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //逻辑验证：uid验证
        List<Education> educationList = educationMapper.queryById(userLogin.getId());
        Education education = copyProperties(educationAddVo,userLogin);
        if(educationList.isEmpty()){
            educationMapper.add(education);
        }else if (educationList.size() == 1){
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                educationMapper.updateEduByLoginId(education);
            }else{
                if (payDivide.getStatus()== 0){
                    educationMapper.updateEduByLoginId(education);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    educationMapper.updateEduByLoginId(education);
                    payDivideMapper.updateStatus(userLogin.getId());
                }else{
                    return ResultVo.error(ConsUtil.NOTEXIST);
                }
            }
        }else{
            return ResultVo.error(ConsUtil.USEREXITED);
        }
        /*if (juZhuList.size()>1){
            return ResultVo.error(ConsUtil.USEREXITED);
        } else if (juZhuList.size() == 1) {
            // 修改
            educationMapper.updateEduByLoginId(education);
        } else {
            // 添加
            educationMapper.add(education);
        }*/
        session.setAttribute(ConsUtil.EDUCATIONADDVO,educationAddVo);
        return ResultVo.success(educationAddVo);
    }

    @Override
    public ResultVo getEducation(HttpServletRequest request) {

        EducationAddVo educationAddVo = (EducationAddVo) request.getSession().getAttribute(ConsUtil.EDUCATIONADDVO);
        if (educationAddVo != null) {
            return ResultVo.success(educationAddVo);
        }
        return ResultVo.error();
    }

    private Education copyProperties(EducationAddVo educationAddVo, UserLogin userLogin) {
        Education education = new Education();
        BeanUtils.copyProperties(educationAddVo,education);
        Date nowDate = new Date();
        education.setUid(userLogin.getId());
        education.setCreateTime(nowDate);
        education.setCreateName(userLogin.getUserName());
        education.setUpdateTime(nowDate);
        education.setUpdateName(userLogin.getUserName());
        return null;
    }
}

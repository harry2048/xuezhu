package com.zr.xuezhu.Live.service.ImpI;

import com.zr.xuezhu.Live.bean.JuZhuAddVo;
import com.zr.xuezhu.Live.bean.UserRelation;
import com.zr.xuezhu.Live.bean.UserRelationAddVo;
import com.zr.xuezhu.Live.bean.UserRelationVo;
import com.zr.xuezhu.Live.enumall.RelationEnum;
import com.zr.xuezhu.Live.mapper.UserRelationMapper;
import com.zr.xuezhu.Live.service.UserRelationService;
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
public class UserRelationServiceImpI  implements UserRelationService {

    @Autowired
    private UserRelationMapper userRelationMapper;
    @Autowired
    private PayDivideMapper payDivideMapper;

    @Override
    @Transactional
    public ResultVo add(UserRelationAddVo userRelationAddVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //逻辑验证：uid验证
        List<UserRelation> userRelationList = userRelationMapper.queryById(userLogin.getId());
        UserRelation userRelation = copyProperties(userRelationAddVo,userLogin);
        if(userRelationList.isEmpty()){
            userRelationMapper.add(userRelation);
        }else if (userRelationList.size() == 1){
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                userRelationMapper.updateRelationByLoginId(userRelation);
            }else{
                if (payDivide.getStatus()== 0){
                    userRelationMapper.updateRelationByLoginId(userRelation);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    userRelationMapper.updateRelationByLoginId(userRelation);
                    payDivideMapper.updateStatus(userLogin.getId());
                }else{
                    return ResultVo.error(ConsUtil.NOTEXIST);
                }
            }
        }else{
            return ResultVo.error(ConsUtil.USEREXITED);
        }
        /*if (userRelationList.size()>1){
            return ResultVo.error(ConsUtil.USEREXITED);
        } else if (userRelationList.size() == 1) {
            // 修改
            userRelationMapper.updateRelationByLoginId(userRelation);
        } else {
            // 新增
            userRelationMapper.add(userRelation);
        }*/
        session.setAttribute(ConsUtil.USERRELATIONADDVO,userRelationAddVo);
        return ResultVo.success(userRelation);
    }

    @Override
    public ResultVo getContacts(HttpServletRequest request) {
        JuZhuAddVo juZhuAddVo = (JuZhuAddVo)request.getSession().getAttribute(ConsUtil.JUZHUADDVO);
        if (juZhuAddVo != null) {
            return ResultVo.success(juZhuAddVo);
        }
        return ResultVo.error();
    }

    private UserRelation copyProperties(UserRelationAddVo userRelationAddVo, UserLogin userLogin) {
        UserRelation userRelation = new UserRelation();
        Date nowDate = new Date();
        BeanUtils.copyProperties(userRelationAddVo,userRelation);
        userRelation.setLoginId(userLogin.getId());
        userRelation.setCreateTime(nowDate);
        userRelation.setCreateName(userLogin.getUserName());
        userRelation.setUpdateTime(nowDate);
        userRelation.setUpdateName(userLogin.getUserName());
        return userRelation;
    }

}

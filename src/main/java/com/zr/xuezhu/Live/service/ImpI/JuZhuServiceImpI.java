package com.zr.xuezhu.Live.service.ImpI;

import com.zr.xuezhu.Live.bean.JuZhu;
import com.zr.xuezhu.Live.bean.JuZhuAddVo;
import com.zr.xuezhu.Live.mapper.JuZhuMapper;
import com.zr.xuezhu.Live.service.JuZhuService;
import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paydivide.service.PayDivideService;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Service
public class JuZhuServiceImpI implements JuZhuService {
    @Autowired
    private JuZhuMapper juZhuMapper;
    @Autowired
    private PayDivideMapper payDivideMapper;

    @Override
    public ResultVo add(JuZhuAddVo juZhuAddVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        //逻辑验证：uid验证
        List<JuZhu> juZhuList = juZhuMapper.queryById(userLogin.getId());
        JuZhu juZhu = copyProperties(juZhuAddVo,userLogin);
        if(juZhuList.isEmpty()){
            juZhuMapper.add(juZhu);
        }else if (juZhuList.size() == 1){
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                juZhuMapper.updateJuzhuByLoginId(juZhu);
            }else{
                if (payDivide.getStatus()== 0){
                    juZhuMapper.updateJuzhuByLoginId(juZhu);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    juZhuMapper.updateJuzhuByLoginId(juZhu);
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
            juZhuMapper.updateJuzhuByLoginId(juZhu);
        } else {
            // 新增
            juZhuMapper.add(juZhu);
        }*/
        session.setAttribute(ConsUtil.JUZHUADDVO,juZhuAddVo);
        return ResultVo.success();
    }

    @Override
    public ResultVo getLive(HttpServletRequest request) {
        JuZhuAddVo juZhuAddVo = (JuZhuAddVo)request.getSession().getAttribute(ConsUtil.JUZHUADDVO);
        if (juZhuAddVo != null) {
            return ResultVo.success(juZhuAddVo);
        }
        return ResultVo.error();
    }

    private JuZhu copyProperties(JuZhuAddVo juZhuAddVo, UserLogin userLogin) {
        JuZhu juZhu = new JuZhu();
        BeanUtils.copyProperties(juZhuAddVo,juZhu);

        Date nowDate = new Date();
        juZhu.setLoginId(userLogin.getId());
        juZhu.setCreateTime(nowDate);
        juZhu.setCreateName(userLogin.getUserName());
        juZhu.setUpdateTime(nowDate);
        juZhu.setUpdateName(userLogin.getUserName());
        return juZhu;
    }

}




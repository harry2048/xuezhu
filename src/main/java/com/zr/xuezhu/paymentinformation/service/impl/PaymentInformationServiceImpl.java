package com.zr.xuezhu.paymentinformation.service.impl;

import com.zr.xuezhu.Live.bean.EducationAddVo;
import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paymentinformation.mapper.PaymentInformationMapper;
import com.zr.xuezhu.paymentinformation.pojo.PaymentInformation;
import com.zr.xuezhu.paymentinformation.pojo.PaymentInformationAddVo;
import com.zr.xuezhu.paymentinformation.pojo.PaymentInformationVo;
import com.zr.xuezhu.paymentinformation.pojo.XiaLaVo;
import com.zr.xuezhu.paymentinformation.service.PaymentInformationService;
import com.zr.xuezhu.paymentinformation.util.StatusEnumClazz;
import com.zr.xuezhu.paymentinformation.util.StatusEnumRepay;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Wjerry on 2019/7/3.
 */
@Service
public class PaymentInformationServiceImpl implements PaymentInformationService {
    @Autowired
    private PaymentInformationMapper paymentInformationMapper;
    @Autowired
    private PayDivideMapper payDivideMapper;

    @Override
    public ResultVo kechengDropdownBox() {
        List<XiaLaVo> clazzList = new ArrayList<>();
        for (int i = 0; i< StatusEnumClazz.values().length; i++){
            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(i);
            xiaLaVo.setName(StatusEnumClazz.getName(i));
            clazzList.add(xiaLaVo);
        }
        return ResultVo.success(clazzList);
    }

    @Override
    public ResultVo huankuanDropdownBox() {
        List<XiaLaVo> repayList = new ArrayList<>();
        for (int i = 0; i< StatusEnumRepay.values().length; i++){
            XiaLaVo xiaLaVo = new XiaLaVo();
            xiaLaVo.setId(i);
            xiaLaVo.setName(StatusEnumRepay.getName(i));
            repayList.add(xiaLaVo);
        }
        return ResultVo.success(repayList);
    }

    @Override
    public ResultVo installmentApply(PaymentInformationAddVo paymentInformationAddVo, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserLogin userLogin = (UserLogin) session.getAttribute(ConsUtil.USER);
        if (userLogin == null) {
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        /**
         * 1.查询是否重复
         * 2.如果只有一条即更新
         * 3.如果没有即新增
         */
        List<PaymentInformation> loginIdList = paymentInformationMapper.queryByLoginId(userLogin.getId());
        PaymentInformation paymentInformation = copyProperties(paymentInformationAddVo, userLogin);
        if(loginIdList.isEmpty()){
            paymentInformationMapper.installmentApply(paymentInformation);
        }else if (loginIdList.size() == 1){
            Integer loginId = userLogin.getId();
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(loginId);
            if (payDivide == null){
                paymentInformationMapper.updatePaymentByLoginId(paymentInformation);
            }else{
                if (payDivide.getStatus()== 0){
                    paymentInformationMapper.updatePaymentByLoginId(paymentInformation);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    paymentInformationMapper.updatePaymentByLoginId(paymentInformation);
                    payDivideMapper.updateStatus(userLogin.getId());
                }else{
                    return ResultVo.error(ConsUtil.NOTEXIST);
                }
            }
            /*for (PaymentInformation paymentInformation1:loginIdList) {
                if (paymentInformation1.getStatus()==0){
                    paymentInformationMapper.updatePaymentByLoginId(paymentInformation);
                }
            }*/
        }else{
            return ResultVo.error("该用户信息已经存在");
        }

        /*if (loginIdList.size()>1){
            // 1.
            return ResultVo.error("该用户信息已经存在");
        } else if (loginIdList.size() == 1) {
            // 2.
            paymentInformationMapper.updatePaymentByLoginId(paymentInformation);
        } else {
            // 3.
            paymentInformationMapper.installmentApply(paymentInformation);
        }*/
        session.setAttribute(ConsUtil.PAYMENTINFORMATIONADDVO,paymentInformationAddVo);
        return ResultVo.success(paymentInformationAddVo);
    }

    @Override
    public ResultVo getFqsq(HttpServletRequest request) {
        PaymentInformationAddVo paymentInformationAddVo = (PaymentInformationAddVo) request.getSession().getAttribute(ConsUtil.PAYMENTINFORMATIONADDVO);
        if (paymentInformationAddVo != null) {
            return ResultVo.success(paymentInformationAddVo);
        }
        return ResultVo.error();
    }

    private PaymentInformation copyProperties(PaymentInformationAddVo paymentInformationAddVo, UserLogin userLogin) {
        PaymentInformation paymentInformation = new PaymentInformation();
        BeanUtils.copyProperties(paymentInformationAddVo,paymentInformation);
        Date nowDate = new Date();
        paymentInformation.setLoginId(userLogin.getId());
        paymentInformation.setCreateTime(nowDate);
        paymentInformation.setCreateName(userLogin.getUserName());
        paymentInformation.setUpdateTime(nowDate);
        paymentInformation.setUpdateName(userLogin.getUserName());
        return paymentInformation;
    }
}

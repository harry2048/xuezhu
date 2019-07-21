package com.zr.xuezhu.paydivide.service.Impl;

import com.zr.xuezhu.paydivide.mapper.PayDivideMapper;
import com.zr.xuezhu.paydivide.pojo.PayDivide;
import com.zr.xuezhu.paydivide.pojo.PayDivideAddVo;
import com.zr.xuezhu.paydivide.pojo.PayDivideDTO;
import com.zr.xuezhu.paydivide.pojo.PayDivideShowVo;
import com.zr.xuezhu.paydivide.service.PayDivideService;
import com.zr.xuezhu.paymentinformation.util.StatusEnumClazz;
import com.zr.xuezhu.paymentinformation.util.StatusEnumRepay;
import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userrepay.meiju.StatusEnum;
import com.zr.xuezhu.util.ConsUtil;
import com.zr.xuezhu.util.ResultVo;
import com.zr.xuezhu.util.UUIDUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ZLFamily on 2019/7/3.
 */
@Service
public class PayDivideServiceImpl implements PayDivideService {
    @Autowired
    private PayDivideMapper payDivideMapper;
    @Override
    public ResultVo queryPage(HttpServletRequest req) {

        UserLogin userLogin = (UserLogin) req.getSession().getAttribute(ConsUtil.USER);
        if (userLogin==null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }

        PayDivideShowVo payDivideShowVo = new PayDivideShowVo();
        List<PayDivideDTO> payDivideDTOkkList =payDivideMapper.queryPage(userLogin.getId());
        if (payDivideDTOkkList.size()>1){
            return ResultVo.error("操作异常，请联系管理员！");
        }
        PayDivideDTO payDivideDTO = payDivideDTOkkList.get(0);
        BeanUtils.copyProperties(payDivideDTO,payDivideShowVo);
        Date now = new Date();

         //  拼接起止时间
        payDivideShowVo = getStartTime(now,payDivideDTO,payDivideShowVo);

        // 首次还款日期，下个月的今天,每月还款日期
        payDivideShowVo = getFirstPay(payDivideShowVo, now);

        // 借贷期限
        payDivideShowVo.setPaymentCycle(StatusEnumRepay.getName(payDivideDTO.getPaymentCycle()));

        // 拼接银行
        payDivideShowVo = getBank(payDivideShowVo, payDivideDTO);

        // 我的课程
        payDivideShowVo.setMyCourse(StatusEnumClazz.getName(payDivideDTO.getMyCourse()));
        payDivideShowVo.setPaymentAccount(StatusEnum.ZHONGRUAN.getStatusName());
        payDivideShowVo.setAgreement(StatusEnum.SHOUQUANSHU.getStatusName());

        return ResultVo.success(payDivideShowVo);
    }

    @Override
    @Transactional
    public ResultVo xueZhuFenQiAdd(PayDivideShowVo payDivideShowVo, HttpServletRequest req) {
        UserLogin userLogin = (UserLogin) req.getSession().getAttribute(ConsUtil.USER);
        if (userLogin==null){
            return ResultVo.notLogin(ConsUtil.USERNOTLOGIN);
        }
        PayDivideAddVo payDivideAddVo = copyProperties(payDivideShowVo,userLogin);
        PayDivide addVos = payDivideMapper.queryStatusByLoginId(userLogin.getId());
        if(addVos==null){
            payDivideAddVo.setOrderNum(UUIDUtil.getOrderNum());
            payDivideMapper.insert(payDivideAddVo);
        }else{
            PayDivide payDivide = payDivideMapper.queryStatusByLoginId(userLogin.getId());
            if (payDivide == null){
                payDivideMapper.updateFenQi(payDivideAddVo);
            }else{
                if (payDivide.getStatus()== 0){
                    payDivideMapper.updateFenQi(payDivideAddVo);
                }else if (payDivide.getStatus()==5 || payDivide.getStatus()== 2){
                    payDivideMapper.updateFenQi(payDivideAddVo);
                    payDivideMapper.updateStatus(userLogin.getId());
                    //如果状态等于确认，只允许查看不允许修改
                }else if (payDivide.getStatus()==1){
                    return ResultVo.success();
                }else {
                    return ResultVo.error(ConsUtil.BUYUNXUXIUGAI);
                }
            }
        }
        /*if (addVos.size()>1){
            return ResultVo.error(ConsUtil.USEREXITED);
        } else if (addVos.size() == 1) {
            // 修改操作
            payDivideMapper.updateFenQi(payDivideAddVo);
        } else {
            // 数据入库
            payDivideAddVo.setOrderNum(UUIDUtil.getOrderNum());
            payDivideMapper.insert(payDivideAddVo);
        }*/

        return ResultVo.success(payDivideAddVo);
    }

    private PayDivideAddVo copyProperties(PayDivideShowVo payDivideShowVo, UserLogin userLogin) {
        PayDivideAddVo payDivideAddVo = new PayDivideAddVo();
        BeanUtils.copyProperties(payDivideShowVo,payDivideAddVo);
        payDivideAddVo.setLoginId(userLogin.getId());
        payDivideAddVo.setPaymentCycle(StatusEnumRepay.getValue(payDivideShowVo.getPaymentCycle()));
        payDivideAddVo.setMyCourse(StatusEnumClazz.getValue(payDivideShowVo.getMyCourse()));
        payDivideAddVo.setCreateName(userLogin.getUserName());
        payDivideAddVo.setUpdateName(userLogin.getUserName());
        return payDivideAddVo;
    }


    private PayDivideShowVo getBank(PayDivideShowVo payDivideShowVo, PayDivideDTO payDivideDTO) {
        String bank = StatusEnum.getName(payDivideDTO.getOpeningBank());
        String bankCode = payDivideDTO.getBankCardAccount();
        String newBankCode = bankCode.substring(bankCode.length() - 4);
        StringBuilder sb = new StringBuilder();
        sb.append(bank).append("(").append(newBankCode).append(")");
        payDivideShowVo.setBackBank(sb.toString());
        return payDivideShowVo;
    }

    private PayDivideShowVo getFirstPay(PayDivideShowVo payDivideShowVo, Date now) {
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd" );
        String startStr = sdf.format(now);
        String[] split = startStr.split("/");
        String monthStr = split[1];
        Integer monthInt = null;
        if (monthStr.equals("12")) {
            monthInt = 1;
        }

        monthInt = Integer.parseInt(monthStr)+1;
        String dayStr = split[2];
        StringBuilder sb = new StringBuilder();
        sb.append(monthStr).append("月").append(dayStr).append("日");
        // 设置首次还款日期
        payDivideShowVo.setFirstPayMentDate(sb.toString());

        // 设置每月几日还款
        sb.delete(0, sb.length());
        sb.append("每月").append(dayStr).append("日");
        payDivideShowVo.setPayMentDate(sb.toString());
        return payDivideShowVo;
    }

    private PayDivideShowVo getStartTime(Date start,PayDivideDTO payDivideDTO,PayDivideShowVo payDivideShowVo) {
        Integer monthNum = StatusEnumRepay.getMonthNum(payDivideDTO.getPaymentCycle());
        long startMillis = start.getTime();
        long endMillis = (long) 1000*60*60*24*30*monthNum;
        long result = endMillis +startMillis;

        Calendar calendar = Calendar.getInstance(); calendar.setTimeInMillis(result);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;// 算出来的月份比实际小1
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy/MM/dd" );
        String startStr = sdf.format(start);

        StringBuilder sb = new StringBuilder();
        sb.append(startStr).append("-")
                .append(year)
                .append("/")
                .append(month)
                .append("/")
                .append(day);
        payDivideShowVo.setStartTime(sb.toString());
        return payDivideShowVo;
    }
}

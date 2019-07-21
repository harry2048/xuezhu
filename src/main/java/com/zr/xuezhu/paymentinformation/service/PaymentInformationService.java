package com.zr.xuezhu.paymentinformation.service;

import com.zr.xuezhu.paymentinformation.pojo.PaymentInformationAddVo;
import com.zr.xuezhu.util.ResultVo;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Wjerry on 2019/7/3.
 */
public interface PaymentInformationService {
    ResultVo kechengDropdownBox();

    ResultVo huankuanDropdownBox();

    ResultVo installmentApply(PaymentInformationAddVo paymentInformationAddVo, HttpServletRequest request);

    ResultVo getFqsq(HttpServletRequest request);
}

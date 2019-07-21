package com.zr.xuezhu.userpicture.service;

import com.zr.xuezhu.util.ResultVo;

public interface ImportService {
    ResultVo addIdPositive(String mode, Integer loginId, String remotePath);
}

package com.zr.xuezhu.userpicture.service.ImpI;


import com.zr.xuezhu.userpicture.bean.UserPicture;
import com.zr.xuezhu.userpicture.mapper.ImportMapper;
import com.zr.xuezhu.userpicture.service.ImportService;
import com.zr.xuezhu.util.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportServiceImpl implements ImportService {
    @Autowired
    private ImportMapper importMapper;
    @Override
    public ResultVo addIdPositive(String mode, Integer loginId, String remotePath) {
        //根据用户查询是否已存在记录
        UserPicture userPicture = importMapper.queryByLoginId(loginId);
        if (userPicture != null){
            importMapper.updatePicIdPostiveById(userPicture.getId(),remotePath);
        }else{
            importMapper.insertPicIdPostiveById(loginId,remotePath);
        }
        return ResultVo.success(remotePath);
    }
}

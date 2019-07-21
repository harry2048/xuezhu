package com.zr.xuezhu.userpicture.controller;

import com.zr.xuezhu.userLogin.pojo.UserLogin;
import com.zr.xuezhu.userpicture.service.ImportService;
import com.zr.xuezhu.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RequestMapping("/import")
@RestController
@CrossOrigin
public class ImportController {
    @Autowired
    private FtpPro ftpPro;
    @Autowired
    private ImportService importService;
    @PostMapping("/idPositive")
    public ResultVo idPositive(HttpServletRequest req, MultipartFile multipartFile) throws IOException {
        Integer loginId = ((UserLogin) req.getSession().getAttribute(ConsUtil.USER)).getId();
        if(loginId == null){
            return ResultVo.error("登录信息已失效请重新登陆！");
        }
        //上传文件
//        String filePath = multipartFile.getOriginalFilename();
        String imageName = IDUtils.genImageName()+".gif";
        String luJing = IDUtils.genLuJing();
//        FileInputStream in=new FileInputStream(new File(filePath));
        boolean flag = FTPUtils.uploadFile(ftpPro.getIp(), ftpPro.getPort(), ftpPro.getUsername(), ftpPro.getPassword(), ftpPro.getBasePath(), luJing, imageName, multipartFile.getInputStream());
        System.out.println(flag);
        //修改数据库
        if (flag){
            return importService.addIdPositive("idPositive",loginId,ftpPro.getBasePath()+luJing+imageName);
        }else{
            return ResultVo.error("ftp服务器存在问题！");
        }
    }

    @GetMapping("/getImg")
    public void getImg(HttpServletResponse resp, String url) {
        try {
            String remotePath = url.substring(0,url.lastIndexOf("/")+1);
            String flieName = url.substring(url.lastIndexOf("/")+1);
            InputStream ins = FTPUtils.getImg(ftpPro.getIp(), ftpPro.getPort(), ftpPro.getUsername(), ftpPro.getPassword(), remotePath,flieName);

            OutputStream out = resp.getOutputStream();
            byte[] b = new byte[ins.available()];
            ins.read(b);
            out.write(b);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

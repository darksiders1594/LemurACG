package org.personal.lemuracg.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class BlobStreamController {

    // 所有 MPEG-DASH 文件经上传后会存放在此目录下
    @Value("${lemuracg.path.upload.video}")
    private String uploadPath;

    /**
     * 该方法通过二进制流式传输, 处理浏览器对 MPEG-DASH 文件的请求
     *
     * @param videoID MPEG-DASH 文件的上级存放路径
     * @param fileName 请求的文件名
     */
    @CrossOrigin
    @RequestMapping(path = "/upload/video/{videoID}/{fileName}", method = RequestMethod.GET)
    @ResponseBody
    public void getByBlobStream(HttpServletResponse response,
                                @PathVariable("videoID") String videoID,
                                @PathVariable("fileName") String fileName) {
        // 声明目标文件绝对路径
        String filePath = uploadPath + "/" + videoID + "/" + fileName;

        // 创建文件对象
        File file = new File(filePath);

        // 重新设置 response 缓冲区
        response.reset();

        // 跨域处理
        response.setHeader("Access-Control-Allow-Origin", "*");

        // 设置响应类型
        response.setContentType("application/octet-stream");

        // 设置响应编码
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Length", "" + file.length());

        // 响应文件
        try (
                FileInputStream fileIS = new FileInputStream(filePath);
                OutputStream responseOS = response.getOutputStream()
        ) {
            byte[] buffer = new byte[1024];
            int bytes;
            while ((bytes = fileIS.read(buffer)) != -1) {
                responseOS.write(buffer, 0, bytes);
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

}

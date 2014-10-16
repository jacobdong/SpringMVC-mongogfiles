package com.xcode.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;
import com.xcode.tool.ToolMongoDb;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public void uploadFileToMongo(HttpServletRequest req, HttpServletResponse resp, @RequestParam("file") MultipartFile mFile) throws IOException {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        try {
            GridFS fs = ToolMongoDb.getGridfs();
            GridFSInputFile gfs = fs.createFile(mFile.getInputStream());

            String originFileName = mFile.getOriginalFilename();

            // 获取文件后缀名
            String[] infos = originFileName.split("\\.");

            System.out.println(infos[1]);
            gfs.put("uuid", uuid);
            gfs.setFilename(uuid + "." + infos[1]);
            gfs.save();
            resp.getWriter().write("uuid" + uuid);
            return;
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("上fa失败");
            return;
        }
    }
}

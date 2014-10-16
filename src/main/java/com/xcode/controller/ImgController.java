package com.xcode.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.xcode.tool.ToolMongoDb;

@Controller
@RequestMapping("/imgs")
public class ImgController {

    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public void uploadFileToMongo(HttpServletRequest req, HttpServletResponse resp, @PathVariable String uuid) throws IOException {

        GridFS fs = ToolMongoDb.getGridfs();
        
        //GridFSDBFile gridFSDBFile = null;
        
        GridFSDBFile gridFSDBFile = fs.findOne(new BasicDBObject("uuid",uuid));

        if (null != gridFSDBFile) {
            try (OutputStream outputStream = resp.getOutputStream()) {
                gridFSDBFile.writeTo(outputStream);
                return;
            }
        } else {
            resp.getWriter().write("not exsit");
            return;
        }
    }
}

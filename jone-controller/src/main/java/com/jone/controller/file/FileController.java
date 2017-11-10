package com.jone.controller.file;

import com.alibaba.fastjson.JSON;
import com.jone.api.common.ContentService;
import com.jone.controller.BaseController;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jone on 2016/10/15.
 */
@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/file")
public class FileController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource(name = "contentService")
    private ContentService fileService;

    @Value("${openoffice.path}")
    private String path;

//    @RequestMapping("/download")
//    public void postRequest(HttpServletRequest request, HttpServletResponse response)
//            throws IOException {
//        String contentId = request.getParameter("cid");
//        String fmt = request.getParameter("fmt");
//        String down = request.getParameter("down");
//        Boolean isDown = true;
//        if (!StringUtils.isEmpty(down))
//            isDown = false;
//        FileObj content;
//        //OutputStream os = null;
//        ServletOutputStream out = null;
//        OutputStream o = new ByteArrayOutputStream();
//        try {
//            content = fileService.getFileById(contentId);
//            out = response.getOutputStream();
//            if (content != null) {
//                File file = new File(content.getPath());
//                ByteArrayOutputStream outb = new ByteArrayOutputStream();
//                byte[] data = null;
//                String outName = "";
//                if (StringUtils.isEmpty(fmt)) {
//                    data = FileUtils.readFileToByteArray(file);
//                    response.setContentType(content.getContentType());
//                    outName = content.getContentName();
//                } else {
//                    if ("pdf".equals(fmt)) {
//                        String name = content.getFileName();
//                        outName = name.substring(0, name.lastIndexOf('.')) + ".pdf";
////                    String outPath = content.getPath().split("\\.")[0]+".pdf";
////                    File outFile = new File(outPath);
////                    Office2PDF.office2PDF(path,file.getCanonicalPath(),outPath);
//                        String outPath = Office2PDF.word2pdf(file.getCanonicalPath(),path);
//                        File outFile = new File(outPath);
//                        data = FileUtils.readFileToByteArray(outFile);
//                        if (outFile.exists())
//                            outFile.delete();
//                        response.setContentType("application/pdf");
//                    }
//                }
//                if (data != null)
//                    response.setContentLength(data.length);
//                if (isDown)
//                    response.setHeader("Content-disposition", "attachment;filename="
//                            + getFileName(request, response, outName));
//                out.write(data);
//                out.flush();
//            } else {
//                out.write(JSON.toJSONBytes("not found data for cid : " + contentId));
//                out.flush();
//            }
//        } catch (Exception e) {
//            //e.printStackTrace();
//        } finally {
//            if (out != null)
//                out.close();
//            if (o != null)
//                o.close();
//        }
//
//    }

    public String getFileName(HttpServletRequest request,
                              HttpServletResponse response, String fileName) {
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(fileName, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(fileName.getBytes("GB2312"),
                            "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (-1 != agent.indexOf("Mozilla")))
                return new String(fileName.getBytes(), "ISO8859-1");

            return fileName;
        } catch (Exception ex) {
            return fileName;
        }

    }

}

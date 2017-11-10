package com.jone.util.office;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.regex.Pattern;

public class Office2PDF {

    public static int office2PDF(String openPath, String sourceFile, String destFile) {
        try {
            File inputFile = new File(sourceFile);
            if (!inputFile.exists()) {
                return -1;// 找不到源文件, 则返回-1
            }

            // 如果目标路径不存在, 则新建该路径
            File outputFile = new File(destFile);
            if (!outputFile.getParentFile().exists()) {
                outputFile.getParentFile().mkdirs();
            }
			/*
			 * 从url.properties文件中读取OpenOffice的安装根目录, OpenOffice_HOME对应的键值.
			 * 我的OpenOffice是安装在D:\Program Files\OpenOffice.org 3下面的, 如果大家的
			 * OpenOffice不是安装的这个目录下面，需要修改url.properties文件中的 OpenOffice_HOME的键值.
			 * 但是需要注意的是：要用"\\"代替"\",用"\:"代替":" . 如果大家嫌麻烦,
			 * 可以直接给OpenOffice_HOME变量赋值为自己OpenOffice的安装目录
			 */

            String OpenOffice_HOME = openPath;
            if (OpenOffice_HOME == null)
                return -1;
            // 如果从文件中读取的URL地址最后一个字符不是 '\'，则添加'\'
            if (OpenOffice_HOME.charAt(OpenOffice_HOME.length() - 1) != '\\') {
                OpenOffice_HOME += "\\";
            }
            // 启动OpenOffice的服务
            String command = OpenOffice_HOME
                    + "program\\soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8108;urp;\" -nofirststartwizard";
            Process pro = Runtime.getRuntime().exec(command);
            // connect to an OpenOffice.org instance running on port 8108
            OpenOfficeConnection connection = new SocketOpenOfficeConnection(
                    "127.0.0.1", 8108);
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(
                    connection);
            converter.convert(inputFile, outputFile);

            // close the connection
            connection.disconnect();
            // 关闭OpenOffice服务的进程
            pro.destroy();

            return 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (ConnectException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

    /**
     * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice
     *
     * @param inputFilePath
     *            源文件,绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试. 包括.doc, .docx, .xls, .xlsx, .ppt, .pptx等.
     *
     */
    public static String word2pdf(String inputFilePath,String openPath) {
        DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

        String officeHome = "";
        if(StringUtils.isEmpty(openPath))
            officeHome = getOfficeHome();
        else
            officeHome = openPath;
        config.setOfficeHome(officeHome);

        OfficeManager officeManager = config.buildOfficeManager();
        officeManager.start();

        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        File inputFile = new File(inputFilePath);
        String outputFilePath = getOutputFilePath(inputFile);
        if (inputFile.exists()) {// 找不到源文件, 则返回
            File outputFile = new File(outputFilePath);
            if (!outputFile.getParentFile().exists()) { // 假如目标路径不存在, 则新建该路径
                outputFile.getParentFile().mkdirs();
            }
            converter.convert(inputFile, outputFile);
        }

        officeManager.stop();
        return outputFilePath;
    }

    public static String getOutputFilePath(File inputFile) {
        try {
            String inputFilePath = inputFile.getCanonicalPath();
            String outputFilePath = inputFilePath.replaceAll(FilenameUtils.getExtension(inputFile.getName()), "pdf");
            return outputFilePath;
        }catch (Exception e){

        }

        return "";
    }

    public static String getOfficeHome() {
        String osName = System.getProperty("os.name");
        if (Pattern.matches("Linux.*", osName)) {
            return "/opt/openoffice.org3";
        } else if (Pattern.matches("Windows.*", osName)) {
            return "D:/openoffice";
        } else if (Pattern.matches("Mac.*", osName)) {
            return "/Application/OpenOffice.org.app/Contents";
        }
        return null;
    }
}

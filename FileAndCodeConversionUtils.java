package com.wondersgroup.inspection.common.util;

import org.apache.axis.utils.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * 文件与base64编码文件相互转换
 * Created by zhangming on 2018/1/25
 */
public class FileAndCodeConversionUtils {

    private static final String DG_PATH = "D：\\base64Code.docx";//默认生成base64编码路径
    private static final String DE_PATH = "D：\\file.docx";//默认生成文件路径

    /**
     * 文件转化成base64字符串
     * @param docxFilePath 文件的位置
     * @return 返回Base64编码过的字节数组字符串
     */
    public static String GetImageStr(String docxFilePath) {// 将文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取文件字节数组
        try {
            if(StringUtils.isEmpty(docxFilePath)){
                docxFilePath= DG_PATH ;
            }
            in = new FileInputStream(docxFilePath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * base64字符串转为文件
     * @param docxFile base64编码的code
     * @param filePath 文件生成的位置
     * @return
     */
    public static boolean GenerateImage(String docxFile ,String filePath) { // 对字节数组字符串进行Base64解码并生成文件
        if (docxFile == null) // 文件数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(docxFile);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成文件
            if(StringUtils.isEmpty(filePath)){
                filePath = DE_PATH ;
            }
            OutputStream out = new FileOutputStream(filePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

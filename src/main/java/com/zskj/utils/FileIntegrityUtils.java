package com.zskj.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.zip.CRC32;

/**
 * @ProjectName: httpClient
 * @packageName: com.zskj.utils
 * @Description: 文件完整性校验
 * @author: huayang.bai
 * @date: 2018-12-11 20:53
 */
public class FileIntegrityUtils {

    public static void main(String[] args) {
        String message = getFileMd5(new File("/Users/baihuayang/Downloads/poi-bin-3.7-20101029.zip"));
        System.out.println("d353644608f9c1b9e38d9d2b722551c0".equals(message));
        String message1 = getFileSHA1(new File("/Users/baihuayang/Downloads/poi-bin-3.7-20101029.zip"));
        System.out.println("bb83902afb82e54cbf33db4f28291a66f405a15d".equals(message1));
        String fileCRC32 = getFileCRC32(new File("/Users/baihuayang/Downloads/poi-bin-3.7-20101029.zip"));
        System.out.println(fileCRC32);
    }

    /**
     * 通过SHA1算法验证文件的完整性
     *
     * @param file
     * @return
     */
    public static String getFileSHA1(File file) {
        return getFileMessage(file, "SHA1");
    }

    /**
     * 通过MD5算法验证文件的完整性
     *
     * @param file
     * @return
     */
    public static String getFileMd5(File file) {
        return getFileMessage(file, "MD5");
    }


    /**
     * 通过CRC32验证文件的完整性
     *
     * @param file
     * @return
     */
    public static String getFileCRC32(File file) {
        CRC32 crc32 = new CRC32();
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] b = new byte[1024];
            int l;
            while ((l = fis.read(b, 0, b.length)) > -1) {
                crc32.update(b, 0, l);
            }
            return String.valueOf(crc32.getValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据指定的算法获取文件的消息摘要
     *
     * @param file
     * @param type
     * @return
     * @throws IOException
     */
    private static String getFileMessage(File file, String type) {
        try (FileInputStream fis = new FileInputStream(file)) {
            MessageDigest messageDigest = MessageDigest.getInstance(type);
            byte[] b = new byte[1024];
            int l;
            while ((l = fis.read(b, 0, b.length)) > 0) {
                messageDigest.update(b, 0, l);
            }
            return byteArrayToHex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组转换成十六进制字符串
     *
     * @param byteArray
     * @return
     */
    private static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray).toLowerCase();
    }


}

/**
 * FileName: MaterialUtil
 * Author:   Phil
 * Date:     11/20/2018 9:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.wechat.material.util;

import com.phil.modules.util.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author Phil
 * @create 11/20/2018 9:13 PM
 * @since 1.0
 */
@Slf4j
public class MaterialUtil extends HttpUtil {

    /**
     * 上传媒体文件(本地)
     *
     * @param api       api的路径
     * @param param     api参数
     * @param mediaPath 待上传的image/music 的path
     * @return
     * @throws Exception
     */
    public static String uploadMediaFile(String api, Map<String, String> param, String mediaPath)
            throws Exception {
        try {
            URL url = new URL(setParmas(api, param, ""));
            File file = new File(mediaPath);
            if (!file.isFile() || !file.exists()) {
                throw new IOException("file is not exist");
            }
            HttpURLConnection conn = getConnection(url, true);
            // 设置边界
            String boundary = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            // 请求正文信息
            // 第一部分
            try (OutputStream output = new DataOutputStream(conn.getOutputStream())) {
                IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
                IOUtils.write(("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName() + "\"\r\n")
                        .getBytes(), output);
                IOUtils.write("Content-Type:application/octet-stream\r\n\r\n".getBytes(), output);
                // 文件正文部分 把文件已流文件的方式 推入到url中
                DataInputStream input = new DataInputStream(new FileInputStream(file));
                IOUtils.copy(input, output);
                // 结尾部分
                IOUtils.write(("\r\n--" + boundary + "--\r\n").getBytes(), output);
                output.flush();
                conn.disconnect();
            }
            return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("read data error");
        }
    }

    /**
     * 上传媒体文件(不能本地)
     *
     * @param api       api的路径
     * @param param     api参数
     * @param mediaPath 待上传的image/music 的path
     * @return
     * @throws Exception
     */
    public static String uploadMediaUrl(String api, Map<String, String> param, String mediaPath) throws Exception {
        try {
            URL url = new URL(setParmas(api, param, ""));
            String boundary = "----";
            HttpURLConnection conn = getConnection(url, POST_METHOD);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            try (OutputStream output = new DataOutputStream(conn.getOutputStream())) {
                URL mediaUrl = new URL(mediaPath);
                HttpURLConnection mediaConn = getConnection(mediaUrl, false);
                String connType = mediaConn.getContentType();
                // 获得文件扩展
                String fileExt = getFileExt(connType);
                if (Objects.isNull(fileExt)) {
                    throw new IOException("the url cannot support this method");
                }
                IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
                IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
                        + "\"\r\n").getBytes(), output);
                IOUtils.write(("Content-Type: " + fileExt + "\r\n\r\n").getBytes(), output);
                BufferedInputStream input = new BufferedInputStream(mediaConn.getInputStream());
                IOUtils.copy(input, output);
                IOUtils.write(("\r\n----" + boundary + "--\r\n").getBytes(), output);
                mediaConn.disconnect();
                conn.disconnect();
            }
            // 获取输入流
            return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("read data error");
        }
    }

    /**
     * 上传Video媒体文件(本地)
     *
     * @param api          api的路径
     * @param param        api参数
     * @param mediaPath    待上传的voide 的path
     * @param title        视频标题
     * @param introduction 视频描述
     * @return
     * @throws Exception
     */
    public static String uploadVideoMediaFile(String api, Map<String, String> param,
                                              String mediaPath, String title, String introduction) throws Exception {
        try {
            File file = new File(mediaPath);
            if (!file.isFile() || !file.exists()) {
                throw new IOException("file is not exist");
            }
            URL url = new URL(setParmas(api, param, null));
            HttpURLConnection conn = getConnection(url, POST_METHOD);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            String boundary = "----------" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            try (OutputStream output = new DataOutputStream(conn.getOutputStream())) {
                output.write(("--" + boundary + "\r\n").getBytes());
                output.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"%s\"\r\n", file.getName()).getBytes());
                output.write("Content-Type: video/mp4 \r\n\r\n".getBytes());
                FileInputStream input = new FileInputStream(file);
                IOUtils.copy(input, output);
                output.write(("--" + boundary + "\r\n").getBytes());
                output.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes());
                output.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes());
                output.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes());
                output.flush();
                conn.disconnect();
            }
            return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("read data error");
        }
    }

    /**
     * 上传Video媒体文件(网络)
     *
     * @param api          api的路径
     * @param param        api参数
     * @param mediaPath    待上传的voide 的path
     * @param title        视频标题
     * @param introduction 视频描述
     *                     //     * @param connTime     连接时间 默认为5000
     *                     //     * @param readTime     读取时间 默认为5000
     * @return
     * @throws Exception
     */
    public static String uploadVideoMediaUrl(String api, Map<String, String> param, String mediaPath,
                                             String title, String introduction) throws Exception {
        try {
            URL url = new URL(setParmas(api, param, null));
            String boundary = "----";
            HttpURLConnection conn = getConnection(url, POST_METHOD);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            try (OutputStream output = new DataOutputStream(conn.getOutputStream())) {
                URL mediaUrl = new URL(mediaPath);
                HttpURLConnection mediaConn = getConnection(mediaUrl, false);
                IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
                IOUtils.write(("Content-Disposition: form-data; name=\"media\"; filename=\"" + getFileName(mediaPath)
                        + "\"\r\n").getBytes(), output);
                IOUtils.write("Content-Type: video/mp4 \r\n\r\n".getBytes(), output);
                BufferedInputStream input = new BufferedInputStream(mediaConn.getInputStream());
                IOUtils.copy(input, output);
                // 结尾部分
                IOUtils.write(("--" + boundary + "\r\n").getBytes(), output);
                IOUtils.write("Content-Disposition: form-data; name=\"description\";\r\n\r\n".getBytes(), output);
                IOUtils.write(String.format("{\"title\":\"%s\", \"introduction\":\"%s\"}", title, introduction).getBytes(), output);
                IOUtils.write(("\r\n--" + boundary + "--\r\n\r\n").getBytes(), output);
                mediaConn.disconnect();
                conn.disconnect();
            }
            // 获取输入流
            return IOUtils.toString(conn.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new IOException("read data error");
        }
    }

}

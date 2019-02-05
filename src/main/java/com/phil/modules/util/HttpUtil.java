/**
 * FileName: HttpReqUtil
 * Author:   Phil
 * Date:     8/2/2018 17:57
 * Description: Http Request Connection Util
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.phil.modules.result.ResultState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.http.MediaType;

import javax.net.ssl.*;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈一句话功能简述〉
 * 〈Http Request Connection Util〉
 *
 * @author Phil
 * @create 8/2/2018 17:57
 * @since 1.0.0
 */
@Slf4j
public class HttpUtil {

    private static MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();

    protected static final String POST_METHOD = "POST";

    private static final String GET_METHOD = "GET";

    static {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                log.debug("ClientTrusted");
            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                log.debug("ServerTrusted");
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        }};

        HostnameVerifier doNotVerify = (s, sslSession) -> true;

        try {
//          SSLContext sc = SSLContext.getInstance("TLSv1.2");
            SSLContext sc = SSLContext.getInstance("SSL", "SunJSSE");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(doNotVerify);
        } catch (Exception e) {
            log.error("Initialization https impl occur exception : {}", e);
        }
    }

    /**
     * 默认的http请求执行方法
     *
     * @param url    url 路径
     * @param method 请求的方法 POST/GET
     * @param map    请求参数集合
     * @param data   输入的数据 允许为空
     * @return result
     */
    private static String HttpDefaultExecute(String url, String method, Map<String, String> map, String data) {
        String result = "";
        try {
            url = setParmas(url, map, null);
            result = defaultConnection(url, method, data);
        } catch (Exception e) {
            log.error("出错参数 {}", map);
        }
        return result;
    }

    public static String httpGet(String url, Map<String, String> map) {
        return HttpDefaultExecute(url, GET_METHOD, map, null);
    }

    public static String httpPost(String url, Map<String, String> map, String data) {
        return HttpDefaultExecute(url, POST_METHOD, map, data);
    }

    /**
     * 默认的https执行方法,返回
     *
     * @param url    url 路径
     * @param method 请求的方法 POST/GET
     * @param map    请求参数集合
     * @param data   输入的数据 允许为空
     * @return result
     */
    private static String HttpsDefaultExecute(String url, String method, Map<String, String> map, String data) {
        try {
            url = setParmas(url, map, null);
            log.info(data);
            return defaultConnection(url, method, data);
        } catch (Exception e) {
            log.error("出错参数 {}", map);
        }
        return "";
    }

    public static String doGet(String url, Map<String, String> map) {
        return HttpsDefaultExecute(url, GET_METHOD, map, null);
    }

    public static String doPost(String url, Map<String, String> map, String data) {
        return HttpsDefaultExecute(url, POST_METHOD, map, data);
    }

    /**
     * @param path   请求路径
     * @param method 方法
     * @param data   输入的数据 允许为空
     * @return
     * @throws Exception
     */
    private static String defaultConnection(String path, String method, String data) throws Exception {
        if (StringUtils.isBlank(path)) {
            throw new IOException("url can not be null");
        }
        String result = null;
        URL url = new URL(path);
        HttpURLConnection conn = getConnection(url, method);
        if (StringUtils.isNotEmpty(data)) {
            OutputStream output = conn.getOutputStream();
            output.write(data.getBytes(StandardCharsets.UTF_8));
            output.flush();
            output.close();
        }
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream input = conn.getInputStream();
            result = IOUtils.toString(input, StandardCharsets.UTF_8);
            input.close();
            conn.disconnect();
        }
//        log.info(result);
        return result;
    }

    /**
     * 根据url的协议选择对应的请求方式
     *
     * @param url    请求路径
     * @param method 方法
     * @return conn
     * @throws IOException 异常
     */
    //待改进
    protected static HttpURLConnection getConnection(URL url, String method) throws IOException {
        HttpURLConnection conn;
        if (StringUtils.equals("https", url.getProtocol())) {
            conn = (HttpsURLConnection) url.openConnection();
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        if (conn == null) {
            throw new IOException("connection can not be null");
        }
        conn.setRequestProperty("Pragma", "no-cache");// 设置不适用缓存
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Connection", "Close");// 不支持Keep-Alive
        conn.setUseCaches(false);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod(method);
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);

        return conn;
    }


    /**
     * 根据url
     *
     * @param url 请求路径
     * @return isFile
     * @throws IOException 异常
     */
    //待改进
    protected static HttpURLConnection getConnection(URL url, boolean isFile) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (conn == null) {
            throw new IOException("connection can not be null");
        }
        //设置从httpUrlConnection读入
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        //如果是上传文件，则设为POST
        if (isFile) {
            conn.setRequestMethod(POST_METHOD); //GET和 POST都可以 文件略大改成POST
        }
        // 设置请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", String.valueOf(StandardCharsets.UTF_8));
        conn.setConnectTimeout(8000);
        conn.setReadTimeout(8000);
        return conn;
    }


    /**
     * 拼接参数
     *
     * @param url     需要拼接参数的url
     * @param map     参数
     * @param charset 编码格式
     * @return 拼接完成后的url
     */
    public static String setParmas(String url, Map<String, String> map, String charset) throws Exception {
        String result = StringUtils.EMPTY;
        boolean hasParams = false;
        if (StringUtils.isNotEmpty(url) && MapUtils.isNotEmpty(map)) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey().trim();
                String value = entry.getValue().trim();
                if (hasParams) {
                    builder.append("&");
                } else {
                    hasParams = true;
                }
                if (StringUtils.isNotEmpty(charset)) {
                    builder.append(key).append("=").append(URLEncoder.encode(value, charset));
                } else {
                    builder.append(key).append("=").append(value);
                }
            }
            result = builder.toString();
        }

        URL u = new URL(url);
        if (StringUtils.isEmpty(u.getQuery())) {
            if (url.endsWith("?")) {
                url += result;
            } else {
                url = url + "?" + result;
            }
        } else {
            if (url.endsWith("&")) {
                url += result;
            } else {
                url = url + "&" + result;
            }
        }
        log.debug("request url is {}", url);
        return url;
    }

    /**
     * 默认的下载素材方法
     *
     * @param api      api路径
     * @param params   参数
     * @param method   http方法 POST/GET
     * @param savePath 素材需要保存的路径
     * @return 返回状态
     */
    public static ResultState defaultDownloadaterial(String api, TreeMap<String, String> params, String method,
                                                     String savePath) {
        ResultState result = new ResultState();
        try {
            api = setParmas(api, params, null);
            URL url = new URL(api);
            HttpURLConnection conn = getConnection(url, method);
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String contentType = conn.getContentType();
                result = contentType(contentType, conn, savePath);
            } else {
                result.setErrcode(500);
                result.setErrmsg(conn.getResponseCode() + "," + conn.getResponseMessage());
            }
        } catch (Exception e) {
            result.setErrcode(400);
            result.setErrmsg("Unknown Error");
            log.error("defaultDownloadaterial exception {}" + e.getMessage());
        }
        return result;
    }

    /**
     * 文件路径
     *
     * @param mediaUrl url 例如 http://su.bdimg.com/static/superplus/img/logo_white_ee663702.png
     * @return logo_white_ee663702.png
     */
    protected static String getFileName(String mediaUrl) {
        return StringUtils.substringAfterLast(mediaUrl, "/");
    }

    /**
     * 获取客户端ip
     *
     * @param request
     * @return
     */
    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (ip != null && ip.indexOf(",") > 0 && ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    /**
     * 根据内容类型判断文件扩展名
     * 采用apache的tika
     *
     * @param contentType 内容类型
     * @return
     */
    protected static String getFileExt(String contentType) {
        if (contentType == null) {
            return "";
        }
        try {
            MimeType mimeType = allTypes.forName(contentType);
            return mimeType.getExtension(); //不存在返回""
        } catch (MimeTypeException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * asd
     *
     * @param contentType
     * @param conn
     * @param savePath
     * @return
     */
    private static ResultState contentType(String contentType, HttpURLConnection conn, String savePath) {
        ResultState result = new ResultState();
        if (conn == null) {
            result.setErrcode(-1);
            result.setErrmsg("Connect Error");
            return result;
        }
        try {
            InputStream input = conn.getInputStream();
            String typeExt = getFileExt(contentType);
            String type = StringUtils.substringAfter(typeExt, ".");

            if (StringUtils.equals(contentType, MimeTypes.PLAIN_TEXT) || StringUtils.equals(contentType, MediaType.APPLICATION_JSON_VALUE)) {
                result.setErrmsg(IOUtils.toString(input, StandardCharsets.UTF_8));
            } else {
                result = inputToMedia(input, savePath, type);
            }
        } catch (Exception ex) {
            result.setErrcode(-1);
            result.setErrmsg("Connect Error");
        }
        return result;
    }

    /**
     * 将字符流转换为图片文件
     *
     * @param input    字符流
     * @param savePath 图片需要保存的路径
     * @param type     jpg/png等
     * @return
     */
    private static ResultState inputToMedia(InputStream input, String savePath, String type) {
        ResultState result = new ResultState();
        File file = new File(savePath);
        String paramPath = file.getParent(); // 路径
        String fileName = file.getName(); //
        String newName = fileName.substring(0, fileName.lastIndexOf(".")) + "." + type;// 根据实际返回的文件类型后缀
        savePath = paramPath + "\\" + newName;
        if (!file.exists()) {
            File dirFile = new File(paramPath);
            dirFile.mkdirs();
        }
        file = new File(savePath);
        try {
            IOUtils.copy(input, new FileOutputStream(file));
            result.setErrmsg("save success!");
        } catch (IOException e) {
            result.setErrcode(-1);
            result.setErrmsg("save failed!");
        }
        return result;
    }

    /**
     * 将网络URL转换成文件流
     *
     * @param url    网络URL
     * @param output 输出流
     * @return
     */
    public static void toOutput(String url, OutputStream output) throws IOException {
        URL path = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) path.openConnection();
        connection.setDoInput(true);
        connection.connect();
        try (InputStream input = connection.getInputStream()) {
            IOUtils.copy(input, output);
            connection.disconnect();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(getFileExt("image/jpeg"));
    }
}



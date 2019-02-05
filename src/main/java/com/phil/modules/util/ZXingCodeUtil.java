/**
 * FileName: ZXingCodeUtil
 * Author:   Phil
 * Date:     8/1/2018 15:45
 * Description: ZXing二维码生成/解码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.phil.modules.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 〈ZXing二维码生成/解码〉
 *
 * @author Phil
 * @create 8/1/2018 15:45
 * @since 1.0.0
 */
public class ZXingCodeUtil {

    private ZXingCodeUtil() {
    }

    private static final int DEFAULT_WIDTH = 400;

    private static final int DEFAULT_HEIGHT = 400;

    /**
     * 生成二维码图片
     *
     * @param content
     * @param logoPath
     * @param savePath
     * @param remark
     * @return
     */
    public static String toQRCode(String content, String logoPath, String savePath, String remark) {
        try {
            BufferedImage bim = toBufferedImage(content, BarcodeFormat.QR_CODE, DEFAULT_WIDTH, DEFAULT_HEIGHT, toDecodeHintType());
            return encode(bim, logoPath, savePath, new LogoConfig(), remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否需要给二维码图片添加Logo
     *
     * @param bim
     * @param logoPath
     * @param savePath
     * @param logoConfig
     * @param remark
     * @return
     */
    private static String encode(BufferedImage bim, String logoPath, String savePath, LogoConfig logoConfig, String remark) {
        ByteArrayOutputStream baos = null;
        try {
            /**
             * 读取二维码图片
             */
            BufferedImage image = bim;
            if (StringUtils.isBlank(logoPath)) { //不需要添加logo
                baos = new ByteArrayOutputStream();
                baos.flush();
                ImageIO.write(image, "png", baos);//流输出
                //ImageIO.write(bim, "png", new File(savePath));//直接写入某路径，本地测试加上
                return Base64.encodeBase64URLSafeString(baos.toByteArray());//Encodes binary data using a URL-safe variation of the base64 algorithm
            }
            /**
             * 构建绘图对象
             */
            Graphics2D g = image.createGraphics();
            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(new File(logoPath));
            /**
             * 设置logo的大小,设置为二维码图片的20%,因为过大会盖掉二维码
             */
            int widthLogo = logo.getWidth(null) > image.getWidth() * 3 / 10 ? (image.getWidth() * 3 / 10)
                    : logo.getWidth(null),
                    heightLogo = logo.getHeight(null) > image.getHeight() * 3 / 10 ? (image.getHeight() * 3 / 10)
                            : logo.getWidth(null);
            /**
             * logo放在中心
             */
            int x = (image.getWidth() - widthLogo) / 2;
            int y = (image.getHeight() - heightLogo) / 2;
            /**
             * logo放在右下角 int x = (image.getWidth() - widthLogo); int y = (image.getHeight() - heightLogo);
             */
            // 开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            // g.drawRoundRect(x, y, widthLogo, heightLogo, 15, 15);
            // g.setStroke(new BasicStroke(logoConfig.getBorder()));
            // g.setColor(logoConfig.getBorderColor());
            // g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();
            // 把备注添加上去，备注不要太长超过两行会自动截取
            if (StringUtils.isNotBlank(remark)) {
                // 新的图片，把带logo的二维码下面加上文字
                BufferedImage outImage = new BufferedImage(400, 445, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                // 画二维码到新的面板
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                // 画文字到新的面板
                outg.setColor(Color.BLACK);
                outg.setFont(new Font("微软雅黑", Font.BOLD, 30)); // 字体、字型、字号
                int strWidth = outg.getFontMetrics().stringWidth(remark);
                if (strWidth > 399) {
                    // //长度过长就截取前面部分
                    // outg.drawString(productName, 0, image.getHeight() +
                    // (outImage.getHeight() - image.getHeight())/2 + 5 ); //画文字
                    String productName1 = remark.substring(0, remark.length() / 2);
                    String productName2 = remark.substring(remark.length() / 2, remark.length());
                    int strWidth1 = outg.getFontMetrics().stringWidth(productName1);
                    int strWidth2 = outg.getFontMetrics().stringWidth(productName2);
                    outg.drawString(productName1, 200 - strWidth1 / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12);
                    BufferedImage outImage2 = new BufferedImage(400, 485, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D outg2 = outImage2.createGraphics();
                    outg2.drawImage(outImage, 0, 0, outImage.getWidth(), outImage.getHeight(), null);
                    outg2.setColor(Color.BLACK);
                    outg2.setFont(new Font("宋体", Font.BOLD, 30)); // 字体、字型、字号
                    outg2.drawString(productName2, 200 - strWidth2 / 2, outImage.getHeight() + (outImage2.getHeight() - outImage.getHeight()) / 2 + 5);
                    outg2.dispose();
                    outImage2.flush();
                    outImage = outImage2;
                } else {
                    outg.drawString(remark, 200 - strWidth / 2, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 12); // 画文字
                }
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            logo.flush();
            image.flush();
            baos = new ByteArrayOutputStream();
            baos.flush();
            //ImageIO.write(image, "png", baos); //不用MatrixToImageWriter
            ImageIO.write(image, "png", new File(savePath));//直接写入某路径
            return Base64.encodeBase64URLSafeString(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成二维码bufferedImage图片
     *
     * @param content       编码内容
     * @param barcodeFormat 编码类型
     * @param width         图片宽度
     * @param height        图片高度
     * @param hints         设置参数
     * @return
     */
    private static BufferedImage toBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height,
                                                 Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bitMatrix = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bitMatrix = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bitMatrix.getWidth();
            int h = bitMatrix.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? MatrixToImageConfig.BLACK : MatrixToImageConfig.WHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码的格式参数
     *
     * @return
     */
    private static Map<EncodeHintType, Object> toDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.MARGIN, 0);
        //hints.put(EncodeHintType.MAX_SIZE, 350);//Only applicable to Data Matrix now
        //hints.put(EncodeHintType.MIN_SIZE, 100);//Only applicable to Data Matrix now
        return hints;
    }

    /**
     * 二维码解码
     *
     * @param imgPath
     * @return
     */
    public static String decode(String imgPath) {
        BufferedImage image = null;
        Result result = null;
        try {
            File file = new File(imgPath);
            image = ImageIO.read(file);
            if (image == null) {
                System.out.println("the decode image may be not exit.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            result = new MultiFormatReader().decode(bitmap, hints);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Logo图片配置
     *
     * @author phil
     * @date 2017年8月30日
     */
    static class LogoConfig {
        // logo默认边框颜色
        private static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
        // logo默认边框宽度
        private static final int DEFAULT_BORDER = 2;
        // logo大小默认为照片的1/5
        private static final int DEFAULT_LOGOPART = 5;

        private final Color borderColor;

        private final int logoPart;

        /**
         * Creates a default config with on color {@link #Color.BLACK} and off color
         * {@link #Color.WHITE}, generating normal black-on-white barcodes.
         */
        public LogoConfig() {
            this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
        }

        public LogoConfig(Color borderColor, int logoPart) {
            this.borderColor = borderColor;
            this.logoPart = logoPart;
        }

        public Color getBorderColor() {
            return borderColor;
        }

        public int getBorder() {
            int border = DEFAULT_BORDER;
            return border;
        }

        public int getLogoPart() {
            return logoPart;
        }
    }
}



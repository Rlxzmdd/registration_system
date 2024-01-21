package com.withmore.common.utils;
import cn.hutool.core.codec.Base64;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

@Data
@Component
public class QRCodeUtils {
    private static final String UNICODE = "utf-8";
    private static final String QRCODE_IMAGE_TYPE = "PNG";
    public static final String IMAGE_HEADER = "data:image/png;base64,";

    /**
     * 生成二维码图片
     *
     * @param content  二维码内容
     * @param logoPath 图片地址
     * @return BufferedImage
     */
    public static BufferedImage qrcodeImageBase64(String content, String logoPath, Integer height, Integer width) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, UNICODE);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height,
                hints);
        width = bitMatrix.getWidth();
        height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (logoPath == null || "".equals(logoPath)) {
            return image;
        }
        // 插入LOGO
        QRCodeUtils.insertImage(image, logoPath, width, height);
        return image;
    }

    /**
     * @param content 二维码内容
     * @param height  图片高度
     * @param width   图片宽度
     * @return qrcode images base64
     */
    public static String qrcodeImageBase64(String content, Integer height, Integer width) throws Exception {
        BufferedImage image = qrcodeImageBase64(content, null, height, width);
        return ConvertToBase64(image);
    }

    /**
     * 插入LOGO
     *
     * @param source   二维码图片
     * @param logoPath LOGO图片地址
     */
    private static void insertImage(BufferedImage source, String logoPath, Integer qrcode_width, Integer qrcode_height) throws Exception {
        File file = new File(logoPath);
        if (!file.exists()) {
            throw new Exception("logo file not found.");
        }
        // 将LOGO 图缩小为二维码图的百分之15
        Image src = ImageIO.read(new File(logoPath));
        int width = Double.valueOf(qrcode_width * 0.15).intValue();
        int height = Double.valueOf(qrcode_height * 0.15).intValue();
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        src = image;

        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (qrcode_width - width) / 2;
        int y = (qrcode_height - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 解析二维码
     *
     * @param path 二维码图片路径
     * @return String 二维码内容
     */
    public static String decode(String path) throws Exception {
        File file = new File(path);
        BufferedImage image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, UNICODE);
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 解析base64信息
     *
     * @param base64 信息
     * @return String 二维码内容
     */
    public static String decodeBase64(String base64) throws Exception {
        byte[] img_base64 = Base64.decode(base64);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(img_base64);
        BufferedImage image = ImageIO.read(inputStream);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, UNICODE);
        result = new MultiFormatReader().decode(bitmap, hints);
        return result.getText();
    }

    /**
     * 将BufferedImage 对象转换为Base64 字符串
     *
     * @param image bufferedImage Object
     * @return image base64 格式
     */
    public static String ConvertToBase64(BufferedImage image) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String base64 = null;
        try {
            ImageIO.write(image, QRCODE_IMAGE_TYPE, outputStream);
            base64 = Base64.encode(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}

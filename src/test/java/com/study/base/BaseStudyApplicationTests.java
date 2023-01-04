package com.study.base;

import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.study.sign.entity.SignatureInfo;
import com.study.sign.utils.ItextPdfUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;

import static com.study.sign.utils.ItextPdfUtil.PASSWORD;

@SpringBootTest
class BaseStudyApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void signTest(){
        try {
            // 将证书文件放入指定路径，并读取keystore ，获得私钥和证书链
            String pkPath = "D:\\Project\\Java Project\\base_study\\src\\main\\resources\\signs\\swd.p12";
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(pkPath), PASSWORD);
            String alias = ks.aliases().nextElement();
            PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
            // 得到证书链
            Certificate[] chain = ks.getCertificateChain(alias);

            //需要进行签章的pdf
            String path = "D:\\Project\\Java Project\\base_study\\src\\main\\resources\\signs\\sign_pdf.pdf";

            // 封装签章信息
            SignatureInfo signInfo = new SignatureInfo();
            signInfo.setReason("理由");
            signInfo.setLocation("位置");
            signInfo.setPk(pk);
            signInfo.setChain(chain);
            signInfo.setCertificationLevel(PdfSignatureAppearance.NOT_CERTIFIED);
            signInfo.setDigestAlgorithm(DigestAlgorithms.SHA1);
            signInfo.setFieldName("demo");

            // 签章图片
            signInfo.setImagePath("D:\\Project\\Java Project\\base_study\\src\\main\\resources\\signs\\章.png");
            signInfo.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
            // 值越大，代表向x轴坐标平移 缩小 （反之，值越小，印章会放大）
            signInfo.setRectllx(70);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectlly(300);
            // 值越大, 代表向x轴坐标向右平移（大小不变）
            signInfo.setRecturx(300);
            // 值越大，代表向y轴坐标向上平移（大小不变）
            signInfo.setRectury(400);

            //签章后的pdf路径
            ItextPdfUtil itextPdf = new ItextPdfUtil();
            itextPdf.sign(path, "D:\\Project\\Java Project\\base_study\\src\\main\\resources\\signs\\sign_pdf_out.pdf", signInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}

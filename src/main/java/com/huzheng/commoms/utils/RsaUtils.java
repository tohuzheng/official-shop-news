package com.huzheng.commoms.utils;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.Cipher;

/**
 * @Author 胡正
 * @Date 2020/3/10 17:49
 * @Description Rsa加密解密工具类
 */
public class RsaUtils {

    /**
     * 获取密钥对
     * @return Map集合，key-private，key-public
     */
    public static Map<String,String> getKey(){
        Map<String,String> map=new HashMap<>();
        String ALGORITHM = "RSA";
        int KEYSIZE = 1024;
        // RSA算法要求有一个可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        // 为RSA算法创建一个KeyPairGenerator对象
        KeyPairGenerator keyPairGenerator=null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            // 利用上面的随机数据源初始化这个KeyPairGenerator对象
            keyPairGenerator.initialize(KEYSIZE, secureRandom);
            // 生成密匙对
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            // 得到私钥
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            // 得到公钥
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            // 得到公钥字符串
            String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

            //存入map
            map.put("public", publicKeyString);
            map.put("private",privateKeyString);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return map;
    }


    /**
     * RSA公钥加密
     * @param str 需要加密的字符串
     * @param publicKey 公钥字符串
     * @return 加密后的结果
     * @throws Exception
     * @throws NoSuchAlgorithmException
     */
    public static String encryptRsa(String str, String publicKey) throws NoSuchAlgorithmException, Exception {
        //base64编码的公钥
        byte[] decoded = Base64.getDecoder().decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 明文
     * @throws InvalidKeySpecException
     * @throws Exception 解密过程中的异常信息
     */
    public static String decodeRsa(String str, String privateKey) throws InvalidKeySpecException, Exception {
        //64位解码加密后的字符串
        byte[] inputByte = Base64.getDecoder().decode(str);
        //base64编码的私钥
        byte[] decoded = Base64.getDecoder().decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }


    /**
     * @author zheng.hu
     * @date 2020/3/10 17:53
     * @description 测试方法
     */
    public static void main(String[] args) throws Exception {

        Map<String,String> keyValue=RsaUtils.getKey();
        System.out.println(keyValue);
        String miwen=RsaUtils.encryptRsa("123456", keyValue.get("public"));
        System.out.println("密文："+miwen);
        String jiemi=RsaUtils.decodeRsa(miwen, keyValue.get("private"));
        System.out.println("解密："+jiemi);

    }

}

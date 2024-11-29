package com.liguo.demo.core.service.superman;

import com.liguo.demo.core.util.EncodeURIUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/2/27 11:34
 * @since 0.0.1
 */
@Slf4j
@Service
public class ClientTest {
    @Autowired
    private RestTemplate restTemplate;

    //http://order.toopay.cn/api/V2createOrder?token=77a81314d134b03ad8e32b8a35d23c22&amount=100&phone=15570888040&out_trade_no=12345678&callback_url=http://103.150.181.88/api.php/apinotify/crhfh&sign=R7gZLnAJDPzMlKegL0%2B2b%2BJ%2F0jYLxTTDMs2lRnAebTyMZk6k8Y82THt%2BfBJz33K8e%2FFpERR07weE71WJWqmFpj3t5MafyP2XqLMB1T6Aj%2BCs2FcYARZgWkFu1EqBpPH3GXnzWrKCG%2FFBGvfgk3xWR8oo287Jnb9LiYwODHYd5DE%3D
    public String get(String order, String phone, String amount) throws UnsupportedEncodingException {
        //公钥
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpp796m0pgb8MUG9QO1B/ykUsx9ZXWeRjn5LBOQHiibLeTBrs10nMSNhe+43/du/i6gkA7vfTivVhz1MOYWCAaLOyQaC8vcWqVS9Kf7zfQ/XIN0mGn89FEd3UurVetAGBoYIN73b++TfHdpO0a2paeoep2zOc5oOdA/hkpjAVlKQIDAQAB";
        //私钥
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKmnv3qbSmBvwxQb1A7UH/KRSzH1ldZ5GOfksE5AeKJst5MGuzXScxI2F77jf927+LqCQDu99OK9WHPUw5hYIBos7JBoLy9xapVL0p/vN9D9cg3SYafz0UR3dS6tV60AYGhgg3vdv75N8d2k7Rralp6h6nbM5zmg50D+GSmMBWUpAgMBAAECgYBER4cAyU790P0qMqj97tGMxn0E9ln/n20upxNt6vSiKi8XtTXTS4eUZMDZyqXDj8tjBNjlMmZEewAGXjL6NT11o6HOp616h7iVy0VzN4M15p7MxOVcbE/IRJE8sqRhghvykB0SqPFbLIeoKxn1OMp+tNdJQ26kMctKy0gTAORGoQJBANWelX8O+oLn+UrcooEW6BMLdTrzcM4HAqAYcLHabX4sv37uckINn3k7PWG8ejyjVy5RrrRE0By22sQvaaAEuDcCQQDLUEmNTsXssa/r4yL2faUI8qMdlS5bewNy83D/SJBGhDYUmW+7febyMQdZm+54N4j906fsnCBeiRrEj8YzrV2fAkBRw+KbAKO4iYwtMGhvGtJ3v7H+f4HVCm/h0ZkJKn8whXZC4ogPamF5KMik6VysayUKkK9bRt+VrhPsde1rF4NfAkAr6NkZxauFZRLDdTi114jvF8gWuYNbioPCLcV3vOo78I3lgQXajmR0ZhZaSg0EtanGeBHKTiXHhQDc9tgtjQGtAkBepj6JIZhJIT5vFw0aVz0xqM6F/y2IIJXYwRcvt6C17bK2fg4bkyEC1DgQsNLWScOHHZf8sluo0TuEbXs4pNh7";
        // 1、待加密数据
        String data = order + phone + amount;
        // 单号+电话号码+金额
        String rsaData =digitalSign(data, privateKey);
        // 2、encodeURI编码
        rsaData = EncodeURIUtil.encodeURI(rsaData);
        // 3、组装url发请求
        String url = String.format("http://order.toopay.cn/api/V2createOrder?token=77a81314d134b03ad8e32b8a35d23c22&amount=%s&phone=%s&out_trade_no=%s&callback_url=http://103.150.181.88/api.php/apinotify/crhfh&sign=%s", amount, phone, order, rsaData);
        log.info("完整url:{}", url);
        ResponseEntity<String> responseEntity = restTemplate

                .exchange(url, HttpMethod.GET, null, String.class);

        String a =  responseEntity.getBody();
        System.out.println("私钥签名数据:\n" + rsaData);
        //验证签名【公钥验证】
        boolean bool = signVerify(data, rsaData, publicKey);
        System.out.println("数字签名是否有效？:\n" + bool);
        log.info("返回:{}", a);
        return a;
    }

    public String query(String order, String phone, String amount) throws UnsupportedEncodingException {
        //公钥
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpp796m0pgb8MUG9QO1B/ykUsx9ZXWeRjn5LBOQHiibLeTBrs10nMSNhe+43/du/i6gkA7vfTivVhz1MOYWCAaLOyQaC8vcWqVS9Kf7zfQ/XIN0mGn89FEd3UurVetAGBoYIN73b++TfHdpO0a2paeoep2zOc5oOdA/hkpjAVlKQIDAQAB";
        //私钥
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKmnv3qbSmBvwxQb1A7UH/KRSzH1ldZ5GOfksE5AeKJst5MGuzXScxI2F77jf927+LqCQDu99OK9WHPUw5hYIBos7JBoLy9xapVL0p/vN9D9cg3SYafz0UR3dS6tV60AYGhgg3vdv75N8d2k7Rralp6h6nbM5zmg50D+GSmMBWUpAgMBAAECgYBER4cAyU790P0qMqj97tGMxn0E9ln/n20upxNt6vSiKi8XtTXTS4eUZMDZyqXDj8tjBNjlMmZEewAGXjL6NT11o6HOp616h7iVy0VzN4M15p7MxOVcbE/IRJE8sqRhghvykB0SqPFbLIeoKxn1OMp+tNdJQ26kMctKy0gTAORGoQJBANWelX8O+oLn+UrcooEW6BMLdTrzcM4HAqAYcLHabX4sv37uckINn3k7PWG8ejyjVy5RrrRE0By22sQvaaAEuDcCQQDLUEmNTsXssa/r4yL2faUI8qMdlS5bewNy83D/SJBGhDYUmW+7febyMQdZm+54N4j906fsnCBeiRrEj8YzrV2fAkBRw+KbAKO4iYwtMGhvGtJ3v7H+f4HVCm/h0ZkJKn8whXZC4ogPamF5KMik6VysayUKkK9bRt+VrhPsde1rF4NfAkAr6NkZxauFZRLDdTi114jvF8gWuYNbioPCLcV3vOo78I3lgQXajmR0ZhZaSg0EtanGeBHKTiXHhQDc9tgtjQGtAkBepj6JIZhJIT5vFw0aVz0xqM6F/y2IIJXYwRcvt6C17bK2fg4bkyEC1DgQsNLWScOHHZf8sluo0TuEbXs4pNh7";
        // 1、待加密数据
        String data = order + phone + amount;
        // 单号+电话号码+金额
        String rsaData =digitalSign(data, privateKey);
        // 2、encodeURI编码
        rsaData = EncodeURIUtil.encodeURI(rsaData);
        // 3、组装url发请求
        String url = String.format("http://order.toopay.cn/api/V2getOrder?act=order&orderId=%s&phone=%s&out_trade_no=%s&callback_url=http://103.150.181.88/api.php/apinotify/crhfh&sign=%s", amount, phone, order, rsaData);
        log.info("完整url:{}", url);
        ResponseEntity<String> responseEntity = restTemplate

                .exchange(url, HttpMethod.GET, null, String.class);

        String a =  responseEntity.getBody();
        System.out.println("私钥签名数据:\n" + rsaData);
        //验证签名【公钥验证】
        boolean bool = signVerify(data, rsaData, publicKey);
        System.out.println("数字签名是否有效？:\n" + bool);
        log.info("返回:{}", a);
        return a;
    }

    public static void main(String[] args) throws Exception {

        //公钥
        String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCpp796m0pgb8MUG9QO1B/ykUsx9ZXWeRjn5LBOQHiibLeTBrs10nMSNhe+43/du/i6gkA7vfTivVhz1MOYWCAaLOyQaC8vcWqVS9Kf7zfQ/XIN0mGn89FEd3UurVetAGBoYIN73b++TfHdpO0a2paeoep2zOc5oOdA/hkpjAVlKQIDAQAB";
        //私钥
        String privateKey="MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAKmnv3qbSmBvwxQb1A7UH/KRSzH1ldZ5GOfksE5AeKJst5MGuzXScxI2F77jf927+LqCQDu99OK9WHPUw5hYIBos7JBoLy9xapVL0p/vN9D9cg3SYafz0UR3dS6tV60AYGhgg3vdv75N8d2k7Rralp6h6nbM5zmg50D+GSmMBWUpAgMBAAECgYBER4cAyU790P0qMqj97tGMxn0E9ln/n20upxNt6vSiKi8XtTXTS4eUZMDZyqXDj8tjBNjlMmZEewAGXjL6NT11o6HOp616h7iVy0VzN4M15p7MxOVcbE/IRJE8sqRhghvykB0SqPFbLIeoKxn1OMp+tNdJQ26kMctKy0gTAORGoQJBANWelX8O+oLn+UrcooEW6BMLdTrzcM4HAqAYcLHabX4sv37uckINn3k7PWG8ejyjVy5RrrRE0By22sQvaaAEuDcCQQDLUEmNTsXssa/r4yL2faUI8qMdlS5bewNy83D/SJBGhDYUmW+7febyMQdZm+54N4j906fsnCBeiRrEj8YzrV2fAkBRw+KbAKO4iYwtMGhvGtJ3v7H+f4HVCm/h0ZkJKn8whXZC4ogPamF5KMik6VysayUKkK9bRt+VrhPsde1rF4NfAkAr6NkZxauFZRLDdTi114jvF8gWuYNbioPCLcV3vOo78I3lgQXajmR0ZhZaSg0EtanGeBHKTiXHhQDc9tgtjQGtAkBepj6JIZhJIT5vFw0aVz0xqM6F/y2IIJXYwRcvt6C17bK2fg4bkyEC1DgQsNLWScOHHZf8sluo0TuEbXs4pNh7";
        //待加密数据
        String data = "88888888100";
        System.out.println("待加密数据:\n" + data);
        //执行数字签名【私钥签名】
        String rsaData =digitalSign(data, privateKey);
        System.out.println("私钥签名数据:\n" + rsaData);
        //验证签名【公钥验证】
        boolean bool = signVerify(data, rsaData, publicKey);
        System.out.println("数字签名是否有效？:\n" + bool);

    }

    /** 执行数字签名【私钥签名】
     * @param data 待加密数据
     * @param privKey  私钥
     * @return String 加密数据
     * @throws Exception
     */
    public static String digitalSign(String data, String privKey) {
        try {
            PrivateKey privateKey = (PrivateKey) KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privKey)));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());
            return Base64.getEncoder().encodeToString(signature.sign());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /** 验证签名【公钥验证】
     * @param data 待加密数据
     * @param rsaData  待验签的加密数据
     * @param pubKey  公钥
     * @return boolean 验签是否成功一致
     * @throws Exception
     */
    public static boolean signVerify(String data, String rsaData, String pubKey) {
        try {
            PublicKey publicKey = (PublicKey) KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pubKey)));
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes());
            return signature.verify(Base64.getDecoder().decode(rsaData));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

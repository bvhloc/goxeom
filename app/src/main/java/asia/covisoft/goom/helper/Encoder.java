package asia.covisoft.goom.helper;

import android.annotation.SuppressLint;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encoder {

    public static String encrypt(String key, String data)  {

        String returnData = "";

        try {
            byte[] raw = Encoder.generateKey(key);
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            @SuppressLint("GetInstance")
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

            byte[] clear = data.getBytes("UTF-8");

            byte[] encrypted = cipher.doFinal(clear);

            returnData = new String(encrypted, "UTF-8");

        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return returnData;
    }

    public static String decrypt(String key, String data) throws Exception {

        byte[] raw = Encoder.generateKey(key);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        @SuppressLint("GetInstance")
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] encrypted = data.getBytes("UTF-8");

        byte[] decrypted = cipher.doFinal(encrypted);

        return new String(decrypted, "UTF-8");
    }

    private static byte[] generateKey(String key) throws NoSuchAlgorithmException {

        byte[] keyStart = key.getBytes();
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        sr.setSeed(keyStart);
        kgen.init(128, sr); // 192 and 256 bits may not be available
        SecretKey skey = kgen.generateKey();
        byte[] genKey = skey.getEncoded();

        return genKey;
    }
}

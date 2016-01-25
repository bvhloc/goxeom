package asia.covisoft.goom.helper;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class Hex {

    public static String encode(String str) {

        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        @SuppressWarnings("")
        String hexString = builder.toString();

        return hexString;
    }

    public static String decode(String hex) {

        if(hex.equals(""))
            return "";

        byte[] bytes = new BigInteger(hex, 16).toByteArray();
        String str = "";
        try {
            str = new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }
}

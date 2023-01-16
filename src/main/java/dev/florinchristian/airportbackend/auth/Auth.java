package dev.florinchristian.airportbackend.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Auth {
    public static String getMD5Hash(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] raw = md.digest(s.getBytes());

        BigInteger bi = new BigInteger(1, raw);

        String result = bi.toString(16);

        while (result.length() < 32)
            result = "0" + result;

        return result;
    }
}

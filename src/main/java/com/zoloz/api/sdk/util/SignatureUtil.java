package com.zoloz.api.sdk.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;

public class SignatureUtil {

    private static final String HMAC_SHA256 = "HmacSHA256";

    /**
     * Generate signature
     *
     * @param data      Data to be signed
     * @param secretKey Secret key for signing
     * @return Signature result
     */
    @SneakyThrows(NoSuchAlgorithmException.class)
    public static String sign(String data, String secretKey) {
        // Decode Base64-encoded secret key
        try {
            byte[] decodedKey = Base64.getUrlDecoder().decode(secretKey);
            // Create SecretKeySpec and initialize MAC
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, HMAC_SHA256);
            Mac mac = Mac.getInstance(HMAC_SHA256);
            mac.init(secretKeySpec);
            // Calculate signature and return result (URL-safe Base64, no padding)
            byte[] signatureBytes = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException("invalid key", e);
        }
    }

    /**
     * Verify signature
     *
     * @param data      Data to be verified
     * @param secretKey Secret key for verification
     * @param signature Signature to verify
     * @return Verification result (true/false)
     */
    public static boolean verify(String data, String secretKey, String signature) {
        String calculatedSignature = sign(data, secretKey);
        return calculatedSignature.equals(signature);
    }

}

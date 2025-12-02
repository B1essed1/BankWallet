package uz.sqb.bankwallet.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;

public class Utils {

    // Regex for international phone number format: +[country code][operator code][subscriber number]
    // Example: +998901234567 (Uzbekistan format)
    private static final Pattern PHONE_PATTERN =Pattern.compile("^\\+998(33|88|90|91|93|94|95|97|98|99)\\d{7}$");

    private static final SecureRandom random = new SecureRandom();

    /**
     * Encodes a raw password using SHA-256
     */
    public static String encode(String rawPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Matches a raw password with an encoded password
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        String hashedInput = encode(rawPassword);
        return hashedInput.equals(encodedPassword);
    }

    /**
     * Converts byte array to hex string
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Validates phone number in international format
     * Format: +[country code][operator code][subscriber number]
     * Example: +998901234567
     *
     * @param phoneNumber the phone number to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        return PHONE_PATTERN.matcher(phoneNumber).matches();
    }
    public static String generateWalletNumber() {
        // X = 999
        String prefix = "999";

        // Last 7 digits of current timestamp
        long timestamp = System.currentTimeMillis() % 10_000_000;
        String timestampPart = String.format("%07d", timestamp);

        // 6 random digits
        int randomPart = random.nextInt(1_000_000);
        String randomSix = String.format("%06d", randomPart);

        // Combine prefix + timestamp + random(6)
        String base = prefix + timestampPart + randomSix;

        // Compute Luhn checksum
        int checksum = luhnChecksum(base);

        // Final wallet number
        return base+ checksum;
    }

    // Luhn algorithm checksum generator
    private static int luhnChecksum(String number) {
        int sum = 0;
        boolean alternate = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int n = number.charAt(i) - '0';

            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }

            sum += n;
            alternate = !alternate;
        }

        return (10 - (sum % 10)) % 10;
    }
}

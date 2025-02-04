package com.mirkwood.logistics.features.parcel.tracking;
import java.security.SecureRandom;

//TODO: we testing number generator, we modify later
public class TrackingNumberGenerator {
    private static final String PREFIX = "MW";
    private static final int BASE_LENGTH = 9;
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateTrackingNumber() {
        StringBuilder trackingNumber = new StringBuilder();

        for (int i = 0; i < BASE_LENGTH; i++) {
            trackingNumber.append(secureRandom.nextInt(10));
        }

        int checkDigit = calculateLuhnCheckDigit(trackingNumber.toString());
        trackingNumber.append(checkDigit);

        return PREFIX + trackingNumber;
    }

    private static int calculateLuhnCheckDigit(String number) {
        int sum = 0;
        boolean doubleDigit = true;
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (10 - (sum % 10)) % 10;
    }
}

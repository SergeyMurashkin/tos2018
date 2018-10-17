package net.thumbtack.school.concert;

import java.security.SecureRandom;

public class TokenGenerator {

    private final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final int LENGTH = 24;
    private SecureRandom rnd = new SecureRandom();

    public String generateToken() {
        StringBuilder sb = new StringBuilder(LENGTH);
        for (int i = 0; i < LENGTH; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}

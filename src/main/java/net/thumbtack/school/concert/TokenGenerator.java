package net.thumbtack.school.concert;

import java.util.UUID;

public class TokenGenerator {

    public String generateToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

}

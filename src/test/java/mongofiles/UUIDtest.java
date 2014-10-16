package mongofiles;

import java.util.UUID;

public class UUIDtest {
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    }
}

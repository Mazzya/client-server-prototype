package client;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Scanner;

public class ClientTest {
    Scanner sc;

    @Test
    public void verificationUsername() {
        String username = "";
        if (username.isEmpty()) {
            System.out.println("Tienes que introducir un nombre");
            assertEquals("Tienes que introducir un nombre", username);
        }
    }
}

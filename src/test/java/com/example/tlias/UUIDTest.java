package com.example.tlias;

import org.junit.jupiter.api.Test;

public class UUIDTest {
    
    @Test
    public void testUUID(){
        for(int i=0;i<10;i++){
            System.out.println(java.util.UUID.randomUUID());
        }
    }
}

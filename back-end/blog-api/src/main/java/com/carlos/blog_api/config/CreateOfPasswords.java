package com.carlos.blog_api.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateOfPasswords {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

           String senhaCriptografada = bCryptPasswordEncoder.encode("1");
        System.out.println(senhaCriptografada);


//        boolean senhaCorreta =bCryptPasswordEncoder.matches("carlos", "$2a$10$Cc0OSeZpJ8qL7FwoWRL6oeDeUHvAVg3yaI1Uz.w0dCSxb7cADKgnC");
//        System.out.println(senhaCorreta);
    }

}
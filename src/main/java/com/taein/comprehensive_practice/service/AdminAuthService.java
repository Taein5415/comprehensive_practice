package com.taein.comprehensive_practice.service;

import com.taein.comprehensive_practice.repository.CashRepository;
import com.taein.comprehensive_practice.repository.PasswordRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class AdminAuthService {
    private final PasswordRepository passwordRepository;
    private static AdminAuthService instance;
    private AdminAuthService(){
        passwordRepository = PasswordRepository.getInstance();
    }

    public static AdminAuthService getInstance(){
        if (instance == null) {
            instance = new AdminAuthService();
        }
        return instance;
    }

    // 관리자 인증 (비밀번호 확인)
    public boolean authenticateAdmin(String inputPassword) {
        Optional<String> optionalPassword = passwordRepository.select(inputPassword);
        if (optionalPassword.isEmpty()) {
            return false;
        }
        return true;

    }

}


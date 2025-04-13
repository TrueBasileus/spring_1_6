package com.example.spring_1_6.service.impl;

import com.example.spring_1_6.service.IOService;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Scanner;

@Service
public class ConsoleIOService implements IOService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void print(String message) {
        System.out.print(message);
    }

    @Override
    public String readString() {
        return scanner.nextLine();
    }

    @Override
    public void println(String message) {
        System.out.println(message);
    }

    @PreDestroy
    public void close() {
        scanner.close();
    }
}
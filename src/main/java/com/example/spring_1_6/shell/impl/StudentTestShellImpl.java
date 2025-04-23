package com.example.spring_1_6.shell.impl;

import com.example.spring_1_6.service.StudentTestService;
import com.example.spring_1_6.shell.StudentTestShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class StudentTestShellImpl implements StudentTestShell {
    private StudentTestService studentTestService;

    @Override
    @ShellMethod(value = "Begin test", key = {"t", "test"})
    public String beginTest(){
        return studentTestService.test();
    }
}

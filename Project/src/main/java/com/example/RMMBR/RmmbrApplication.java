package com.example.RMMBR;

import com.example.RMMBR.auth.models.RunActiveTasks;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;
import java.util.Scanner;

@SpringBootApplication
public class RmmbrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmmbrApplication.class, args);
		RunActiveTasks.runAllActiveTasks();
	}
}

package ru.dwfe.auth_nydiarra;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class AppBoot extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(AppBoot.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(AppBoot.class);
    }
}

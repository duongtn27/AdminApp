package com.duongtn.adminapp.config;

import android.content.Context;

import io.github.cdimascio.dotenv.Dotenv;

public class InitConfig {
    private static Dotenv dotenv;

    private void EnvManager() {}

    public static Dotenv getDotenv(Context context) {
        if (dotenv == null) {
            dotenv = Dotenv.configure()
                    .directory("/")
                    .filename(".env")
                    .load();
        }
        return dotenv;
    }
}

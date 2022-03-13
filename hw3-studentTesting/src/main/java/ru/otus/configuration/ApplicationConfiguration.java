package ru.otus.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@ConfigurationProperties("application")
public class ApplicationConfiguration {
    private String language;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Locale getLocale() {
        return new Locale(language);
    }

    public String getCsvFileName() {
        return "questions_" + getLanguage() + ".csv";
    }
}

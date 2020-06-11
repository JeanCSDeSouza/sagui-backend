package br.com.unirio.sagui.pdfupload;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    @Getter@Setter private String uploadDir;
    @Getter@Setter private String processedDir;
}

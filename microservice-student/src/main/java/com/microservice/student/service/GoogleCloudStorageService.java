package com.microservice.student.service;

import com.google.api.gax.paging.Page;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoogleCloudStorageService {
    private final Storage storage;
    private final String bucketName;

    @Value("$gcp.credentials.location")
    private String credentialsPath;
    public GoogleCloudStorageService() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("credentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream);
        this.storage = StorageOptions.newBuilder()
                .setCredentials(credentials)
                .setProjectId("festive-vim-424816-r7")
                .build()
                .getService();
        this.bucketName = "bucket-students";
    }

    public InputStream obtenerArchivoComoInputStream(String nombreArchivo) throws IOException {
        BlobId blobId = BlobId.of(bucketName, nombreArchivo);
        Blob blob = storage.get(blobId);

        if (blob == null) {
            throw new FileNotFoundException("Archivo no encontrado en el bucket");
        }
        return Channels.newInputStream(blob.reader());
    }
    public void uploadFile(MultipartFile file) throws IOException {
        BlobId blobId = BlobId.of(bucketName, file.getOriginalFilename());
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        storage.create(blobInfo, file.getInputStream());
    }

    public List<String> listFiles() {
        List<String> fileNames = new ArrayList<>();
        Page<Blob> blobs = storage.list(bucketName);
        for (Blob blob : blobs.iterateAll()) {
            fileNames.add(blob.getName());
        }
        return fileNames;
    }
}

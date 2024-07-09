package com.microservice.student.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.channels.Channels;
import java.nio.file.Paths;

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
    public void uploadFile(){

    }

}

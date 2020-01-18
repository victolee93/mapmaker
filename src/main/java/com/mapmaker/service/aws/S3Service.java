package com.mapmaker.service.aws;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class S3Service {
    private final AmazonS3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // S3에 업로드
    public String upload(MultipartFile file) throws IOException {
        SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
        String fileName = date.format(new Date()) + "_" +file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null).withCannedAcl(CannedAccessControlList.PublicRead));

        return s3Client.getUrl(bucket, fileName).toString();
    }

}

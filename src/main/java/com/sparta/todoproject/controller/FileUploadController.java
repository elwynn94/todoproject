package com.sparta.todoproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private static final List<String> VALID_MIME_TYPES = Arrays.asList(
            "image/jpeg",
            "image/png",
            "image/gif",
            "image/bmp",
            "image/webp"
    );

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestPart MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        String contentType = file.getContentType();

        if (originalFileName == null || originalFileName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "허용되지 않는 파일명입니다."));
        }

        if (contentType == null || !VALID_MIME_TYPES.contains(contentType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "이미지 파일 업로드만 가능합니다."));
        }

        // Assuming the file is saved successfully
        // Save the file to the server (not implemented here)

        Map<String, String> response = new HashMap<>();
        response.put("message", "파일 업로드가 완료되었습니다.");
        response.put("파일명", originalFileName);
        response.put("확장자명", contentType);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
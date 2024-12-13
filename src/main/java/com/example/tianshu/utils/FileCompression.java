package com.example.tianshu.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class FileCompression {

    public static MultipartFile compressImage(MultipartFile file) throws IOException {
        BufferedImage originalImage = ImageIO.read(file.getInputStream());

        // 设置JPEG压缩参数，quality范围为0.0至1.0，值越小压缩率越高
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(originalImage, "jpg", outputStream);

        byte[] compressedData = outputStream.toByteArray();

        return new MultipartFile() {
            @Override
            public String getName() {
                return file.getName();
            }

            @Override
            public String getOriginalFilename() {
                return file.getOriginalFilename();
            }

            @Override
            public String getContentType() {
                return file.getContentType();
            }

            @Override
            public boolean isEmpty() {
                return compressedData.length == 0;
            }

            @Override
            public long getSize() {
                return compressedData.length;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return compressedData;
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return new ByteArrayInputStream(compressedData);
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {
                try (FileOutputStream fos = new FileOutputStream(dest)) {
                    fos.write(compressedData);
                }
            }
        };
    }
}

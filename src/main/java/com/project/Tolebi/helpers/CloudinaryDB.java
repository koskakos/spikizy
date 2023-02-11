package com.project.Tolebi.helpers;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryDB {
//    public CloudinaryDB() {
//        // Configure
//        Map config = new HashMap();
//        config.put("cloud_name", "dxuquaaez");
//        config.put("api_key", "889215844532248");
//        config.put("api_secret", "ZULhkjFN0yJDoCbXOsWA9GledEg");
//        Cloudinary cloudinary = new Cloudinary(config);
//
//
//        // Upload
//        try {
//            cloudinary.uploader().upload("https://linchakin.com/files/word/1000/212/1.jpg", ObjectUtils.asMap("public_id", "gagdafadga"));
//        } catch (IOException exception) {
//            System.out.println(exception.getMessage());
//        }
//
//        // Transform
////        String url = cloudinary.url().transformation(new Transformation().width(10000).height(150).crop("fill")).generate("olympic_flag");
//        System.out.println(cloudinary.url().generate("gagdafadga"));
//    }

    public static String upload(MultipartFile file, String id) {
        Map config = new HashMap();
        config.put("cloud_name", "dxuquaaez");
        config.put("api_key", "889215844532248");
        config.put("api_secret", "ZULhkjFN0yJDoCbXOsWA9GledEg");
        Cloudinary cloudinary = new Cloudinary(config);
        StringBuilder sb = new StringBuilder();
        // Upload
        try {
//            cloudinary.uploader().upload()

            cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("public_id", id));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        // Transform
//        String url = cloudinary.url().transformation(new Transformation().width(10000).height(150).crop("fill")).generate("olympic_flag");
        return cloudinary.url().generate(id);
    }

    public static void destroy(String publicId) {
        Map config = new HashMap();
        config.put("cloud_name", "dxuquaaez");
        config.put("api_key", "889215844532248");
        config.put("api_secret", "ZULhkjFN0yJDoCbXOsWA9GledEg");
        Cloudinary cloudinary = new Cloudinary(config);

        try{
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

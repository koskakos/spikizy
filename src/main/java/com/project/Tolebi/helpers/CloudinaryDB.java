package com.project.Tolebi.helpers;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CloudinaryDB {
    public CloudinaryDB() {
        // Configure
        Map config = new HashMap();
        config.put("cloud_name", "dxuquaaez");
        config.put("api_key", "889215844532248");
        config.put("api_secret", "ZULhkjFN0yJDoCbXOsWA9GledEg");
        Cloudinary cloudinary = new Cloudinary(config);


        // Upload
        try {
            cloudinary.uploader().upload("https://upload.wikimedia.org/wikipedia/commons/a/ae/Olympic_flag.jpg", ObjectUtils.asMap("public_id", "olympic_flag"));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        // Transform
        String url = cloudinary.url().transformation(new Transformation().width(100).height(150).crop("fill")).generate("olympic_flag");
        System.out.println(url);
    }
}

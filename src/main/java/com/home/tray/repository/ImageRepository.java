package com.home.tray.repository;

import com.home.RunMe;

import javax.inject.Inject;
import java.awt.*;
import java.net.URL;

public class ImageRepository {
    @Inject
    public ImageRepository() {
    }

    public Image loadImageByName(String name) {
        URL resource = RunMe.class.getClassLoader().getResource(name);
        return Toolkit.getDefaultToolkit().getImage(resource);
    }
}

package com.kedacom.imageloader;


import com.kedacom.imageloader.glide.GlideImpl;

/**
 * Copyright ©2017 by ruzhan
 * @deprecated to see {@link  GlideUtil }
 */

public final class ImageLoader {

    private static IImageLoader imageLoader;

    private ImageLoader() {
    }

    public static IImageLoader getInstance() {
        if (imageLoader == null) {
            synchronized (ImageLoader.class) {
                if (imageLoader == null) {
                    imageLoader = new GlideImpl();
                }
            }
        }
        return imageLoader;
    }
}

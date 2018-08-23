package com.kedacom.module_lib.imageloader;


import com.kedacom.module_lib.imageloader.glide.GlideImpl;

/**
 * Copyright ©2017 by ruzhan
 *
 * @deprecated to see {@link  ImageLoaderUtil }
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

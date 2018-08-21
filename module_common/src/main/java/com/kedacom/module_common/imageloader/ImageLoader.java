package com.kedacom.module_common.imageloader;


import com.kedacom.module_common.imageloader.glide.GlideImpl;

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

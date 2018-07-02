package com.kedacom.utils;

import android.content.Context;
import android.os.Environment;


import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * Created by SilenceDut on 17/04/19.
 * Helper class to do operations on regular files/directories.
 */
public class FileHelper {

    private static String mAppName;

    public static void init(String appName) {
        mAppName = appName;
    }

    /**
     * SDCard root
     */
    public static String sdcardRoot() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static String getAppFolder() {
        // Create the application workspace
        File cacheDir = new File(sdcardRoot() + File.separator + mAppName + File.separator);
        if (!cacheDir.exists()) {
            makeDir(cacheDir);
        }
        return cacheDir.getPath();
    }

    public static String getLogTrace() {

        File logDir = new File(sdcardRoot() + File.separator + mAppName + File.separator + "log");
        if (!logDir.exists()) {
            makeDir(logDir);
        }
        return logDir.getPath();
    }

    public static boolean makeDir(File dir) {
        if (!dir.exists()) {
            return dir.mkdirs();
        }
        return (dir.exists() && dir.isDirectory());
    }

    /**
     * Build a file, used to be inserted in the disk cache.
     *
     * @param fileId The name build the file.
     * @return A valid file.
     */
    public static File buildFile(String fileId) {
        File file = new File(getAppFolder(), fileId);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }


    /**
     * 同步读取一个文件，will be block
     */
    public static String readFileContent(String fileId) {
        final File file = buildFile(fileId);
        StringBuilder fileContentBuilder = new StringBuilder();
        if (file.exists()) {
            String stringLine;
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine).append("\n");
                }
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileContentBuilder.toString();
    }


    public static boolean isCached(String fileId) {
        final File entityFile = buildFile(fileId);
        return exists(entityFile);
    }

    public static boolean exists(File file) {
        return file.exists();
    }

    public static String assetFile2String(String fileName, Context context) {
        StringBuilder result = new StringBuilder();
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line;

            while ((line = bufReader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        closeIO(inputReader, bufReader);

        return result.toString();
    }


    /**
     * 关闭IO
     *
     * @param closeables closeable
     */
    public static void closeIO(Closeable... closeables) {
        if (closeables == null) {
            return;
        }
        try {
            for (Closeable closeable : closeables) {
                if (closeable != null) {
                    closeable.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

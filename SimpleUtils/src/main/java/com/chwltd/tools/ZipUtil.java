package com.chwltd.tools;

import java.io.*;
import java.util.zip.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class ZipUtil {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    public static boolean zipFile(String srcDir, String destZipFile, String password) {
		try
		{
        FileOutputStream fos = new FileOutputStream(destZipFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, password);
        addFolderToZip("", srcDir, zos, cipher);
        zos.close();
        fos.close();
		return true;
		}
		catch(Exception e){return false;}
    }

    public static boolean unzipFile(String zipFile, String destDir, String password) {
		try
		{
        FileInputStream fis = new FileInputStream(zipFile);
        ZipInputStream zis = new ZipInputStream(fis);
        Cipher cipher = initCipher(Cipher.DECRYPT_MODE, password);
        ZipEntry entry;
        while ((entry = zis.getNextEntry()) != null) {
            File file = new File(destDir, entry.getName());
            if (!entry.isDirectory()) {
                extractFile(zis, file, cipher);
            } else {
                file.mkdir();
            }
            zis.closeEntry();
        }
        zis.close();
        fis.close();
		return true;
		}catch(Exception e){return false;}
    }

    private static Cipher initCipher(int mode, String password) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(password.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(mode, keySpec);
        return cipher;
    }

    private static void addFolderToZip(String path, String srcFolder, ZipOutputStream zos, Cipher cipher) throws Exception {
        File folder = new File(srcFolder);
        for (String fileName : folder.list()) {
            if (path.equals("")) {
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zos, cipher);
            } else {
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zos, cipher);
            }
        }
    }

    private static void addFileToZip(String path, String srcFile, ZipOutputStream zos, Cipher cipher) throws Exception {
        File file = new File(srcFile);
        if (file.isDirectory()) {
            addFolderToZip(path, srcFile, zos, cipher);
        } else {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            zos.putNextEntry(new ZipEntry(path + "/" + file.getName()));
            byte[] bytes = new byte[1024];
            int length;
            while ((length = bis.read(bytes)) >= 0) {
                zos.write(bytes, 0, length);
            }
            zos.closeEntry();
            bis.close();
            fis.close();
        }
    }

    private static void extractFile(ZipInputStream zis, File file, Cipher cipher) throws Exception {
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        CipherInputStream cis = new CipherInputStream(zis, cipher);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = cis.read(bytes)) >= 0) {
            bos.write(bytes, 0, length);
        }
        bos.close();
        cis.close();
        fos.close();
    }
}

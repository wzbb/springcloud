package com.rb.ribbonconsumer.controller;

import java.io.*;

/**
 * Created by admin on 2020-11-10.
 */
public class FileUploadTest {

    private static int position = -1;

    public static void main(String[] args){
        File sourceFile = new File("D:/", "test.txt");
        File targetFile = new File("D:/", "test22.txt");
        FileInputStream fis = null;
        FileOutputStream fos = null;

        byte[] buf = new byte[1];

        try {

            fis = new FileInputStream(sourceFile);
            fos = new FileOutputStream(targetFile);

            // 数据读写
            while (fis.read(buf) != -1) {
                fos.write(buf);
                // 当已经上传了3字节的文件内容时，网络中断了，抛出异常
                if (targetFile.length() == 3) {
                    position = 3;
                    throw new FileAccessException();
                }
            }
        } catch (FileAccessException e) {
            keepGoing(sourceFile,targetFile, position);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("指定文件不存在");
        } catch (IOException e) {
            // TODO: handle exception
        } finally {
            try {
                // 关闭输入输出流
                if (fis != null)
                    fis.close();

                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void keepGoing(File source,File target, int position) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RandomAccessFile readFile = null;
        RandomAccessFile writeFile = null;
        try {
            readFile = new RandomAccessFile(source, "rw");
            writeFile = new RandomAccessFile(target, "rw");
            System.out.println("sadasdasd===" + writeFile.getFilePointer());
            readFile.seek(position);
            writeFile.seek(position);

            // 数据缓冲区
            byte[] buf = new byte[1];
            // 数据读写
            while (readFile.read(buf) != -1) {
                writeFile.write(buf);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                // 关闭输入输出流
                if (readFile != null){
                    readFile.close();
                }

                if (writeFile != null){
                    writeFile.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    static class FileAccessException extends Exception {

    }

}

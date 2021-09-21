package com.test.class1;

import java.io.*;

public class HelloClassLoader extends ClassLoader{

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException, ClassNotFoundException {

        String path = "E:\\极客学习资料\\class1\\Hello.xlass";

        ClassLoader helloClassLoader = new HelloClassLoader();

        Class<?> clazz = helloClassLoader.loadClass(path);
    }

    private String classPath;

    protected  Class<?> findClass(String name){
        byte [] classData = new byte[0];
        try {
            classData = getData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < classData.length; i++) {
            classData[i] = (byte)(255-classData[i]);
        }

    return defineClass(name,classData,0,classData.length);
    }

    private  byte[] getData(String name) throws IOException {

        String path = name;

        InputStream fileInputStream = null;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try {
            fileInputStream = new FileInputStream(path);
            byte[] bytes = new byte[1024];
            int len = 0;

            while ((len = fileInputStream.read(bytes)) != -1){
                byteArrayOutputStream.write(bytes,0,len);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                fileInputStream.close();
            }
        }
        return  byteArrayOutputStream.toByteArray();
    }

}

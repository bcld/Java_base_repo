package com.derek.demo.FileManager;

import java.io.File;

public class deleteDFile {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入需要删除错误迭代文件夹名字（迭代内文件夹名字应一致）");
//        System.out.println("例如：D:\\develop_java\\Java_repo\\code\\test   删除的是test内嵌套的test名字的文件夹");
//
//        String input = sc.next();
//        File file = new File(input);
//        File finalFile = find1(file);
//        File zhongjie = delete1(finalFile, file.getName());
//        System.out.println("zhongjie = " + zhongjie);
        delete5(new File("D:\\develop_java\\Java_repo\\code\\test"));
    }

    private static File delete1(File file, String name) {
        if (name.equals(file.getName())) {
            file.delete();
            return delete1(file.getParentFile(), name);
        }
        return file;
    }

    private static File find1(File file) {
        File[] files = file.listFiles();
        if (files.length != 0) {
            for (File file1 : files) {

                return find1(file1);
            }
        }
        System.out.println(file);
        return file;
    }

    public static void delete5(File file) {
        File[] files = file.listFiles();
        if (files.length != 0) {
            for (File file1 : files) {
                delete5(file1);
                file1.getParentFile().delete();
            }
        } else {
            file.delete();
        }
    }


}


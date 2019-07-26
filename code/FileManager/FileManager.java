package com.derek.demo.FileManager;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String desDir = "D:\\develop_java\\Java_repo\\code";//目标地址

        //默认地址
        String defaultDir = "D:\\develop_java\\JavaBase\\module_base\\src\\com\\derek\\demo";//检索地址
        File defaultFile = new File(defaultDir);
        System.out.println("默认文件地址为：" + defaultFile.getAbsolutePath());

        //输入地址
        System.out.println("请输入需要查找的文件夹<默认为>" + defaultFile.getName());
        String dir = sc.nextLine();

        //拼接后最终地址
        String firstDir0 = defaultDir + "\\" + dir;
        File firstDir1 = new File(firstDir0);
        String srcDir;
        System.out.println("正在检索：" + firstDir1.getAbsolutePath());

        //获取列表
        List<String> files = fileSearch(firstDir1);
        int num = files.size();
        System.out.println("请输入选择的文件/夹，输入“0”为结束：");
        //保存输入的选择
        int[] choic = new int[num + 1];
        int n;
        int i = 0;
        while (true) {
            n = sc.nextInt();
            if (n == 0) {
                break;
            }
            choic[i] = n;
            i++;
        }
        for (int choose : choic) {
            if (choose != 0) {
                srcDir = firstDir0 + "/" + files.get(choose - 1);
                copyFile(srcDir, desDir);//复制文件
            }
        }
    }

    private static void copyFile(String srcDir, String desDir) throws IOException {
        File srcFile = new File(srcDir);
        String name = srcFile.getName();

        if (srcFile.isFile()) {
            FileWriter fw = new FileWriter(desDir + "/" + name);
            FileReader fr = new FileReader(srcDir);
            char[] b = new char[1024];
            int len;
            while ((len = fr.read(b)) != -1) {
                fw.write(b, 0, len);
            }
            fr.close();
            fw.close();
        } else {
            desDir = desDir + "/" + name;
            new File(desDir).mkdir();
            //遍历文件夹内的文件
            for (String file : srcFile.list()) {
                srcDir = srcFile + "/" + file;
                copyFile(srcDir, desDir);
            }
        }
    }

    private static List<String> fileSearch(File firstDir1) {
        ArrayList<String> files = new ArrayList<>();
        int num_file = 0;
        ArrayList<String> dirs = new ArrayList<>();

        //遍历目录，分派文件和文件夹，同步得到文件
        File[] fileList = firstDir1.listFiles();

        System.out.println("<有如下文件：>");
        for (File elements : fileList) {
            if (elements.isFile()) {
                String name = elements.getName();
                files.add(name);
                num_file++;
                System.out.println("[" + num_file + "]" + " " + name);
            } else if (elements.isDirectory()) {
                dirs.add(elements.getName());
            }
        }

        //遍历得到文件夹
        System.out.println("<有如下文件夹：>");
        Iterator<String> iterator_dirs = dirs.iterator();
        while (iterator_dirs.hasNext()) {
            num_file++;
            System.out.println("[" + num_file + "]" + " " + iterator_dirs.next());
        }
        Stream<String> stream = Stream.concat(files.stream(), dirs.stream());
        return stream.collect(Collectors.toList());
    }
}

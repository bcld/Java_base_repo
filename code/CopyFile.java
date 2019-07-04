package com.derek.demo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;


public class CopyFile {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //将F盘下面的file.txt文件拷贝到桌面
        String desPathStr = "D:\\develop_java\\Java_repo\\code"; //默认目标文件地址
        String srcPathStr = "D:\\develop_java\\JavaBase\\module_base\\src\\com\\derek\\demo"; //默认源文件地址
        System.out.println("最终目标地址为："+desPathStr);
        //打印地址中的文件名
        File file = new File(srcPathStr);
        int num = showFiles(file);
        System.out.println("一共有" + num + "个文件（包括子文件夹）。");
        System.out.println("请输入需要复制的文件编号(输入0结束)：");
        //收集需要打印的文件编号
        int[] arr = new int[num];//全部值为0，遇到0退出；
        int count = 0;//统计输入了几个数字
        for (int i = 0; i < arr.length; i++) {
            int inp = sc.nextInt();
            if (inp == 0) {
                break;
            }
            arr[i] = inp;
            count = i + 1;
        }
        //复制选择的文件并调用方法复制
        toCopyFiles(arr, count, file, desPathStr);
    }

    //调用copyFile方法，复制选定文件
    public static void toCopyFiles(int[] arr, int num, File file, String desPathStr) {
        int file_num = 0;
        File[] files = file.listFiles();

        for (File a : files) {
            file_num++;
            for (int i = 0; i < num; i++) {
                if (arr[i] == file_num) {
                    copyFile(a.getAbsolutePath(), desPathStr);
                }

            }
        }
        System.out.println("复制完成！");
    }

    //查找当前目录下的文件（不包括子文件夹内容）
    public static int showFiles(File file) {
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("文件夹为");
            return 0;
        }
        int count = 0;

        for (File a : files) {
            count++;
            System.out.println("[" + count + "]:" + a.getAbsolutePath().substring(a.getAbsolutePath().lastIndexOf("\\") + 1));//打印地址
            //用于查找文件夹内嵌套文件夹
           /* if (a.isDirectory()) {
                showFiles(a);
            }*/
        }
        return count;
    }

    /*
     * 实现文件的拷贝
     * @param srcPathStr
     *          源文件的地址信息
     * @param desPathStr
     *          目标文件的地址信息
     */
    private static void copyFile(String srcPathStr, String desPathStr) {
        //1.获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\") + 1);

        //源文件地址
//        System.out.println(srcPathStr);
        desPathStr = desPathStr + File.separator + newFileName;
        //目标文件地址
//        System.out.println(desPathStr);

        try {
            //2.创建输入输出流对象
            FileInputStream fis = new FileInputStream(srcPathStr);
            FileOutputStream fos = new FileOutputStream(desPathStr);

            //创建搬运工具
            byte datas[] = new byte[1024 * 8];
            //创建长度
            int len = 0;
            //循环读取数据
            while ((len = fis.read(datas)) != -1) {
                fos.write(datas, 0, len);
            }
            //3.释放资源
            fis.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
/*---------------------
    作者：xyphf_和派孔明
    来源：CSDN
    原文：https://blog.csdn.net/xyphf/article/details/78276796
    版权声明：本文为博主原创文章，转载请附上博文链接！*/

}


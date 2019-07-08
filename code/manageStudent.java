package com.derek.demo;

import java.util.ArrayList;
import java.util.Scanner;

public class manageStudent {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        int judege = 0;//用于判断退出

        while (true) {
            System.out.println("--------学生管理系统--------");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 修改学生");
            System.out.println("4. 查看所有学生");
            System.out.println("5. 退出系统");
            System.out.println("请输入你的选择");

            String num_input = sc.nextLine();
            switch (num_input) {
                case "1":
                    addStudent(array);
                    break;
                case "2":
                    rmStudent(array);
                    break;
                case "3":
                    modifyStudent(array);
                    break;
                case "4":
                    checkStudenet(array);
                    break;
                case "5":
                    System.out.println("谢谢使用");
                    judege = 1;
                    break;
                default:
                    System.out.println("你输入的数字有误，请重新输入");
                    break;

            }
            if (judege == 1) {
                break;
            }
        }
    }

    public static void addStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生号：");
        String id = sc.nextLine();

        //判断学号是否存在
        int length=array.size();
        if (array == null || array.size() == 0) {
        }else{
            for (int i = 0; i <length ; i++) {
                if(array.get(i).getId().equals(id)){
                    System.out.println("输入的学号已经存在！");
                    return;
                }
            }
        }



        System.out.println("请输入学生名字：");
        String name = sc.nextLine();

        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();//如果是int类型，nextline会录入回车符，改为next()即可

        System.out.println("请输入居住地：");
        String address = sc.nextLine();

        Student student = new Student(id, name, age, address);
        array.add(student);
        System.out.println("操作成功！");
    }

    public static void checkStudenet(ArrayList<Student> array) {
        if (array == null || array.size() == 0) {
            System.out.println("无学生信息，请录入");
            return;
        }

        System.out.println("学号\t\t姓名\t\t年龄\t\t居住地");
        int length = array.size();

        for (int i = 0; i < length; i++) {
            Student s = array.get(i);
            System.out.println(s.getId() + "\t\t\t" + s.getName() + "\t\t" + s.getAge() + "\t\t\t" + s.getAddress());
        }
    }

    public static void modifyStudent(ArrayList<Student> array) {
        int length = array.size();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入需要修改的学生学号");

        String s = sc.nextLine();
        for (int i = 0; i < length; i++) {
            if (array.get(i).getId().equals(s)) {   //注意String的比较需要equals;

                System.out.println("请输入学生名字：");
                String name = sc.nextLine();
                array.get(i).setName(name);

                System.out.println("请输入学生年龄：");
                String age = sc.nextLine();//如果是int类型，nextline会录入回车符，改为next()即可
                array.get(i).setName(age);

                System.out.println("请输入居住地：");
                String address = sc.nextLine();
                array.get(i).setAddress(address);

                System.out.println("修改成功");
                return;

            }

        }
        System.out.println("输入学号有误！");

    }

    public static void rmStudent(ArrayList<Student> array) {
        int length = array.size();
        System.out.println("请输入需要删除学生的学号：");
        Scanner sc = new Scanner(System.in);
        String s_id = sc.next();

        for (int i = 0; i < length; i++) {
            if (s_id.equals(array.get(i).getId())) {
                array.remove(i);
                System.out.println("删除成功！");
                return;
            }

        }

        System.out.println("输入学号有误！");


    }
}

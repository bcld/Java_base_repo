package com.derek.demo;

import java.util.*;

public class BooksManager {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean quit = false;                             //控制退出

        //initial
        Books book1 = new Books("挪威的森林", 28.99);
        Books book2 = new Books("失踪的孩子", 26.50);
        HashSet<Books> wenxue = new HashSet<>();        //?
        Collections.addAll(wenxue, book1, book2);

        Books book3 = new Books("java从入门到出家", 32.80);
        Books book4 = new Books("中二病的数学", 22.00);
        HashSet<Books> sheke = new HashSet<>();        //?
        Collections.addAll(sheke, book3, book4);

        //归类   type   Books
        HashMap<String, HashSet> booksMap = new HashMap<>();
        booksMap.put("文学", wenxue);
        booksMap.put("社科", sheke);

        //互动开始
        while (!quit) {
            //开始界面
            System.out.println("------欢迎来到图书管理系统------");
            System.out.println("1. 查看书籍");
            System.out.println("2. 添加书籍");
            System.out.println("3. 删除书籍");
            System.out.println("4. 修改书籍");
            System.out.println("5. 退出系统");
            System.out.println("请输入你的选择：(1~5)");
            int choose = sc.nextInt();
            switch (choose) {
                case 1:
                    lookupBook(booksMap);
                    break;
                case 2:
                    addBook(booksMap);
                    break;
                case 3:
                    removeBook(booksMap);
                    break;
                case 4:
                    modifyBook(booksMap);
                    break;
                case 5:
                    System.out.println("慢走，人类的赞歌就是关于勇气的赞歌！");
                    quit = true;
                    break;
                default:
                    System.out.println("你输入的数字有误，请重新输入！");
                    break;
            }
        }


    }

    private static void modifyBook(HashMap<String, HashSet> booksMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("请输入修改的书籍类型");
        String type = sc.next();

        System.out.println("请输入修改的书籍名字");
        String name = sc.next();

        System.out.println("请输入修改书籍的价格");
        double price = sc.nextDouble();

        //判断是否存在这本书
        if (!booksMap.containsKey(type)) {
            System.out.println("没有该类型的书籍");
            return;
        }
        HashSet<Books> bookset = booksMap.get(type);
        for (Books book : bookset) {
            if(name.equals(book.getName())){
                book.setPrice(price);
                System.out.println("修改成功！");
                return;
            }
        }
        System.out.println("这本书不存在");

    }

    private static void removeBook(HashMap<String, HashSet> booksMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("请输入需要删除的书籍的类型");
        String type = sc.next();

        System.out.println("请输入需要删除的书籍的名字");
        String name = sc.next();

 /*       System.out.println("请输入需要删除的书籍的价格");
        double price = sc.nextDouble();
*/
        //判断是否存在这本书
        if (!booksMap.containsKey(type)) {
            System.out.println("没有该类型的书籍");
            return;
        }
        HashSet<Books> bookset = booksMap.get(type);
        for (Books book : bookset) {
            if(name.equals(book.getName())){
                bookset.remove(book);
                //如果该类型没书了，删掉该类型
                if(booksMap.get(type).size()==0){
                    booksMap.remove(type);
                }
                System.out.println("删除成功！");
                return;
            }
        }
        System.out.println("这本书不存在");


    }

    //用HashMap一个很大的问题，就是没有索引，要获得单一元素，得多一步
    private static void addBook(HashMap<String, HashSet> booksMap) {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------------");
        System.out.println("请输入添加的书籍类型");
        String type = sc.next();

        System.out.println("请输入添加的书籍名字");
        String name = sc.next();

        System.out.println("请输入添加书籍的价格");
        double price = sc.nextDouble();

        //判断如果没有该类型，添加进去
        if (!booksMap.containsKey(type)) {
            booksMap.put(type, null);
            HashSet<Books> newBook = new HashSet<>();
            newBook.add(new Books(name, price));
            booksMap.put(type, newBook);
        } else {
            HashSet<Books> books = booksMap.get(type);

            int size = books.size();
            books.add(new Books(name, price));
            if (books.size() != size)
                System.out.println("添加成功");
        }

    }


    private static void lookupBook(HashMap<String, HashSet> booksMap) {

        System.out.println("类型" + "\t\t" + "书名" + "\t\t" + "价格");
        System.out.println("---------------------------------------------------");
        Set<Map.Entry<String, HashSet>> entries = booksMap.entrySet();

        //遍历查看键值对，用Entry
        for (Map.Entry<String, HashSet> entry : entries) {
            System.out.println(entry.getKey());             //打印类型

            //遍历哈希set
            HashSet booksSet = entry.getValue();
            for (Object o : booksSet) {                     //因为是地址，所以类型为Object
                System.out.println(o);             //重写了toString,大一座库
            }
        }
    }

}

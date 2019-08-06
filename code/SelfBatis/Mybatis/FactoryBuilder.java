package com.derek.demo.SelfBatis.Mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.derek.demo.SelfBatis.Entity.Mapper;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class FactoryBuilder {
    private String driver;
    private String username;
    private String password;
    private String url;

    private Connection conn;
    private DruidDataSource dds;
    private HashMap<String, Mapper> sqlmap=new HashMap<>();


    public FactoryBuilder() {
    }

    //实现built()方法，传入inputstream，返回factory
    public SessionFactory build(InputStream inputStream) {
        //解析核心配置文件，创建连接池
        loadConfig(inputStream);
        SessionFactory sf = new SessionFactory(conn,sqlmap);
        return sf;
    }

    //加载配置文件
    public void loadConfig(InputStream in) {
        //解析配置文件
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(in);
            Element defaultEle = (Element) doc.selectSingleNode("//environments");
            String aDefault = defaultEle.attributeValue("default");

            //查找默认的配置信息

            //拼接变量到xpath路径,得到选择的配置表
            String id = String.format("//environment[@id='%s']", aDefault);
            Element env = (Element) doc.selectSingleNode(id);
            //得到所有配置属性值
            Element driverEle = (Element) env.selectSingleNode("//property[@name='driver']");
            driver = driverEle.attributeValue("value");

            Element usernameEle = (Element) env.selectSingleNode("//property[@name='username']");
            username = usernameEle.attributeValue("value");

            Element passwordEle = (Element) env.selectSingleNode("//property[@name='password']");
            password = passwordEle.attributeValue("value");

            Element urlEle = (Element) env.selectSingleNode("//property[@name='url']");
            url = urlEle.attributeValue("value");

            // 创建连接池
            dds = new DruidDataSource();
            dds.setUsername(username);
            dds.setPassword(password);
            dds.setUrl(url);
            dds.setDriverClassName(driver);
            conn = dds.getConnection();

            //解析接口映射文件的信息
            //遍历得到每一个Mapper标签

            List<Node> nodes = doc.selectNodes("//mapper");
            for (Node node : nodes) {
                Element element = (Element) node;
                String path = element.attributeValue("resource");
                SAXReader readerIntf = new SAXReader();
                InputStream in2 = FactoryBuilder.class.getResourceAsStream("/" + path);

                //解析得到的文件地址
                Document docIntf = readerIntf.read(in2);
                String namespace = docIntf.getRootElement().attributeValue("namespace");

                //遍历mapper将方法和sql操作以键值对保存到map中
                List<Node> selectNodes = docIntf.selectNodes("//select");
                int size = selectNodes.size();
                for (int i = 0; i < size; i++) {
                    Element element1 = (Element) selectNodes.get(i);
                    String sql = element1.getTextTrim();
                    String id1 = element1.attributeValue("id");
                    String resultType = element1.attributeValue("resultType");
                    sqlmap.put(namespace + "." + id1, new Mapper(id1, resultType, namespace, sql));
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //getter and setter
    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public DruidDataSource getDds() {
        return dds;
    }

    public void setDds(DruidDataSource dds) {
        this.dds = dds;
    }

    public HashMap<String, Mapper> getSqlmap() {
        return sqlmap;
    }

    public void setSqlmap(HashMap<String, Mapper> sqlmap) {
        this.sqlmap = sqlmap;
    }
}

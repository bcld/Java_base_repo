package com.derek.demo.SelfBatis.Entity;

public class Mapper {
    private String id;
    private String resultType;
    private String nameSpace;
    private String sql;

    public Mapper(String id, String resultType, String nameSpace, String sql) {
        this.id = id;
        this.resultType = resultType;
        this.nameSpace = nameSpace;
        this.sql = sql;
    }

    public Mapper() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}

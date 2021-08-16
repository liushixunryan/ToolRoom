package com.xql.toolroom.db;

import org.litepal.crud.LitePalSupport;

/**
 * @ClassName: News
 * @Description: java类作用描述
 * @CreateDate: 2021/8/14 14:53
 * @UpdateUser: RyanLiu
 */

public class News extends LitePalSupport {
    private long id;
    private String title;
    private String textcontent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextcontent() {
        return textcontent;
    }

    public void setTextcontent(String textcontent) {
        this.textcontent = textcontent;
    }
}

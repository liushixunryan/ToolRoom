package com.xql.note.bean;

/**
 * @ClassName: NoteListBean
 * @Description: java类作用描述
 * @CreateDate: 2021/8/18 11:08
 * @UpdateUser: RyanLiu
 */

public class NoteListBean {
    private int id;
    private String notetitle;
    private String notecontent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotetitle() {
        return notetitle;
    }

    public void setNotetitle(String notetitle) {
        this.notetitle = notetitle;
    }

    public String getNotecontent() {
        return notecontent;
    }

    public void setNotecontent(String notecontent) {
        this.notecontent = notecontent;
    }
}

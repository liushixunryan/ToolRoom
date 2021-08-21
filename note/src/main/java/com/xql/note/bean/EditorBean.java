package com.xql.note.bean;

/**
 * @ClassName: EditorBean
 * @Description: java类作用描述
 * @CreateDate: 2021/8/19 11:23
 * @UpdateUser: RyanLiu
 */

public class EditorBean {
    private int editor_id;
    private int editor_img;
    private String editor_name;

    public int getEditor_id() {
        return editor_id;
    }

    public void setEditor_id(int editor_id) {
        this.editor_id = editor_id;
    }

    public int getEditor_img() {
        return editor_img;
    }

    public void setEditor_img(int editor_img) {
        this.editor_img = editor_img;
    }

    public String getEditor_name() {
        return editor_name;
    }

    public void setEditor_name(String editor_name) {
        this.editor_name = editor_name;
    }
}

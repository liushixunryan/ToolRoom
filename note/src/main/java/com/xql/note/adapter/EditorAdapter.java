package com.xql.note.adapter;

import android.content.Context;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.note.R;
import com.xql.note.bean.EditorBean;
import com.xql.note.interfaces.IOnEditorListener;

import java.util.List;

/**
 * @ClassName: EditorAdapter
 * @Description: java类作用描述
 * @CreateDate: 2021/8/19 11:25
 * @UpdateUser: RyanLiu
 */

public class EditorAdapter extends BaseAdapter<EditorBean> {
    private IOnEditorListener mIOnEditorListener;

    public void setiOnEditorListener(IOnEditorListener onEditorListener){
        mIOnEditorListener = onEditorListener;
    }

    public EditorAdapter(Context context, List<EditorBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, EditorBean editorBean, int postion) {
        baseHolder.setImageView(R.id.img_editor,editorBean.getEditor_img());
        baseHolder.setOnclickListioner(R.id.img_editor, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showLong(editorBean.getEditor_id());
                mIOnEditorListener.OnEditorItemClick(postion,editorBean.getEditor_id());
            }
        });
    }
}

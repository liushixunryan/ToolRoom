package com.xql.note.adapter;

import android.content.Context;

import com.xql.basic.adapter.BaseAdapter;
import com.xql.basic.adapter.BaseHolder;
import com.xql.note.R;
import com.xql.note.bean.NoteListBean;

import java.util.List;

/**
 * @ClassName: NoteListAdapter
 * @Description: java类作用描述
 * @CreateDate: 2021/8/18 11:19
 * @UpdateUser: RyanLiu
 */

public class NoteListAdapter extends BaseAdapter<NoteListBean> {
    public NoteListAdapter(Context context, List<NoteListBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    protected void onBindData(BaseHolder baseHolder, NoteListBean noteListBean, int postion) {
        baseHolder.setText(R.id.tv_notetitle, noteListBean.getNotetitle() + "");
        baseHolder.setText(R.id.tv_notecontent, noteListBean.getNotecontent() + "");
    }
}

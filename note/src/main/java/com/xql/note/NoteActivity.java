package com.xql.note;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.xql.annotation.BindPath;
import com.xql.basic.activity.BaseActivity;
import com.xql.note.adapter.NoteListAdapter;
import com.xql.note.bean.NoteListBean;
import com.xql.note.databinding.ActivityNoteBinding;
import com.xql.note.vm.NoteVM;

import java.util.ArrayList;
import java.util.List;

@BindPath(key = "note/note")
public class NoteActivity extends BaseActivity<ActivityNoteBinding, NoteVM> {
    private NoteListAdapter noteListAdapter;
    private List<NoteListBean> noteListBeans;

    @Override
    protected int layoutId() {
        return R.layout.activity_note;
    }

    @Override
    protected void initView() {
        noteListBeans = new ArrayList<>();
        for (int i =0;i<5;i++){
            NoteListBean noteListBean = new NoteListBean();
            noteListBean.setId(i);
            noteListBean.setNotetitle("文章标题" + i);
            noteListBean.setNotecontent("文章内容" + i);
            noteListBeans.add(noteListBean);
        }
        noteListAdapter = new NoteListAdapter(NoteActivity.this, noteListBeans, R.layout.item_notelist);
        mBinding.rvNotelist.setLayoutManager(new LinearLayoutManager(NoteActivity.this));
        mBinding.rvNotelist.setAdapter(noteListAdapter);
    }

    @Override
    protected void initData() {

    }
}
package com.xql.note;

import android.graphics.Color;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xql.basic.activity.BaseActivity;
import com.xql.note.adapter.EditorAdapter;
import com.xql.note.bean.EditorBean;
import com.xql.note.databinding.ActivityInputNoteBinding;
import com.xql.note.interfaces.IOnEditorListener;
import com.xql.note.vm.InputNoteVM;

import java.util.ArrayList;
import java.util.List;

public class InputNoteActivity extends BaseActivity<ActivityInputNoteBinding, InputNoteVM> {
    private EditorAdapter editorAdapter;
    private List<EditorBean> editorBeans;
    private final int[] editorimg = {R.mipmap.bold, R.mipmap.italic, R.mipmap.strikethrough, R.mipmap.justifyleft, R.mipmap.justifycenter, R.mipmap.justifright, R.mipmap.heading1, R.mipmap.heading2, R.mipmap.heading3, R.mipmap.heading4, R.mipmap.insertimage, R.mipmap.insertvideo, R.mipmap.insertaudio, R.mipmap.insertlink, R.mipmap.textcolor, R.mipmap.textbackgroundcolor};
    private final int[] editorid = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    @Override
    protected int layoutId() {
        return R.layout.activity_input_note;
    }

    @Override
    protected void initView() {
        editorBeans = new ArrayList<>();

        for (int i = 0; i < editorimg.length; i++) {
            EditorBean editorBean = new EditorBean();
            editorBean.setEditor_img(editorimg[i]);
            editorBean.setEditor_id(editorid[i]);
            editorBeans.add(editorBean);
        }
    }

    @Override
    protected void initData() {
        mBinding.rvEditor.setPadding(10, 10, 10, 10);
        editorAdapter = new EditorAdapter(InputNoteActivity.this, editorBeans, R.layout.item_editor);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(InputNoteActivity.this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mBinding.rvEditor.setLayoutManager(linearLayoutManager);
        mBinding.rvEditor.setAdapter(editorAdapter);
        editorAdapter.setiOnEditorListener(new IOnEditorListener() {
            @Override
            public void OnEditorItemClick(int position, int ID) {
                switch (position) {
                    case 0:
                        //字体加粗:Bold
                        mBinding.richeditor.setBold();
                        break;
                    case 1:
                        //斜体:Italic
                        mBinding.richeditor.setItalic();
                        break;
                    case 2:
                        //删除线:Strikethrough
                        mBinding.richeditor.setStrikeThrough();
                        break;
                    case 3:
                        //左对齐:AlignLeft
                        mBinding.richeditor.setAlignLeft();
                        break;
                    case 4:
                        //中心对齐:AlignCenter
                        mBinding.richeditor.setAlignCenter();
                        break;
                    case 5:
                        //右对齐:AlignRight
                        mBinding.richeditor.setAlignRight();
                        break;
                    case 6:
                        //标题1:Heading(1)
                        mBinding.richeditor.setHeading(1);
                        break;
                    case 7:
                        //标题2:Heading(2)
                        mBinding.richeditor.setHeading(2);
                        break;
                    case 8:
                        //标题3:Heading(3)
                        mBinding.richeditor.setHeading(3);
                        break;
                    case 9:
                        //标题4:Heading(4)
                        mBinding.richeditor.setHeading(4);
                        break;
                    case 110:
                        //插入图片:insertImage
                        mBinding.richeditor.insertImage("https://raw.githubusercontent.com/wasabeef/art/master/twitter.png","ddd");
                        break;
                    case 11:
                        //插入视频:insertVideo
                        mBinding.richeditor.insertVideo("https://maiche.jmtv.com.cn/2020/10/27/ca4d92e47f2848c98dfdb8dfee36e195.high.mp4");
                        break;
                    case 12:
                        //插入音频:insertAudio
                        mBinding.richeditor.insertAudio("https://maiche.jmtv.com.cn/2020/10/27/ca4d92e47f2848c98dfdb8dfee36e195.high.mp4");
                        break;
                    case 13:
                        //插入链接:insertLink
                        mBinding.richeditor.insertLink("https://bj.96weixin.com/tools/rgb","颜色对照表");
                        break;
                    case 14:
                        //字体颜色:TextColor
                        mBinding.richeditor.setTextColor(Color.parseColor("#FF0000"));
                        break;
                    case 15:
                        //文本背景色:TextBackgroundColor
                        mBinding.richeditor.setTextBackgroundColor(Color.parseColor("#63B8FF"));
                        break;

                }
            }
        });
    }
}
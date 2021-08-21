package com.xql.note;

import android.graphics.Color;
import android.util.Log;

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
    private Boolean bold = false, italic = false, strikethrough = false, justifyleft = false, justifycenter = false, justifright = false, heading1 = false, heading2 = false, heading3 = false, heading4 = false, insertimage = false, insertvideo = false, insertaudio = false, insertlink = false, textcolor = false, textbackgroundcolor = false;

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
                        if (bold) {
                            bold = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.bold);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            bold = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.boldselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //字体加粗:Bold
                        mBinding.richeditor.setBold();
                        break;
                    case 1:
                        if (italic) {
                            italic = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.italic);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            italic = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.italicselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //斜体:Italic
                        mBinding.richeditor.setItalic();
                        break;
                    case 2:
                        if (strikethrough) {
                            strikethrough = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.strikethrough);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            strikethrough = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.strikethroughselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //删除线:Strikethrough
                        mBinding.richeditor.setStrikeThrough();
                        break;
                    case 3:
                        if (justifyleft) {
                            justifyleft = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifyleft);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            justifyleft = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifyleftselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //左对齐:AlignLeft
                        mBinding.richeditor.setAlignLeft();
                        break;
                    case 4:
                        if (justifycenter) {
                            justifycenter = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifycenter);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            justifycenter = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifycenterselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //中心对齐:AlignCenter
                        mBinding.richeditor.setAlignCenter();
                        break;
                    case 5:
                        if (justifright) {
                            justifright = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifright);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            justifright = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.justifrightselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //右对齐:AlignRight
                        mBinding.richeditor.setAlignRight();
                        break;
                    case 6:
                        if (heading1) {
                            heading1 = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading1);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            heading1 = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading1select);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //标题1:Heading(1)
                        mBinding.richeditor.setHeading(1);
                        break;
                    case 7:
                        if (heading2) {
                            heading2 = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading2);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            heading2 = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading2select);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //标题2:Heading(2)
                        mBinding.richeditor.setHeading(2);
                        break;
                    case 8:
                        if (heading3) {
                            heading3 = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading3);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            heading3 = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading3select);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //标题3:Heading(3)
                        mBinding.richeditor.setHeading(3);
                        break;
                    case 9:
                        if (heading4) {
                            heading4 = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading4);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            heading4 = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.heading4select);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //标题4:Heading(4)
                        mBinding.richeditor.setHeading(4);
                        break;
                    case 10:
                        if (insertimage) {
                            insertimage = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertimage);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            insertimage = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertimageselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //插入图片:insertImage
                        mBinding.richeditor.insertImage("https://raw.githubusercontent.com/wasabeef/art/master/twitter.png", "ddd");
                        break;
                    case 11:
                        if (insertvideo) {
                            insertvideo = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertvideo);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            insertvideo = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertvideoselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //插入视频:insertVideo
                        mBinding.richeditor.insertVideo("https://maiche.jmtv.com.cn/2020/10/27/ca4d92e47f2848c98dfdb8dfee36e195.high.mp4");
                        break;
                    case 12:
                        if (insertaudio) {
                            insertaudio = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertaudio);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            insertaudio = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertaudioselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //插入音频:insertAudio
                        mBinding.richeditor.insertAudio("https://maiche.jmtv.com.cn/2020/10/27/ca4d92e47f2848c98dfdb8dfee36e195.high.mp4");
                        break;
                    case 13:
                        if (insertlink) {
                            insertlink = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertlink);
                            editorAdapter.notifyDataSetChanged();
                        } else {
                            insertlink = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.insertlinkselect);
                            editorAdapter.notifyDataSetChanged();
                        }
                        //插入链接:insertLink
                        mBinding.richeditor.insertLink("https://bj.96weixin.com/tools/rgb", "颜色对照表");
                        break;
                    case 14:
                        if (textcolor) {
                            textcolor = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.textcolor);
                            editorAdapter.notifyDataSetChanged();
                            mBinding.richeditor.setTextColor(Color.parseColor("#000000"));
                        } else {
                            textcolor = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.textcolorselect);
                            editorAdapter.notifyDataSetChanged();
                            //字体颜色:TextColor
                            mBinding.richeditor.setTextColor(Color.parseColor("#FF0000"));
                        }

                        break;
                    case 15:
                        if (textbackgroundcolor) {
                            textbackgroundcolor = false;
                            editorBeans.get(position).setEditor_img(R.mipmap.textbackgroundcolor);
                            editorAdapter.notifyDataSetChanged();
                            //文本背景色:TextBackgroundColor
                            mBinding.richeditor.setTextBackgroundColor(Color.parseColor("#FFFFFF"));
                        } else {
                            textbackgroundcolor = true;
                            editorBeans.get(position).setEditor_img(R.mipmap.textbackgroundcolorselect);
                            editorAdapter.notifyDataSetChanged();
                            //文本背景色:TextBackgroundColor
                            mBinding.richeditor.setTextBackgroundColor(Color.parseColor("#63B8FF"));
                        }
                        break;

                }
            }
        });
    }
}
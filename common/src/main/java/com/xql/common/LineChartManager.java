package com.xql.common;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: LineChartManager
 * @Description: 折线图工具类
 * @CreateDate: 2021/6/30 9:23
 * @UpdateUser: RyanLiu
 */

public class LineChartManager {
    public LineChartManager(LineChart lineChart,List<Entry> entries) {
        //是否显示边框
        lineChart.setDrawBorders(false);
        //得到X轴
        XAxis xAxis = lineChart.getXAxis();
        //设置X轴的位置 (不设置默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔（因为此图有缩放功能，X轴,Y轴可设置可缩放）
        xAxis.setGranularity(1f);
        //设置X轴的刻度数量 第二个参数表示是否平均分配
        xAxis.setLabelCount(12, true);
        //设置X轴的值（最小值、最大值、然后会根据设置的刻度数量自动分配刻度显示）
        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(23f);
        //X轴文字颜色
        xAxis.setTextColor(Color.WHITE);
        //X轴网格线颜色
        xAxis.setGridColor(Color.TRANSPARENT);
        //X轴颜色
        xAxis.setAxisLineColor(Color.WHITE);
        //设置X轴动画
        //        mBinding.lineChart.animateX(3000);

        //得到Y轴
        YAxis leftYAxis = lineChart.getAxisLeft();
        YAxis rightYAxis = lineChart.getAxisRight();
        //设置从左侧Y轴值
        leftYAxis.setAxisMinimum(0f);
        leftYAxis.setAxisMaximum(60f);
        //设置从左侧Y轴值
        //        rightYAxis.setAxisMinimum(0f);
        //        rightYAxis.setAxisMaximum(100f);
        //右侧Y轴不显示
        rightYAxis.setEnabled(false);
        //设置Y轴坐标之间的最小间隔（因为此图有缩放功能，X轴,Y轴可设置可缩放）
        leftYAxis.setGranularity(1f);
        //设置Y轴的刻度数量 第二个参数表示是否平均分配
        leftYAxis.setLabelCount(10, false);
        //Y轴文字颜色
        leftYAxis.setTextColor(Color.WHITE);
        //Y轴网格线颜色
        leftYAxis.setGridColor(Color.TRANSPARENT);
        //Y轴颜色
        leftYAxis.setAxisLineColor(Color.WHITE);
        //设置X轴动画
        //        mBinding.lineChart.animateY(3000);

        //        //得到限制线
        //        LimitLine limitLine = new LimitLine(95,"高限制性");
        //        //宽度
        //        limitLine.setLineWidth(4f);
        //        //字体大小
        //        limitLine.setTextSize(10f);
        //        //字体颜色
        //        limitLine.setTextColor(Color.RED);
        //        //线大小
        //        limitLine.setLineColor(Color.BLUE);
        //        //Y轴添加限制线
        //        leftYAxis.addLimitLine(limitLine);
        //        //X轴添加限制线
        //        xAxis.addLimitLine(limitLine);

        //得到Legend (下边的颜色线)
        Legend legend = lineChart.getLegend();
        //设置Legend 文本颜色
        legend.setTextColor(Color.WHITE);
        //设置Legend 在顶部显示
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        //设置Legend 在右侧显示
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        //设置Legend 在横向显示
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        //设置标签是否换行（当多条标签时 需要换行显示）
        //true：可换行。false：不换行
        legend.setWordWrapEnabled(false);
        //是否显示Lengend
        //true：显示。false：不显示
        legend.setEnabled(true);

        //得到描述
        Description description = new Description();
        //是否显示描述
        //true：显示。false：不显示
        description.setEnabled(true);
        //设置x轴描述
        description.setText("时间/小时");
        description.setTextColor(Color.WHITE);
        lineChart.setDescription(description);

        //设置XY轴动画
        lineChart.animateXY(3000, 3000);

        //一个LineDataSet就是一条线
        LineDataSet lineDataSet = new LineDataSet(entries, "温度");
        //设置value的字体颜色
        lineDataSet.setValueTextColor(Color.WHITE);
        //设置曲线值的圆点是实心还是空心
        lineDataSet.setDrawCircleHole(false);
        //设置显示值的字体大小
        lineDataSet.setValueTextSize(9f);
        //线模式为圆滑曲线（默认折线）
        lineDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);
        //设置折线颜色
        lineDataSet.setColor(Color.WHITE);
        //设置点颜色
        //        lineDataSet.setCircleColor(Color.WHITE);
        //设置填充颜色
        //        lineDataSet.setDrawFilled(true);
        //        lineDataSet.setFillDrawable(getResources().getDrawable(R.drawable.line_gradient_bg_shape));

        // 设置拖拽、缩放等
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setScaleXEnabled(false);
        lineChart.setScaleYEnabled(false);
        // 设置双指缩放
        lineChart.setPinchZoom(true);

        LineData data = new LineData(lineDataSet);
        lineChart.setData(data);
    }
}

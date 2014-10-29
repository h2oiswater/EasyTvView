EasyTvView 说明
===================================  
    使用ViewItem与ViewRender可以轻易的做出TV上需要的界面，本项目包含一个例子可参考。

### 效果示例
    自带一个选中放大的效果。
![github](https://github.com/h2oiswater/EasyTvView/blob/master/screenShot/device-2014-10-29-125634.png "github") 

![github](https://github.com/h2oiswater/EasyTvView/blob/master/screenShot/device-2014-10-29-125648.png "github") 

### 目前特性
    1.支持按照网格的方式来布局
    2.支持按照相对位置来布局
    3.支持在设置了长宽之后在设置比例，最终会按照设置的比例居中显示在原来的位置上(后续增加居中之外的选项)

### 后续改进
    1.当使用相对位置布局时，顺序很重要，例如，B1在A1的右边，如果给的数据，A1在B1之后，那么B1的位置会不准确，目前提供了一个设定顺序来改进。
    2.设置比例后目前只能居中显示。
    3.目前的margin只能设置一个，该margin会控制item之间的距离和item与边界的距离。

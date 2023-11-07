# Ltool

# 简易文件批量处理工具

## 1.概述

批量处理文件工具，可以帮助用户快速执行相同的操作，并节省大量的时间。目前商业软件通常具备以下功能:

1. 批量重命名文件：有时用户需要将多个文件的名称更改为统一的格式，或者添加一些公共的前缀或后缀。
2. 批量转换文件格式：用户可能需要将多个文件从一种格式转换为另一种格式，例如将多个图像文件转换为 JPEG 格式。
3. 批量复制或移动文件：用户可能需要将多个文件复制到另一个文件夹中，或者将它们移动到另一个位置。
4. 批量添加或删除文件元数据：用户可能需要在多个文件中添加或删除元数据，例如标签、作者信息或日期。
5. 批量应用文件操作：用户可能希望应用同一种操作（例如图像旋转或滤镜应用）到多个文件中。
6. 批量执行脚本或命令：用户可能希望批量执行某些脚本或命令，以对多个文件进行处理。
7. 支持多种文件类型：用户希望工具能够处理多种类型的文件。

而本项目实现了其中大量功能并有一定拓展，免费使用，且只有几MB大小，不用安装，即用即走。正符合本项目的名字:

	<center><h4>Ltool（Lightweight tool）轻量级工具</h4></center>



## 2.工程目录

> E:\项目\文件\SRC
> │  opr.properties    //数据库配置文件
> ├─code                    //存放对文件操作的静态方法
> ├─database            //数据库操作
> ├─META-INF
> ├─res                       //资源文件
> └─UI                        //UI代码



## 3.功能实现一览

### 3.1 code

> ├─code
> │      DeleteFile.java               //文件删除相关
> │      DoubleFileList.java       //双窗口数据封装类
> │      FileCompare.java          //文件对比相关
> │      FileCopy.java	             //文件复制相关
> │      FileSel.java                     //文件选择相关
> │      Getallfiles.java               //获取目录所有文件以供搜索使用
> │      ReName.java                 //文件重命名相关
> │      SearchFile.java              //文件搜索相关
> │      Zip.java                           //文件压缩相关

### 3.2 database

> ├─database
> │      Database.java               //数据库处理相关(用来储存操作记录)
> │      DataItem.java                //数据库数据封装类

### 3.3 UI

> └─UI
>     │  DoubleWindow.java       //主窗口（主类）
>     │  FileItem.java                    //文件封装类
>     │  FileListBox.java               //重写JList鼠标监听器
>     │  FileListRenderer.java     //实现ListCellRenderer接口自定义JList显示方式
>     │  MainFrame.java              //单JList窗口
>     │  QXWindow.java              //选择指定后缀名窗口
>     │  RecordWindow.java       //操作记录查看窗口
>     │  ReNameWindow.java     //重命名窗口
>     │  SearchWindow.java        //搜索窗口
>     │  ZipWindow.java              //压缩文件窗口
>     │  
>     └─util
>             SwingUtil.java             //工具类



## 4.软件使用教程

请见附件 ：[Ltool使用说明](http://ltool.cc/help.html)

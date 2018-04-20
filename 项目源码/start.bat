rem @表示隐藏它后面的这一行命令本身，只印象当前行
rem  echo off表示它后面的索引命令都不显示命令，只显示结果，可以用echo on打开
@echo off

rem 设置classpath，引入项目依赖的jar包
set classpath=.;%cd%\cn\qlq\craw\lib\*

rem 编译源代码
javac .\cn\qlq\craw\JsoupCrawJWXT\*.java

rem 执行主程序，开始进行爬虫 
java cn.qlq.craw.JsoupCrawJWXT.MainClass

rem 执行完毕之后不关机窗口，用户按下任意键之后关闭窗口
pause

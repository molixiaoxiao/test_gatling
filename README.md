# test_gatling

1、工程结构：
- **bodies**：用来存放请求的body数据
- **data**：存放需要输入的数据
- **scala**：存放Simulation脚本
- **Engine**：右键运行跟运行 bin\gatling.bat 效果一致
- **Recorde**r：右键运行跟运行 bin\recorder.bat 效果一致，录制的脚本存放在scala目录下
- **target**：存放运行后的测试报告，浏览器打开target/gatling/baidusimulation-xxxxxx/index.html即可查看对应的测试报告表

2、如何运行  
&emsp;&emsp;方法一（推荐）：终端执行命令  
&emsp;&emsp;` mvn gatling:test -DLOG_LEVEL=WARN -DuserCount=10 -DduringTime=100 -DstartUserCount=0 -DrampDuringTime=20 -Durl=https://www.baidu.com -DtestClass=BaiduSimulation`  
&emsp;&emsp;方法二：选中src/test/scala下面的Engine，右键，Run 'Engine'  

3、其它问题  
（1）推荐IDEA：Intellij IDEA  
（2）IDEA无法New Scala Class  
&emsp;&emsp;解决办法：https://www.cnblogs.com/lingluo2017/p/8673243.html  
（3）scala sdk安装失败，或安装不成功  
&emsp;&emsp;解决办法：版本不对，可以换个版本再安装：https://blog.csdn.net/iamlihongwei/article/details/72783459  
（4）选中Engine.scala右键时，没有run选项  
&emsp;&emsp;解决办法：选中src目录，右键，Mark Directory As → Source Root  
（5）选中Engine.scala右键run后，出现报错：Gatling 错误: 找不到或无法加载主类 Engine  
&emsp;&emsp;解决办法：把scala-sdk删掉，重新添加，选择File → Project Structure... → Global Libraries，选中 - 号，把scala-sdk-x.x.x删掉，然后选择 + 号，添加任意版本，建议使用低版本，高版本不够稳定可能会有新的问题，这里楼主选择2.12.8版本，最后Apply后，点OK保存。  
（6）选中Engine.scala右键run后，出现报错：  
```
Exception in thread "main" java.lang.ExceptionInInitializerError
	at Engine$.delayedEndpoint$Engine$1(Engine.scala:7)
	at Engine$delayedInit$body.apply(Engine.scala:4)
	at scala.Function0.apply$mcV$sp(Function0.scala:34)
	at scala.Function0.apply$mcV$sp$(Function0.scala:34)
	at scala.runtime.AbstractFunction0.apply$mcV$sp(AbstractFunction0.scala:12)
	at scala.App.$anonfun$main$1$adapted(App.scala:76)
	at scala.collection.immutable.List.foreach(List.scala:389)
	at scala.App.main(App.scala:76)
	at scala.App.main$(App.scala:74)
	at Engine$.main(Engine.scala:4)
	at Engine.main(Engine.scala)
Caused by: java.lang.NullPointerException
	at io.gatling.commons.util.PathHelper$.url2path(PathHelper.scala:34)
	at IDEPathHelper$.<init>(IDEPathHelper.scala:7)
	at IDEPathHelper$.<clinit>(IDEPathHelper.scala)
	... 11 more
```
&emsp;&emsp;解决办法：选中src目录右键，选择Mark Directory as... → Unmark as Sources Root，然后再选中scala目录右键，选择Mark Directory as... → Test Sources Root。再次run一下Engine.scala即可。
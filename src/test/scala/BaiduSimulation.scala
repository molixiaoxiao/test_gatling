package test.scala

import java.lang.Double.parseDouble
import java.lang.Integer.getInteger

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.PopulationBuilder

class BaiduSimulation extends Simulation {

  val userCount = System.getProperty("userCount").toDouble
  val duringTime = System.getProperty("duringTime").toInt
  val startUserCount = System.getProperty("startUserCount").toDouble
  val rampDuringTime = System.getProperty("rampDuringTime").toInt
  val httpConf = System.getProperty("url")

  // 初始化协议
  val url = http.baseUrl(httpConf).shareConnections

  // 测试用例
  def scnBaidu = scenario("Baidu")
    .exec(
      http("Baidu")
        .get("/")
        .check(status.is(200))
    )

  var setUpList = List[PopulationBuilder]()

  // 恒定模型：QPS以设定的userCount值进行恒定压力测试，时长during
  setUpList :::= List(
    scnBaidu.inject(constantUsersPerSec(userCount) during(duringTime)).protocols(url)
  )

//  // 爬升模式：QPS从指定的startUserCount值开始，在时长rampDuringTime内匀速爬升到QPS值为userCount，即停止测试
//  setUpList :::= List(
//    scnBaidu.inject(rampUsersPerSec(startUserCount) to (rampDuringTime) during(duringTime)).protocols(url)
//  )

//  // 混合模式：先爬升一段时间，再恒定一段时间
//  setUpList :::= List(
//    scnBaidu.inject(rampUsersPerSec(startUserCount) to (userCount) during(rampDuringTime),
//      constantUsersPerSec(userCount) during (duringTime)
//    ).protocols(url)
//  )

  // 执行所有case
  setUp(setUpList)

  // run:  mvn gatling:test -DLOG_LEVEL=WARN -DuserCount=10 -DduringTime=100 -DstartUserCount=0 -DrampDuringTime=20 -Durl=https://www.baidu.com -DtestClass=BaiduSimulation
}
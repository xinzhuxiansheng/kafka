package com.xinzhuxiansheng.consumer

import org.apache.kafka.common.utils.Utils

object Test01 {
  def main(args: Array[String]): Unit = {
    var gid:String = "=yzhou_app_sight_show_rcm_lf_202003300941";
    var partition = Utils.abs(gid.hashCode) % 50
    println(partition)
  }
}

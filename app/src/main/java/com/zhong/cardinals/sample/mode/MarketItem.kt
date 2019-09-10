package com.zhong.cardinals.sample.mode

import java.io.Serializable

/**
 * Created by 16570 on 2017/10/30.
 */

class MarketItem : Serializable {


    var buy: Double = 0.toDouble()
    var closePrice: Double = 0.toDouble()
    var lastPrice: Double = 0.toDouble()
    var isMarketState: Boolean = false
    var sell: Double = 0.toDouble()
    var symbol: String? = null
    var symbolStr: String? = null
    var volume: Double = 0.toDouble()
    var chart: DoubleArray? = null
}

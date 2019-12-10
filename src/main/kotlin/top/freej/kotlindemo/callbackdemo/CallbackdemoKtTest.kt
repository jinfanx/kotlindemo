package top.freej.kotlindemo.callbackdemo

import org.junit.Test
import top.freej.kotlindemo.callback.lambdaDemo
import top.freej.kotlindemo.callback.mapDemo
import top.freej.kotlindemo.callback.post

class CallbackdemoKtTest {

    @Test
    fun mapDemoTest() {
        mapDemo()
    }

    @Test
    fun postTest() {
        post(
            "https://www.freej.top/students",
            mapOf(Pair("class", "1")),
            fun(data, error) {
                if (data is List<*>) {
                    data.forEach(::println)
                }
                println(error?:return)
            }
        )
    }

    @Test
    fun lambdaDemoTest() {
        lambdaDemo()
    }
}

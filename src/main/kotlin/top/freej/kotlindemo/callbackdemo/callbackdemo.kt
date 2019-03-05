package top.freej.kotlindemo.callback

/**
 * 语法点：
 * （1）创建map
 *     mapOf
 * （2）lambda表达式只有一行，不能用大括号括起来
 *      // 错误写法，后面的lambda表达式内容不能用大括号括起来
 *      val f: (s1: String, s2: String) -> String = { s1: String, s2: String ->{(s1 + " " + s2).toUpperCase()}}
 *      // 正确写法
 *      val f: (s1: String, s2: String) -> String = { s1: String, s2: String ->(s1 + " " + s2).toUpperCase()}
 * （3）实现类似jquery的回调
 *     在调用时传入的函数如果有多条语句，需要传入匿名函数而不是lambda表达式
 * （4）调用函数的两种方法
 *      第一种：像普通方法一样 method(arg1,atr2)
 *      第二种：method.call(arg1,atg2)
 *
 *
 *
 */
fun main(args: Array<String>) {

    // lambda表达式定义
    val f: (s1: String, s2: String) -> String = { s1: String, s2: String ->
        (s1 + " " + s2).toUpperCase()
    }
    /**
     * 错误写法，后面的lambda表达式内容不能用大括号括起来
     *  val f: (s1: String, s2: String) -> String = { s1: String, s2: String ->{(s1 + " " + s2).toUpperCase()}}
     *
     * */


    // 直接调用lambda表达式
    println(f("hello", "world"))

    // 回调有多条语句，类似jQuery，用匿名函数
    post(
        "https://www.freej.top",
        mapOf<String, String>(Pair("username", "fanrj"), Pair("password", "pass123")),
        fun(data: String, error: String) {
            println(data)
            println(error)
        })

    // 回调只有一条语句，用lambda表达式，此处lambda表达式类型可以推断不用写
    post("https://www.freej.top", mapOf<String, String>(Pair("username", "fanrj"), Pair("password", "pass123")), { data, error -> println(data) })

    //post("https://www.freej.top",mapOf<String,String>(Pair("username","fanrj"),Pair("password", "pass123")),{data:String,error:String-> println(data)})

    // map定义
    val m1 = mapOf<String, String>("username" to "fanrj", "password" to "fanrj")
    val m2 = mapOf<String, Int>(Pair("A", 13), Pair("B", 14))

    // map遍历
    m1.forEach(::println)
    m2.forEach({
        println(it.key + " = " + it.value)
    })

}

/**
 * 类似jQuery的回调函数定义
 * 在callback中处理返回
 *
 * 函数定义中的函数参数
 *  callback: (data: String, error: String) -> Unit
 *   参数名     函数参数1     函数参数2       返回类型
 *
 *  回调函数返回空值Unit不可省略
 */
fun post(url: String, data: Map<String, String>, callback: (data: String, error: String) -> Unit) {
    callback(url + "?" + data.get("username") + "=" + data.get("password"), "success")
    // 方法调用的另一种形式
//    callback.invoke("回调2", "success")
}
package top.freej.kotlindemo.callback

/**
 * 语法点：
 * 1. 创建map
 *
 * 2. lambda表达式
 *    能访问当前上下文的匿名代码块,在kotlin和python中只能有一行, 可以有参数和返回值,该行的值即为返回值,不能写return
 *
 * 3. 实现类似jquery的回调
 *    在调用时传入的函数如果有多条语句，需要传入匿名函数而不是lambda表达式
 *
 * 4. 调用函数的两种方法
 *    第一种：像普通方法一样 method(arg1,atr2)
 *    第二种：method.invoke(arg1,arg2)
 *
 * 5. 字符串模板
 *    访问复杂对象的属性需要用用大括号, eg: ${obj.attr1}
 *
 */


fun lambdaDemo() {
    val username = "user1"
    val func1 = {
        "I am a lambda, and I can visit current context! username: $username"
    }
    println(func1())

    val func2 = { s1: String, s2: String -> "I am another lambda, and I have to arguments: $s1,$s2" }
    print(func2.invoke("hello", "world"))
}

/**
 *
 * map定义
 *
 * 1. 可变map与不可变map
 * mapOf定义不可变map, 无法增删改, 只能查询
 * mutableMapOf定义可变map, 可以任意增删改查
 *
 *
 * 2. map元素定义方式
 * 方式1: Pair(key, value)
 * 方式2: key to value
 *
 *
 * 3. map遍历
 *
 *
 * 4. 新增
 *    kotlin1.3语法修改,不建议用put,直接使用python类似的字典赋值
 *
 *
 * 5. 获取与修改
 *    类似python字典
 *
 */
fun mapDemo() {
    val map1 = mapOf(Pair("user1", 13), Pair("user2", 14))
    val map2 = mutableMapOf("user3" to 15, "user4" to 16)

    map1.forEach {
        println("${it.key} = ${it.value}")
    }

    map2["user5"] = 17
    map2["user4"] = 14

    map2.keys.forEach(::println)
}

/**
 * 模拟jquery ajax
 *
 * @param url
 * @param data
 * @param callback
 */
fun post(url: String, data: Map<String, String>, callback: (data: Any, error: Any?) -> Unit) {
    println("send ajax request to $url ...")
    println()

    println("request parameters: ")
    data.forEach{
        println("${it.key} = ${it.value}")
    }
    println()
    println("I received the response and start to call the callback ...")
    val result = listOf("student1", "student2", "student3")
    val error = null
    callback(result, error)
}

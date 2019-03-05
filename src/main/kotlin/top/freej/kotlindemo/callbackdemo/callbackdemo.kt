package top.freej.kotlindemo.callback

/**
 * �﷨�㣺
 * ��1������map
 *     mapOf
 * ��2��lambda���ʽֻ��һ�У������ô�����������
 * ��3��ʵ������jquery�Ļص�
 *     �ڵ���ʱ����ĺ�������ж�����䣬��Ҫ������������������lambda���ʽ
 * ��4�����ú��������ַ���
 *      ��һ�֣�����ͨ����һ�� method(arg1,atr2)
 *      �ڶ��֣�method.call(arg1,atg2)
 *
 *
 *
 */
fun main(args: Array<String>) {

    // lambda���ʽ����
    val f: (s1: String, s2: String) -> String = { s1: String, s2: String ->
        (s1 + " " + s2).toUpperCase()
    }

    // ֱ�ӵ���lambda���ʽ
    println(f("hello", "world"))

    // �ص��ж�����䣬����jQuery������������
    post(
        "https://www.freej.top",
        mapOf<String, String>(Pair("username", "fanrj"), Pair("password", "pass123")),
        fun(data: String, error: String) {
            println(data)
            println(error)
        })

    // �ص�ֻ��һ����䣬��lambda���ʽ���˴�lambda���ʽ���Ϳ����ƶϲ���д
    post("https://www.freej.top", mapOf<String, String>(Pair("username", "fanrj"), Pair("password", "pass123")), { data, error -> println(data) })

    //post("https://www.freej.top",mapOf<String,String>(Pair("username","fanrj"),Pair("password", "pass123")),{data:String,error:String-> println(data)})

    // map����
    val m1 = mapOf<String, String>("username" to "fanrj", "password" to "fanrj")
    val m2 = mapOf<String, Int>(Pair("A", 13), Pair("B", 14))

    // map����
    m1.forEach(::println)
    m2.forEach({
        println(it.key + " = " + it.value)
    })

}

fun post(url: String, data: Map<String, String>, callback: (data: String, error: String) -> Unit) {
    callback(url + "?" + data.get("username") + "=" + data.get("password"), "success")
    // �������õ���һ����ʽ
//    callback.invoke("�ص�2", "success")
}
package top.freej.kotlindemo.designpattern

/**
 * 语法点：
 * 1. 可变集合与不可变集合
 *    MutableList、MutableMap、MutableSet等是可变的
 *    List、Set、Map不可变
 *
 * 2. kotlin中类的定义和使用
 *
 *
 *
 *
 * @author fanrj
 *
 *  观察者设计模式kotlin实现
 *
 *  概念： 一个被观察者，多个观察者，当被观察者发生变化时可以通知观察者
 *  作用： 当一个对象发生变化需要主动通知多个对象时可以使用
 *  实例：
 *  （1）swing等gui程序中的事件监听，一个事件源（被观察者）上的事件触发后可以通知多个事件监听器（观察者）执行对应的事件处理方法
 *  （2）统计图（观察者）和统计表（观察者）从同一个数据源（被观察者）获取数据渲染页面，当数据发生改变时，
 *       数据源可以主动通知统计图和统计表重新渲染数据
 *
 *   一个不太恰当的栗子：
 *   一个音乐播放器，在同一个页面上统计各种音乐的播放次数，该数据随时变化，要求实时更新统计图和统计表
 *
 *   传统的做法可能是去做轮询，间隔很短的时间去查询一次数据源，发现数据发生了变化则重新渲染页面
 *   缺点：数据源压力大、请求太多
 *
 *   观察者模式：将需要监听数据变化的对象注册到数据源的观察者列表，当数据源数据发生变化时，将变化推送给所有已注册的观察者
 *
 */

fun main(args:Array<String>) {
    // 初始化数据源，此处需要使用MutableMap，不能用Map，因为Map不可变
    var map : MutableMap<String,Int> = mutableMapOf("pop" to 20, "hip-hop" to 2, "jazz" to 10)
    var dataSource : DataSource = DataSource(map)

    // 注册监听器（观察者）
    dataSource.register(ChartLister())
    dataSource.register(TableLister())

    // 触发改变
    dataSource.changeData("pop",25)
    dataSource.changeData("jazz",12)
}

/**
 * 观察者接口
 */
interface DataChangeEventLister{
    fun render()
}

/**
 * 数据源（被观察者）
 */
class DataSource(var data : MutableMap<String,Int>){
    // 监听器列表
    var listeners:MutableList<DataChangeEventLister> = mutableListOf()
    // 构造方法
    init {
        this.data = data;
    }

    /**
     * 监听器注册方法
     */
    fun register(listener:DataChangeEventLister){
        listeners.add(listener)
    }

    /**
     * 修改数据源方法
     */
    fun changeData(type : String, number : Int){
        // 数据改变
        data.set(type,number)

        // 通知观察者
        listeners.forEach({it->it.render()})

    }
}

/**
 * 监听器实现
 */
class ChartLister : DataChangeEventLister{
    override fun render() {
        println("重新渲染统计图")
    }
}

/**
 * 监听器实现
 */
class TableLister : DataChangeEventLister{
    override fun render() {
        println("重新渲染统计表")
    }
}



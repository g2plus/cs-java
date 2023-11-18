from Employee import Employee
"创建 Employee 类的第一个对象"
emp1 = Employee("Zara", 2000)
"创建 Employee 类的第二个对象"
emp2 = Employee("Manni", 5000)

print(emp1.salary)
#判断是否有某个属性
print(hasattr(emp1, 'salary'))
#私有属性的获取
print(emp1._Employee__site)


# __foo__: 定义的是特殊方法，一般是系统定义名字 ，类似 __init__() 之类的。
#
# _foo: 以单下划线开头的表示的是 protected 类型的变量，即保护类型只能允许其本身与子类进行访问，不能用于 from module import *
#
# __foo: 双下划线的表示的是私有类型(private)的变量, 只能是允许这个类本身进行访问了
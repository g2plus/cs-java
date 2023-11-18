import time  # 引入time模块
#https://www.runoob.com/python/python-date-time.html
ticks = time.time()
#小数
print(ticks)
print("------------------")
#取整数
print((int)(ticks))
print(time.localtime())
print("------------------")
print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
print(time.strftime("%Y-%m-%d", time.localtime()))
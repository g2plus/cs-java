# 联系模块化与导入 自定义函数
from function import func
from function import DateUtils

func.cpcPrint("小呆逼")

print(DateUtils.getCurrentDate())
print(DateUtils.strftime(DateUtils.getCurrentDate(),"%Y-%m-%d"))
# dateStr=input("请输入日期(yyyy-MM-dd):");
# print(f"{dateStr}是{DateUtils.weekNumber(dateStr)}")
print(DateUtils.getCalendar(2017,5))
dateStr1='2017-05-31'
print(f"{dateStr1}是{DateUtils.weekNumber(dateStr1)}")
dateStr2='2023-07-19'
print(f"{dateStr2}是{DateUtils.weekNumber(dateStr2)}")

print(DateUtils.getDays(2023,2))

print(DateUtils.getDay2('2023-01-01', '2023-01-10'))

print(DateUtils.getDay3("2022-01-01","2022-03-01",2))

print(DateUtils.getRecentDays("2023-08-28",5))

print(DateUtils.getRecentLeapYear(2023,5))

print(DateUtils.getRecentYearMonth("2023-08",6))
print(DateUtils.getMonthWithnDays(2023,28))
print(DateUtils.getDay4(2021,7))
print(DateUtils.getMonths("2022-01-01","2025-10-01"))
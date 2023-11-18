import calendar
from datetime import datetime,timedelta

#获取当前日期
def getCurrentDate():
    return datetime.now()

#获取某年某月的日历信息
def getCalendar(year,month):
    return calendar.month(year,month)

#获取几天之后的日期
def getDaysAfter(days_to_add):
    current_date=datetime.now()
    return current_date + timedelta((int)(days_to_add))

#计算两个日期之间的天数
def getDaysBetween(startDate,endDate):
    start=datetime.strptime(date_str, "%Y-%m-%d")
    end=datetime.strptime(date_str, "%Y-%m-%d")
    days_difference = (end_date - start_date).days
    return days_difference

#将时间date转成某种格式
def strftime(date,timeFormat):
    return date.strftime(timeFormat)

#判断今天是星期几
def weekNumber(date_str):
    try:
        # 解析日期字符串
        date_obj = datetime.strptime(date_str, "%Y-%m-%d")
        # 获取星期几（0代表星期一，6代表星期日）
        day_of_week = date_obj.weekday()
        # 将数字转换为星期几的字符串表示
        days = ["星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"]
        day_name = days[day_of_week]
        return day_name
    except ValueError:
        print("日期格式错误,请使用yyyy-MM-dd格式.")

#获取某年某月的所有日期
def getDays(year,month):
    # 获取某年某月的日历对象
    cal = calendar.monthcalendar(year, month)
    # 使用列表解析构建包含所有日期的字符串列表
    all_dates = [f"{year:04d}-{month:02d}-{day:02d}" for week in cal for day in week if day != 0]
    return all_dates

#返回两个日期之间的所有日期
def getDay2(startDate,endDate):
    start_date = datetime.strptime(startDate, "%Y-%m-%d")
    end_date = datetime.strptime(endDate, "%Y-%m-%d")
    current_date = start_date
    all_dates = []
    while current_date <= end_date:
        all_dates.append(current_date.strftime("%Y-%m-%d"))
        current_date += timedelta(1)
    return all_dates

#返回两个日期之间的所有日期为指定星期的日期
def getDay3(startDate,endDate,day):
    start_date = datetime.strptime(startDate, "%Y-%m-%d")
    end_date = datetime.strptime(endDate, "%Y-%m-%d")
    current_date = start_date
    days = []
    while current_date <= end_date:
        if current_date.weekday() == day:  # Monday is represented by 0
            days.append(current_date.strftime("%Y-%m-%d"))
        current_date += timedelta(1)
    return days

#获取近n天的日期
def getRecentDays(dateStr,gap):
    start_date = datetime.strptime(dateStr, "%Y-%m-%d")
    date_list = []
    for i in range(gap):
        date_list.append(start_date.strftime("%Y-%m-%d"))
        start_date -= timedelta(1)
    date_list=date_list[::-1]
    return date_list

#返回近n年的所有年份
def getRecentYears(input_year,gap):
    recent_years = [input_year - i for i in range(gap)]
    return recent_years


#计算是否是平年
def is_leap_year(year):
    return (year % 4 == 0 and year % 100 != 0) or (year % 400 == 0)

#获取近n年为平年的年份
def getRecentLeapYear(input_year,gap):
    recent_leap_years = [year for year in range(input_year -gap, input_year -1) if not is_leap_year(year)]
    return recent_leap_years

#获取近n个月的年月
def getRecentYearMonth(input_str,gap):
    input_date = datetime.strptime(input_str, "%Y-%m")
    recent_months = []
    for i in range(gap):
        year = input_date.year
        month = input_date.month
        recent_months.append(f"{year:04d}-{month:02d}")
        # 获取上一个月的年月
        if month == 1:
            year -= 1
            month = 12
        else:
            month -= 1
        input_date = input_date.replace(year=year, month=month)
    recent_months.reverse()  # 将年月列表反转，使其按升序排列
    return tuple(recent_months)

#放回某年月份有n天的月份 28 29 30 31
def getMonthWithnDays(year,days):
    months_with_n_days = []
    for month in range(1, 13):
        if calendar.monthrange(year, month)[1] == days:
            months_with_n_days.append(month)
    return months_with_n_days

#返回某年某月有多少天
def getDay4(year,month):
    days = calendar.monthrange(year, month)[1]
    return days



def getMonths(start_date_str,end_date_str):
    start_date = datetime.strptime(start_date_str, "%Y-%m-%d")
    end_date = datetime.strptime(end_date_str, "%Y-%m-%d")

    current_date = start_date
    months_between = []

    while current_date <= end_date:
        year = current_date.year
        month = current_date.month
        months_between.append(f"{year}-{month:02d}")

        if month == 12:
            year += 1
            month = 1
        else:
            month += 1

        current_date = current_date.replace(year=year, month=month, day=1)

    return months_between
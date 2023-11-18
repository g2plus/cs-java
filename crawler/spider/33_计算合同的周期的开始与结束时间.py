from datetime import datetime, timedelta
#
# start_date_str = "2020-01-01 00:00:00"
# end_date_str = "2025-12-31 23:59:59"
#
# start_date = datetime.strptime(start_date_str, "%Y-%m-%d %H:%M:%S")
# end_date = datetime.strptime(end_date_str, "%Y-%m-%d %H:%M:%S")
#
# current_date = start_date
# periods = []
#
# while current_date <= end_date:
#     # 获取当月最后一天的日期
#     last_day_of_month = current_date.replace(day=28) + timedelta(days=4)
#     last_day_of_month = last_day_of_month - timedelta(days=last_day_of_month.day)
#
#     period_start = current_date
#     period_end = last_day_of_month.replace(hour=23, minute=59, second=59)
#
#     periods.append((period_start, period_end))
#
#     # 获取下一个月的开始日期
#     next_month = current_date.replace(day=1) + timedelta(days=31)
#     current_date = next_month.replace(day=1, hour=0, minute=0, second=0)
#
# for period_start, period_end in periods:
#     print("周期开始时间:", period_start)
#     print("周期结束时间:", period_end)
#     print("---")



start_date_str = "2020-01-01 00:00:00"
end_date_str = "2025-12-31 23:59:59"

start_date = datetime.strptime(start_date_str, "%Y-%m-%d %H:%M:%S")
end_date = datetime.strptime(end_date_str, "%Y-%m-%d %H:%M:%S")

current_date = start_date
periods = []

while current_date <= end_date:
    last_day_of_month = current_date.replace(day=28) + timedelta(days=4)
    last_day_of_month = last_day_of_month - timedelta(days=last_day_of_month.day)

    period_start = current_date
    period_end = last_day_of_month.replace(hour=23, minute=59, second=59)

    periods.append({
        "month": period_start.strftime("%Y-%m"),
        "start": period_start.strftime("%Y-%m-%d %H:%M:%S"),
        "end": period_end.strftime("%Y-%m-%d %H:%M:%S")
    })

    next_month = current_date.replace(day=1) + timedelta(days=31)
    current_date = next_month.replace(day=1, hour=0, minute=0, second=0)

print(periods)




cal = calendar.month(2023, 10)
# 查看2023年10月的日历
print(cal)

print(calendar.firstweekday())


start_date = datetime(2017, 5, 31)
end_date = datetime(2023, 8, 28)

current_date = datetime.now()
days_difference = (end_date - start_date).days

print("从2017-05-31到2023-08-29共有", days_difference, "天")
days_difference = (current_date - start_date).days
print("从2017-05-31到当前日期共有", days_difference, "天")


days_to_add = input("输入几天: ")


try:
    future_date = current_date + timedelta((int)(days_to_add))
    print("今天的日期是:", current_date.strftime("%Y-%m-%d"))
    print(days_to_add + "天后的日期是:", future_date.strftime("%Y-%m-%d"))
except ValueError:
    print("输入无效！请输入整数。")





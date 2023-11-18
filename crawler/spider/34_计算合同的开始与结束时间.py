from datetime import datetime, timedelta

start_date_str = "2020-01-01 00:00:00"
end_date_str = "2025-12-31 23:59:59"
period_days = 30

start_date = datetime.strptime(start_date_str, "%Y-%m-%d %H:%M:%S")
end_date = datetime.strptime(end_date_str, "%Y-%m-%d %H:%M:%S")

current_date = start_date
periods = []

while current_date <= end_date:
    period_start = current_date
    period_end = current_date + timedelta(days=period_days - 1)

    if period_end > end_date:
        period_end = end_date

    periods.append({
        "month": period_start.strftime("%Y-%m"),
        "start": period_start.strftime("%Y-%m-%d %H:%M:%S"),
        "end": period_end.strftime("%Y-%m-%d %H:%M:%S")
    })

    current_date = period_end + timedelta(days=1)

print(periods)

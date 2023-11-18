from datetime import datetime, timedelta

start_date_str = "2020-01-01 00:00:00"
end_date_str = "2025-12-31 23:59:59"
period_days = 30
periods_per_cycle = 2

start_date = datetime.strptime(start_date_str, "%Y-%m-%d %H:%M:%S")
end_date = datetime.strptime(end_date_str, "%Y-%m-%d %H:%M:%S")

current_date = start_date
periods = []

while current_date <= end_date:
    cycle_start = current_date
    cycle_end = current_date + timedelta(days=period_days * periods_per_cycle, seconds=-1)

    if cycle_end > end_date:
        cycle_end = end_date

    periods.append({
        "month": cycle_start.strftime("%Y-%m"),
        "start": cycle_start.strftime("%Y-%m-%d %H:%M:%S"),
        "end": cycle_end.strftime("%Y-%m-%d %H:%M:%S")
    })

    current_date = cycle_end + timedelta(seconds=1)

print(periods)

import csv
from helper import *
from request_measure import *

# Store measurements in a CSV file
projects_tasks_scenarios = [
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 1},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 5},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 8},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 10},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 20},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 30},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 40},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 50},
    {"endpoint": "projects/tasks", "method": "POST", "num_requests": 100},

    
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 1},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 5},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 8},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 10},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 20},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 30},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 40},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 50},
    # {"endpoint": "projects/tasks", "method": "PUT", "num_requests": 100},

    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 1},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 5},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 8},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 10},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 20},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 30},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 40},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 50},
    {"endpoint": "projects/tasks", "method": "DELETE", "num_requests": 100},
]

csv_file = "csv/projects_tasks_measurements.csv"

with open(csv_file, mode="w", newline="") as file:
    fieldnames = ["endpoint", "method", "nb_requests", "avg_time_per_request", "cpu_usage", "memory_usage"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)

    # Write header
    writer.writeheader()

    # Measure and write data for each scenario
    for scenario in projects_tasks_scenarios:
        measurement = measure_scenario(scenario)
        writer.writerow(measurement)

print(f"Measurements written to {csv_file}")
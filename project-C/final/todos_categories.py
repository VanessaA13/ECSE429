import csv
from helper import *
from request_measure import *

# Store measurements in a CSV file
todos_categories_scenarios = [
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 1},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 5},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 8},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 10},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 20},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 30},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 40},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 50},
    {"endpoint": "todos/categories", "method": "POST", "num_requests": 100},

    
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 1},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 5},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 8},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 10},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 20},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 30},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 40},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 50},
    # {"endpoint": "todos/categories", "method": "PUT", "num_requests": 100},

    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 1},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 5},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 8},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 10},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 20},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 30},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 40},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 50},
    {"endpoint": "todos/categories", "method": "DELETE", "num_requests": 100},
]

csv_file = "csv/todos_categories_measurements.csv"

with open(csv_file, mode="w", newline="") as file:
    fieldnames = ["endpoint", "method", "nb_requests", "avg_time_per_request", "cpu_usage", "memory_usage"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)

    # Write header
    writer.writeheader()

    # Measure and write data for each scenario
    for scenario in todos_categories_scenarios:
        measurement = measure_scenario(scenario)
        writer.writerow(measurement)

print(f"Measurements written to {csv_file}")
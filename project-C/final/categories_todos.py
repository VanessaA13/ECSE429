import csv
from helper import *
from request_measure import *

# Store measurements in a CSV file
categories_todos_scenarios = [
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 1},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 5},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 8},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 10},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 20},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 30},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 40},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 50},
    {"endpoint": "categories/todos", "method": "POST", "num_requests": 100},

    
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 1},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 5},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 8},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 10},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 20},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 30},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 40},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 50},
    # {"endpoint": "categories/todos", "method": "PUT", "num_requests": 100},

    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 1},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 5},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 8},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 10},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 20},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 30},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 40},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 50},
    {"endpoint": "categories/todos", "method": "DELETE", "num_requests": 100},
]

csv_file = "csv/categories_todos_measurements.csv"

with open(csv_file, mode="w", newline="") as file:
    fieldnames = ["endpoint", "method", "nb_requests", "avg_time_per_request", "cpu_usage", "memory_usage"]
    writer = csv.DictWriter(file, fieldnames=fieldnames)

    # Write header
    writer.writeheader()

    # Measure and write data for each scenario
    for scenario in categories_todos_scenarios:
        measurement = measure_scenario(scenario)
        writer.writerow(measurement)

print(f"Measurements written to {csv_file}")
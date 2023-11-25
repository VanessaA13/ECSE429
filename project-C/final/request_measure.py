import requests
import time
import csv
import psutil
import json
from helper import *
import random

BASE_URL = "http://localhost:4567"

list_of_ids = []

# Function to make an HTTP request and measure time, CPU, and memory usage
def measure_request(endpoint, method, data=None):
    url = f"{BASE_URL}/{endpoint}"
    headers = {"Content-Type": "application/json"}

    # Measure start time
    start_time = time.time()

    # Make the HTTP request
    if method == "POST":
        response = requests.post(url, headers=headers, data=data)
        list_of_ids.append(response.json()["id"])
    elif method == "PUT":
        random_element = random.choice(list_of_ids)
        response = requests.put(url + "/"+str(random_element), headers=headers, data=data)
    elif method == "DELETE":
        response = requests.delete(url + "/" + str(list_of_ids.pop()), headers=headers, data=data)
    # Measure end time
    end_time = time.time()

    # Measure CPU and memory usage
    cpu_percent = psutil.cpu_percent()
    memory_percent = psutil.virtual_memory().percent

    # Calculate elapsed time
    elapsed_time = end_time - start_time

    return {
        "endpoint": endpoint,
        "method": method,
        "time": elapsed_time,
        "cpu_usage": cpu_percent,
        "memory_usage": memory_percent,
    }

def measure_relationship_request(endpoint, method, data=None):
    url = f"{BASE_URL}/"
    headers = {"Content-Type": "application/json"}

    entities = endpoint.split("/")

    # Measure start time
    start_time = time.time()

    # Make the HTTP request
    if method == "POST":
        response = requests.post(url+entities[0]+"/1/"+entities[1], headers=headers, data=data)
        list_of_ids.append(response.json()["id"])
    elif method == "DELETE":
        response = requests.delete(url+entities[0]+"/1/"+entities[1] + "/" + str(list_of_ids.pop()), headers=headers, data=data)

    # Measure end time
    end_time = time.time()

    # Measure CPU and memory usage
    cpu_percent = psutil.cpu_percent()
    memory_percent = psutil.virtual_memory().percent

    # Calculate elapsed time
    elapsed_time = end_time - start_time

    return {
        "endpoint": endpoint,
        "method": method,
        "time": elapsed_time,
        "cpu_usage": cpu_percent,
        "memory_usage": memory_percent,
    }

# Function to perform measurements for different scenarios
def measure_scenario(scenario):

    total_time = 0
    total_cpu = 0
    total_memory = 0
    counter = 0

    for i in range(scenario["num_requests"]):
        # print(counter)
        # counter += 1
        title = generate_random_string()
        description = generate_random_string()
        data = json.dumps({"title": title, "description": description})

        # Make a request and collect measurements
        if '/' in scenario["endpoint"]:
            obj = measure_relationship_request(scenario["endpoint"], scenario["method"], data)
        else: 
            obj = measure_request(scenario["endpoint"], scenario["method"], data)
        total_time += obj["time"]
        total_cpu += obj["cpu_usage"]
        total_memory += obj["memory_usage"]

    

    final_obj = {
        "endpoint": scenario["endpoint"],
        "method": scenario["method"],
        "nb_requests": scenario["num_requests"],
        "avg_time_per_request": total_time / scenario["num_requests"],
        "cpu_usage": total_cpu / scenario["num_requests"],
        "memory_usage": total_memory / scenario["num_requests"],
    }

    return final_obj

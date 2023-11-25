import random
import string

def generate_random_string():
    # Set the range for the length of the string
    min_length = 5
    max_length = 9
    string_length = random.randint(min_length, max_length)

    # Generate a random string of the specified length
    random_string = ''.join(random.choices(string.ascii_letters + string.digits, k=string_length))

    return random_string


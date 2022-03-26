# Benjamin Brady
# Student ID: 001160134

# Time complexity of entire program -> O(n^2)
from Distance import *

# G.  Provide an interface for the user to view the status and info (as listed in part F) of any package at any time, and the total mileage traveled by all trucks. (The delivery status should report the package as at the hub, en route, or delivered. Delivery status must include the time.)
# -> O(n)
def interface():
    # Get user input for time and print
    hours = input(f'To find all packages delivered by a certaint time, please give an hour in military time ')
    minutes = input(f'Now please give a minutes ')
    time: datetime = datetime.datetime(2022, 1, 14, int(hours), int(minutes), 0)
    packageTable.print_time_readable(time)

    # Print total mileage
    total_mileage = truck1.miles + truck2.miles + truck3.miles
    print(f'The total mileage of all trucks: {total_mileage}')

    # Get user input and look up by package ID
    print(packageTable.lookup_by_id(input(f'To look up a package please input the package id of the package you would like to find: ')))


load_packages()

deliver_packages(truck1)
deliver_packages(truck3)
deliver_packages(truck2)

packageTable.print_readable()

print(f'~~~~~ PACKAGES WITH DEADLINES ~~~~~')
print(f"{packageTable.lookup_by_id('1')}")
print(f"{packageTable.lookup_by_id('6')}")
print(f"{packageTable.lookup_by_id('13')}")
print(f"{packageTable.lookup_by_id('14')}")
print(f"{packageTable.lookup_by_id('15')}")
print(f"{packageTable.lookup_by_id('16')}")
print(f"{packageTable.lookup_by_id('20')}")
print(f"{packageTable.lookup_by_id('25')}")
print(f"{packageTable.lookup_by_id('29')}")
print(f"{packageTable.lookup_by_id('30')}")
print(f"{packageTable.lookup_by_id('31')}")
print(f"{packageTable.lookup_by_id('34')}")
print(f"{packageTable.lookup_by_id('37')}")
print(f"{packageTable.lookup_by_id('40')}")

interface()


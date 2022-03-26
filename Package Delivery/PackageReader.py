import csv
import datetime

from HashTable import ChainingHashTable
from Truck import Truck
from Package import Package

# Global time variable -> O(1)
global the_time
the_time: datetime = datetime.datetime(2022, 1, 14, 8, 0, 0)

# create objects -> O(1)
packageTable = ChainingHashTable()
truck1 = Truck(1)
truck2 = Truck(2)
truck3 = Truck(3)


# Get full list of packages -> O(1)
def get_package_list():
    return packageTable


# Create/populate package list and populate trucks -> O(n)
def load_packages():
    # Open CSV and create package objects with info
    with open("WGUPS Package File.csv") as packages:
        package_data = csv.reader(packages, delimiter=',')
        num_packages = 0
        for package in package_data:
            id = package[0]
            street_zip = package[1]
            city = package[2]
            state = package[3]
            zip = package[4]
            delivery_deadline = package[5]
            size = package[6] + ' kg'
            note = package[7]
            delivery_status = 'At hub, ETA:'

            package = Package(id, street_zip, city, state, zip, delivery_deadline, size, note, delivery_status)
            packageTable.insert(package.id, package)
            num_packages += 1

            # First truck's delivery: Packages that must be delivered -> O(1)
            if package.id == '13' or package.id == '14' or package.id == '15' or package.id == '16' or package.id == '19' or package.id == '20':
                truck1.add_package(package)
            elif package.id == '25':
                truck3.add_package(package)
            # First truck's delivery: gets all that have a deadline and will at the hub on time  ->O(1)
            elif package.delivery_deadline != 'EOD' and package.note != 'Delayed on flight---will not arrive to depot until 9:05 am':
                truck1.add_package(package)

            # Second truck's delivery: gets packages that need to be delivered together, only on truck 2 and late arrivals ->O(1)
            elif 'Can only be' in package.get_notes():
                truck2.add_package(package)
            elif 'Delayed' in package.get_notes():
                truck2.add_package(package)
            # Thrid Truck delivery: Correct incorrect package details does not happen till 10:20 ->O(1)
            elif 'Wrong address listed' in package.get_notes():
                package.street = '410 S State St'
                package.city = 'Salt Lake City'
                package.state = 'UT'
                package.zip = '84111'
                truck3.add_package(package)
                #print(f'note:{package.note} || truck 3 packages {package} ')
            # Place rest of packages on truck ->O(1)
            else:
                if truck3.numPackages <= truck3.max_cargo:
                    truck3.add_package(package)
                elif truck2.numPackages <= truck2.max_cargo:
                    truck2.add_package(package)
                elif truck1.numPackages <= truck1.max_cargo:
                    truck1.add_package(package)
                else:
                    print(f'package {package.id} was not added to truck')

    # Print results -> O(1)
    print(f'truck 1 was loaded with {len(truck1.cargo)} packages.')
    print(f'truck 2 was loaded with {len(truck2.cargo)} packages.')
    print(f'truck 3 was loaded with {len(truck3.cargo)} packages.')
    print(f'{num_packages} packages loaded in to package table')

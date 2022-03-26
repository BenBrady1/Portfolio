import datetime

import PackageReader


class Truck:
    # Time Complexity O(1)
    def __init__(self, truck_id='truckID'):
        self.truck_id: str = truck_id
        self.cargo = []
        self.numPackages = len(self.cargo)
        self.max_cargo = 15
        self.delivered_packages = 0
        self.speed = 18
        self.miles = 0
        self.status = 'HUB'

    # Time Complexity O(1)
    def __str__(self):
        return

    # Time Complexity O(1)
    def __repr__(self):
        return

    # Time Complexity O(1)
    def get_truck_id(self):
        return self.truck_id

    # Time Complexity O(1)
    def get_num_packages(self):
        return self.numPackages

    # Time Complexity O(1)
    def add_package(self, Package):
        self.cargo.append(Package)
        self.numPackages += 1

    # Time Complexity O(1)
    def get_truck_list(self):
        print(f'Truck {self.truck_id} has {self.numPackages} packages on it.')
        print(self.cargo)
        return self.cargo

    # Time Complexity O(1)
    def remove_from_truck(self, id):
        self.cargo.pop(id)

    # Time Complexity O(n)
    def get_package_address(self, package_id):
        for packages in self.cargo:
            if packages == self.cargo[0]:
                address = (f'{self.cargo[package_id].street},{self.cargo[package_id].zip}')
                return address

        print('could not find package address')

    # Time Complexity O(1)
    def print_out(self):
        print(
            f'~~Truck {self.truck_id} delivered {self.delivered_packages} packages in {self.miles} miles and finished at {PackageReader.the_time} with the following still in the truck: {self.cargo}~~')

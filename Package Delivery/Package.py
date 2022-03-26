import PackageReader


class Package:
    # Time Complexity O(1)
    def __init__(self, id='id', street='street', city='city', state='state', zip='zip',
                 delivery_deadline='delivery_deadline', size='size', note='note', delivery_status='At hub'):
        self.id: str = id
        self.street = street
        self.city = city
        self.state = state
        self.zip = zip
        self.delivery_deadline = delivery_deadline
        self.size = size
        self.note = note
        self.delivery_status = delivery_status
        self.time = PackageReader.the_time

    # Time Complexity O(1)
    def __str__(self):
        return f'|| Package ID: {self.id:<2} || Address: {self.street:<38} {self.city:<16} {self.state:<2}, {self.zip:<5} || Size: {self.size:<5} || Delivery Status: {self.delivery_status:<12} {self.delivery_deadline:<20}'

    # Time Complexity O(1)
    def __repr__(self):
        return f'|| Package ID: {self.id:<2} || Address: {self.street:<38} {self.city:<16}, {self.state:<2}, {self.zip:<5} || Size: {self.size:<5} || Delivery Status: {self.delivery_status:<12} {self.delivery_deadline:<20}'

    # Time Complexity O(1)
    def get_street(self):
        return self.street

    # Time Complexity O(1)
    def get_city(self):
        return self.city

    # Time Complexity O(1)
    def get_state(self):
        return self.state

    # Time Complexity O(1)
    def get_zip(self):
        return self.zip

    # Time Complexity O(1)
    def delivery_deadline(self):
        return self.delivery_deadline

    # Time Complexity O(1)
    def get_size(self):
        return self.size

    # Time Complexity O(1)
    def get_notes(self):
        return self.note

    # Time Complexity O(1)
    def get_delivery_status(self):
        return self.delivery_status

# time complexity O(n)
def deliver_package(truck, id_zip):
    # remove package from truck cargo list -> O(N)
    for i, package in enumerate(truck.cargo):
        if f'{package.street},{package.zip}' == str(id_zip):
            index = i
            # update truck list and package -> O(1)
            truck.remove_from_truck(index)
            truck.numPackages -= 1
            package.delivery_status = 'Delivered at'
            package.delivery_deadline = f'{PackageReader.the_time}'
            package.time = PackageReader.the_time
            print(f'Truck {truck.truck_id} delivered package {package.id} to {package.street},{package.zip} at {PackageReader.the_time}')
            return True


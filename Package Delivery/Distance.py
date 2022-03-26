import PackageReader
from PackageReader import *
from DistanceList import distanceNames, distanceList
from Package import deliver_package


# Prints a readable distance dictionary  ->O(n)
def print_distance_dictionary():
    i = 0
    while i <= 26:
        print(f'{distanceNames[i]}  {distanceList[i]}')
        i += 1


# Gets the closest address ->O(n^2)
def get_next_address(street):
    # https://www.geeksforgeeks.org/python-get-key-from-value-in-dictionary/
    # search distanceNames dicitonary for the key
    for key, value in distanceNames.items():
        if street == value:
            # Use the key to find the distance list
            list = distanceList[key]

            # search the list for the closest stop
            closest_stop = 100
            for mileage in list:
                next_stop = mileage
                if closest_stop > next_stop != 0:
                    closest_stop = next_stop
                    best_stop = distanceNames.get(list.index(closest_stop))
                # print(f'{closest_stop} , {best_stop}')
            return best_stop


# Delivers the package - >O(n^2)
def deliver_packages(truck):
    # set packages to 'en route' ->O(1)
    for i, package in enumerate(truck.cargo):
        package.delivery_status = 'en route'

    # Set start time.
    if truck == truck2:
        PackageReader.the_time = PackageReader.the_time.replace(hour=9, minute=5)
    # makes a list of stops ->O(n^2)
    list = []
    for i, package in enumerate(truck.cargo):
        street = f'{package.street},{package.zip}'
        for key, value in distanceNames.items():
            if street == value:
                # Use the key to find the distance list
                list.append(key)

    # Use list to of index to find the closest stop ->O(n)
    closestIndex = 0
    while len(list) > 0:
        closestdistance = 100
        # deliver package 6 and 25 (its late and has a deadline)
        if truck == truck2 and 13 in list:
            #deliver 6
            closestIndex = 13
            closestdistance = distanceList[0][13]
            truck.miles += closestdistance
            # Calculate how long the drive took: Time = Distance * 60 (for minutes) ->O(1)
            trip = (closestdistance / truck.speed) * 60
            PackageReader.the_time += datetime.timedelta(minutes=trip)
            # pop the stop off the list ->O(n)
            for i, distance in enumerate(list):
                if distance == closestIndex:
                    list.pop(i)
                    print(list)
            # Determines the next closest stop  ->O(n)
        elif truck == truck3 and 24 in list:
            #deliver 25
            closestIndex = 24
            closestdistance = distanceList[13][24]
            truck.miles += closestdistance
            # Calculate how long the drive took: Time = Distance * 60 (for minutes) ->O(1)
            trip = (closestdistance / truck.speed) * 60
            PackageReader.the_time += datetime.timedelta(minutes=trip)

            # Drop off package ->O(1)
            if deliver_package(truck, distanceNames.get(closestIndex)) == True:
                deliver_package(truck, distanceNames.get(closestIndex))
                truck.delivered_packages += 1
            elif deliver_package(truck, distanceNames.get(closestIndex)) == False:
                print(f'distance {distanceNames.get(closestIndex)} was not delivered')

            # pop the stop off the list ->O(n)
            for i, distance in enumerate(list):
                if distance == closestIndex:
                    list.pop(i)
                    print(list)
            # Determines the next closest stop  ->O(n)
            continue
        else:
            for i, distance in enumerate(distanceList[closestIndex]):
                if distance <= closestdistance and i in list:
                    closestIndex = i
                    closestdistance = distance

        # Go to closest stop ->O(1)
        truck.miles += closestdistance
        # Calculate how long the drive took: Time = Distance * 60 (for minutes) ->O(1)
        trip = (closestdistance / truck.speed) * 60
        PackageReader.the_time += datetime.timedelta(minutes=trip)

        # Drop off package ->O(1)
        if deliver_package(truck, distanceNames.get(closestIndex)) == True:
            deliver_package(truck, distanceNames.get(closestIndex))
            truck.delivered_packages += 1
        elif deliver_package(truck, distanceNames.get(closestIndex)) == False:
            print(f'distance {distanceNames.get(closestIndex)} was not delivered')

        # pop the stop off the list ->O(n)
        for i, distance in enumerate(list):
            if distance == closestIndex:
                list.pop(i)
                #print(list)

        # Go back to hub ->O(1)
        if len(truck.cargo) == 0:
            # Add distance and mileage
            trip_distance = distanceList[closestIndex][0]
            truck.miles += trip_distance
            trip_time = (trip_distance / truck.speed) * 60
            PackageReader.the_time += datetime.timedelta(minutes=trip_time)
            truck.print_out()

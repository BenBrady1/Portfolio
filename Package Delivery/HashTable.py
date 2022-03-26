# C950 - Webinar-1 - Let’s Go Hashing
# W-1_ChainingHashTable_zyBooks_Key-Value.py
# Ref: zyBooks: Figure 7.8.2: Hash table using chaining.
# Modified for Key:Value

# HashTable class using chaining.
class ChainingHashTable:
    # Constructor with optional initial capacity parameter.
    # Assigns all buckets with an empty list.
    def __init__(self, initial_capacity=10):
        # initialize the hash table with empty bucket list entries.
        self.table = []
        for i in range(initial_capacity):
            self.table.append([])

    # Create hash key -> O(1)
    def create_hash_key(self, key):
        return int(key) % len(self.table)

    # Inserts a new item into the hash table.
    def insert(self, key, value):  # does both insert and update
        # get the bucket list where this item will go.
        keyHash = hash(key) % len(self.table)
        keyValue = self.table[keyHash]

        if self.table[keyHash] == None:
            self.table[keyHash] = list([keyValue])
            return True
        # update key if it is already in the bucket
        else:
            for kv in keyValue:
                # print (key_value)
                if kv[0] == key:
                    kv[1] = value
                    return True

        # if not, insert the item to the end of the bucket list.
        key_value = [key, value]
        keyValue.append(key_value)
        return True


    def lookup_by_id(self, keyUseStr):
        # get the bucket list where this key would be.
        bucket = hash(keyUseStr) % len(self.table)
        bucket_list = self.table[bucket]
        # print(bucket_list)

        # search for the key in the bucket list
        for kv in bucket_list:
            if kv[0] == keyUseStr:
                return kv[1]  # value
        return None

    def remove(self, key):
        # get the bucket list where this item will be removed from.
        bucket = hash(key) % len(self.table)
        bucket_list = self.table[bucket]

        # remove the item from the bucket list if it is present.
        for kv in bucket_list:
            if kv[0] == key:
                bucket_list.remove([kv[0], kv[1]])
                print(f'{kv[0]} was removed')
            else:
                print('package could not be removed. No package was not found')

    def update(self, key, value):
        # get the bucket list where this key would be.
        bucket = hash(key) % len(self.table)
        bucket_list = self.table[bucket]

        # search for the key in the bucket list -> O(1)
        for kv in bucket_list:
            if kv[0] == key:
                kv[1] = value
                return kv[1]  # value
        return None

    #Prints full package list with formatting
    def print_readable(self, i=1):
        key = 0
        # Search table -> O(1)
        while key < 10:
            bucket_list = self.table[key]

            # Search each bucket -> O(n)
            for kv in bucket_list:
                print(kv)
            key += 1

    # Prints time number of packages delivered in a time frame
    def print_time_readable(self,time):
        key = 0
        num_packages = 0
        # Search table -> O(1)
        while key < 10:
            bucket_list = self.table[key]
            # Search each bucket -> O(n)
            for kv in bucket_list:
                # Make value a package to access package elemets
                package = kv[1]

                # If the package is before this time then print it
                if package.time < time:
                    print(package)
                    num_packages += 1
            key += 1
        print(f'{num_packages} packages were delivered by {time}')
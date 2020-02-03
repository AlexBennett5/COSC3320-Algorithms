import time

def binarysearch(arr, lo, hi, val):
    while lo <= hi:

        mid = lo + (hi - lo)//2
        if arr[mid] == val:
            return mid
        elif arr[mid] < val:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1

def testarr(size, val):
    arr = []

    for i in range(0,size):
        arr.append(val - 1)

    start = time.time()
    
    for i in range(0, 10000000):
        res = binarysearch(arr, 0, size-1, val)
    
    end = time.time() - start
    fin_time = end * 1000

    print("The time it took for array of size " + str(size) + " is " + str(fin_time) + " milliseconds")

def main():
    testarr(128, 5)
    testarr(512, 5)
    testarr(2048, 5)
    testarr(8192, 5)
    testarr(32768, 5)
    testarr(131072, 5)
    testarr(524288, 5)
    testarr(2097152, 5)

if __name__ == "__main__":
    main()

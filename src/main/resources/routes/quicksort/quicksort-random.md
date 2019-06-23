# Quicksort

Quicksort is an efficient sorting algorithm. Its average case runtime is O(nlog(n)). It works by recursively partitioning
the input array and performing quicksort on the two resulting sub arrays. The input is partition based on the values of
one of the elements in the array (let's call this item k). After partitioning, all items which are less than k will be
moved before k. All items which are greater than k will be moved after k.

Quicksort is an in-place sorting algorithm. A classic quicksort implementation will use O(log(n)) additional space on
average. If we always sort the smaller sub array before the larger sub array, then we are guaranteed to use O(log(n))
additional space.

Quicksort is not a stable sorting algorithm.

The best case scenario for a classic quicksort uses ~nlog(n) compares. This scenario occurs when the partitioning always
splits the array perfectly in half.

The worst case scenario uses ~(n^2)/2 compares. One way that this can happen is if the array is already sorted. In this
scenario, partitioning will always result in creating one sub array of length 0 and one sub array of length m -1, where
m is the length of the sub array being partitioned. To reduce the risk of this occurring, it is important to shuffle the
input array before starting the sorting. 

Duplicate items in the input array can be a big problem for quicksort. If you have lots of duplicates, it can lead to
quadratic runtime. For this reason, this implementation uses 3-way partitioning. With 3-way partitioning, duplicate
values improve performance instead of making it worse.

## Instructions for trying Quicksort

To try it out, click the "Try it" button. All you have to do is enter any number from 0 to 100000000.
An array will be generated with that number of elements in it. Each element will have a random value.
Then this array will be sorted using quicksort!
    
The Quicksort Java class which does the sorting is actually capable of sorting any type which
implements the `Comparable` interface, but I have restricted it to only integers here to make my
life a little easier. :)

## Improvements to Quicksort

There are a number of ways which we could improve this implementation of quicksort:
* Do insertion sort for all sub arrays with around 10 items or less
* When choosing a partitioning element, get the median of a few randomly chosen items. This will increase the likelihood
of evenly partitioning the array

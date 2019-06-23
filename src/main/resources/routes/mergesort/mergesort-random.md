# Mergesort

## About Mergesort

Mergesort is an efficient algorithm for sorting. It takes a "Divide & Conquer" approach to sorting.
This means that it solves the problem by recursively/iteratively dividing up the input and solving
for the smaller subsets of the total input.

Mergesort usually consists of two methods:

* merge(): Merges two sorted sub-arrays into one sorted array
* sort(): Divides the array into two sub-arrays, calls sort() on each one and calls merge() to combine
the results

The runtime of mergesort is O(nlog(n)). This means that is an optimal sorting algorithm, because it
can be proven that sorting requires at least n(lg(n)) compares in the worst case.

Mergesort is a stable sorting algorithm.

## Instructions for trying Mergesort

To try it out, click the "Try it" button. All you have to do is enter any number from 0 to 100000000.
An array will be generated with that number of elements in it. Each element will have a random value.
Then this array will be sorted using mergesort!
    
The Mergesort Java class which does the sorting is actually capable of sorting any type which
implements the `Comparable` interface, but I have restricted it to only integers here to make my
life a little easier. :)

## Improvements to Mergesort

There are some improvements that could be made to this implementation of Mergesort. These include:

* Use Insertion sort for small sub-array sizes (< 7)
* Perform a check before merging to see if the two sub arrays are already sorted
* Avoid copying the array into the auxiliary array for every merge by continuously swapping the
the roles of the auxiliary array and the input array each time you merge

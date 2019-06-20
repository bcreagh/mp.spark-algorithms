# Shellsort

## About Shellsort

Shellsort is a simple but efficient sorting algorithm. It works by iteratively h-sorting
the input with decreasing values of h.

H-sorting is a technique where you take some value of h, and sort the input such that
input[i] < input[i + h] for all values of i. In this implementation of shellsort, I used
insertion sort to perform the h-sorting.

The reason that shellsort is efficient is because for large values of h, you are only
sorting a small number of elements. Insertion sort is efficient at sorting input with a
small number of elements. For small values of h, insertion sort is also efficient because
the input is already almost sorted (because it has already been h-sorted for larger values
of h).

Shellsort relies on an interesting property of h-sorting for its performance. If you
h-sort an array and then k-sort it, the array remains h-sorted. This is true for any values
of h and k.

When deciding what sequence to use for values of h, 3x + 1 is a pretty simple and effective
one. Nobody knows what the most efficient sequence is.

## Instructions for trying Shellsort

To try it out, click the "Try it" button. You must provide an array of integers in JSON format.
For example:

    [
        34,
        6366,
        849,
        8275,
        87,
        980,
        98252,
        32
    ]
    
The Shellsort Java class which does the sorting is actually capable of sorting any type which
implements the `Comparable` interface, but I have restricted it to only integers here to make my
life a little easier. :)

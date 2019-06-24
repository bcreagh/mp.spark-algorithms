# Quickselect

## About Quickselect

Quickselect is an algorithm for finding the kth smallest/largest (depending on the implementation) item in a list of
items. It is based on quicksort partitioning. It is like quicksort, except that after performing the partitioning, it
only looks at one of the two resulting sub arrays - the one which contains the kth smallest/largest item.

Quickselect has O(n) performance in the average case. Like quicksort, however, its worst case performance is O(n^2)

## Instructions for trying Quickselect

To try it out, click the "Try it" button. The input must be in JSON format, and should look something like this:

    {
        "k": 2,
        "input": [
            32,
            9287,
            7787,
            989,
            12,
            3,
            97877,
            898
        ]
    }

As you can see in this example input, two fields are required:
* k: This is a number. Quickselect will return the kth smallest item.
* input: This is the list of integers that Quickselect will be operating on.

Note that k is 0-based. So that means that if you want to find the smallest item, you would set k = 0. If you want to
find the second smallest item, set k = 1, etc. In the example input above Quickselect will return 32, because 32 is the
3rd smallest item.
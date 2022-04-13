# Bloom Filter

**Bloom filter** is a highly space-efficient probabilistic data structure designed to check the set membership. The
price paid for this efficiency is that a Bloom filter is a probabilistic data structure:

- it tells us that the element either definitely is not in the set or may be in the set.

When we design our Bloom filter, it is important that we provide a reasonably accurate value for the expected number of
elements. Otherwise, our filter will return false positives at a much higher rate than desired. Suppose that we created
a filter with a desired false-positive probability of one percent and an expected 5 elements, but then we inserted
100,000 elements. Because the expected number of elements is so small, the filter will occupy very little memory.
However, as we add more items than expected, the filter becomes over-saturated and has a much higher probability of
returning false positive results than the desired percentage.

The **Guava** project is a collection of several of Google’s core libraries for string processing, caching, etc.
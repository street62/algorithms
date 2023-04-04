package org.example.hand_of_straights;

class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> queue = new PriorityQueue<>();
        queue.addAll(Arrays.stream(hand).boxed().collect(Collectors.toSet()));

        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }

        while (queue.size() > 0) {
            int start = queue.peek();

            for (int cur = start; cur < start + groupSize; cur++) {
                if (!map.containsKey(cur)) {
                    return false;
                }
                map.put(cur, map.get(cur) - 1);

                if (map.get(cur) == 0) {
                    if (queue.peek() != cur) {
                        return false;
                    }
                    queue.poll();
                }
            }
        }
        return true;
    }
}
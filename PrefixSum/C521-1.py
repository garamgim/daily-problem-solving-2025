# 정수 N개의 합 2

n, k = map(int, input().split())
arr = list(map(int, input().split()))

# Please write your code here.
prefix_sum = [0] * n
prefix_sum[0] = arr[0]
for i in range(1, n):
    prefix_sum[i] = prefix_sum[i-1] + arr[i]

max_sum = -100 * 100000

for i in range(k, n):
    max_sum = max(max_sum, prefix_sum[i] - prefix_sum[i-k])

print(max_sum)
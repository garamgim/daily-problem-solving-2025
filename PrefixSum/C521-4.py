# 연속한 K개의 숫자
# 연속된 숫자들의 구간에서 어떠한 count의 합의 최솟값을 확인해야 하므로 구간합이 적합

N, K, B = map(int, input().split())
missing = [int(input()) for _ in range(B)]

# Please write your code here.
prefix_sum = [0] * (N + 1)
for num in missing:
    prefix_sum[num] = 1

for i in range(1, N + 1):
    prefix_sum[i] = prefix_sum[i] + prefix_sum[i - 1]
    
min_count = N + 1
for i in range(K, N + 1):
    min_count = min(min_count, prefix_sum[i] - prefix_sum[i - K])

print(min_count)
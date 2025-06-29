# 부분 수열의 합이 K

n, k = map(int, input().split())
arr = list(map(int, input().split()))

# Please write your code here.
prefix_sum = [0] * (n + 1)

prefix_sum[1] = arr[0]

for i in range(2, n+1):
    prefix_sum[i] = prefix_sum[i-1] + arr[i-1]

l, r = 0, 1
cnt = 0

while l < r and r < n+1:
    curr_sum = prefix_sum[r] - prefix_sum[l]
    if curr_sum == k:
        cnt += 1
        r += 1
    elif curr_sum < k:
        r += 1
    else:
        l += 1

print(cnt)
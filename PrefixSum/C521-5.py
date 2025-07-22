# 범위 내에 있는 점의 수 2

n, q = map(int, input().split())
points = list(map(int, input().split()))
ranges = [tuple(map(int, input().split())) for _ in range(q)]

# Please write your code here.
nums = [0] * 1_000_001

for point in points:
    nums[point] = 1

for i in range(1, 1_000_001):
    nums[i] = nums[i] + nums[i-1]

for rg in ranges:
    a, b = rg[0], rg[1]
    if a == 0:
        print(nums[b])  # 숫자 범위 확인
    else:
        print(nums[b] - nums[a - 1])
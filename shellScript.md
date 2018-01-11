#### 문자열 자르기
``` sh
stringZ=abcABC123ABCabc
#       0123456789.....
#       0 부터 시작하는 인덱싱.

echo ${stringZ:0}                            # abcABC123ABCabc
echo ${stringZ:1}                            # bcABC123ABCabc
echo ${stringZ:7}                            # 23ABCabc

echo ${stringZ:7:3}                          # 23A
                                             # 3글자짜리 문자열조각.
```

#!bin/bash
#默认删除30天前-昨天的日志数据
# now-START天至now-END天之间luck-开头的数据
START=30
END=1

if [ $# == 1 ] ;then
        START=$1
fi

if [ $# == 2 ] ;then
	START=$1
        END=$2
fi

START_DATE=`date -d '-'"${START}"' day' +%Y.%m.%d`;

END_DATE=`date -d '-'"${END}"' day' +%Y.%m.%d`;

echo "开始删除${START_DATE}至${END_DATE}的数据"

while [ ${START} -gt ${END}  ]
do
   END_DATE=`date -d '-'"${END}"' day' +%Y.%m.%d`;
   curl -XDELETE http://127.0.0.1:9200/luck-*-${END_DATE}
   echo “"删除${END_DATE}数据..."
   let END++
done


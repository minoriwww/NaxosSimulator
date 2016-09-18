- 可以删去db，此处未使用
- 地图包位于JavaSim/data/文件夹内  
- 地图预览见media/cities/  
- 推荐使用GEN01和GEN03来模拟简单的交通流  
- 主要修改3个文件，位于地图包内，分别是crossings.txt segments.txt map_segments.txt
    - crossing: A B C 表示从A到C需要经过B
    - map_segments.txt : A B C D E 表示从A到B的点C位置，一般为9个点，C为点编号
    - segments.txt : A B C D 表示从A到B，距离为C，方向为D（3点钟方向为0，逆时针旋转）
- GEN01为田字形，可借助理解
- **注意不要漏掉任何一条边，否则会null pointer，但是顺序可以颠倒**



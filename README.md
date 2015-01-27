#video split using hadoop

##This prject will try to split the video to images. The basic ideas is 
1. It is mapper only job;

2. About input, convert normal video fomrat into SequenceFile format in which the key is Text and the value is binary data of video

3. About output, the output will be SequenceFle as well in which the key is Text image id and the value is binary data of image. These image SequenceFile can be used in the following MapReduce Task.

##Code structure:

#####com/edison/spike/mapperonly
In this folder, it is a runnable mapper only job;

#####com/edison/spike/mapper
In this folder, there is VideoSplitMapper.java which are interfaces of our mapper for splitting video.For now, it is not runnable.

#####com/edison/spike/readhdfs/SequenceFileRead.java
This Java class is demo of how to parse sequence file. And it is runnable

#####com/edison/spike/writehdfs/SequenceFileWrite.java
This Java class is demo of how to convert normal binary file to sequence file. And it is runnable.


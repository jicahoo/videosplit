# videosplit
video split using hadoop

##This prject will try to split the video to images. The basic ideas is 
###1. It is mapper only job;
###2. About input, convert normal video fomrat into SequenceFile format in which the key is Text and the value is binary data of video
###3. About output, the output will be SequenceFle as well in which the key is Text image id and the value is binary data of image. These image
###SequenceFile can be used in the following MapReduce Task.

# Android

This is a sample project usage of Handlers, Loopers and Messages.

As you are aware about, that native java doesn't provide a kind of looper for a threads. Here i try to solve the problem 
3 task issue without and with looper mechanism.

WithoutLooper: 
1. If we have to perform 3 task then so performing those task created a runnable object
2. Once created those runnables, added in a queue.
3. Till that queue is not empty call the run method.

In the first approach we have to create our own custom logic to create the runnable queue.

WithLooper:
1. If we do same problem with Looper then we don't need to create our own custom runnable queue.
2. We have to just create a Handler class object and attached a looper to it.
3. Once we added the task in Handler's Post then it will automatically take care, each of runnable object. 

Please look out the examples which i have created, If it's doesn't look good to you,
please reach out to me at tushargarg2201@gmail.com

# Android

Here i have made a Service request of 3 types.

1. Through IntentService
2. Through JobIntentService
3. Through NormalService.

1. IntentService: IntentService is outdated post and above oreo. Intent service doesn't respect the doze mode. For Intent service we
also need to acquire wakelock.

2. JobIntentService: JobIntentService is more preferable for oreo and above. JobIntentService, we don't need to acquire the wakelock and it's
respect the doze mode which is used for improving the battery performance. For JobIntentService we put the jobs in a queue. 
Here i have done communication from service to UI layer through ResultReceiver. ResultReceiver is a fantastic generic class which is given
by Android for interprocess communication. Object of ResultReciever we can pass it through intent which is a key here.

3. NormalService: Normal service which extends Service. It's a called started service. Here communication between Service to UI have been done 
through ResultReceiver class. By default it's don't create a seperate thread similar to IntentService and JobIntentService. Here by default
work is done in the main Thread. Here we have to create our seperate thread through HandlerThread.

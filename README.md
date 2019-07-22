# README #

#### Summary ####
* I've followed the 3 stories from the challenge in order, treating them as individual releases and creating new branches for each. You will find them tagged as "v1.0-story[x]". If you check out these tagged commits, you will find the app in the state that each story describes.  
* Since there was no network required, the challenge was faster to work on. I could have simply made the app to directly add messages to the view, but I thought it was a better idea to structure the app as if network calls were made. I did this to give you an idea how I would build a real app and I used Google's MVVM architecture for it.  
* Since this challenge is simple, there is only one ViewModel called `MessagesViewModel` which takes in user input, sends it to the repository and recevies a random response from the repository. The random response is simulated to be received after 1 second. In a real app, the sent message would be sent to the server by the `AppRepository`, and there would need to be a confirmation from the server that the sent message was received by the server. I did not overcomplicate my solution with this requirement, but I am aware that this would be a requirement in a real app.  

#### Tests ####
* I've written an instrumented test for 
    - sending message
    - receiving message
    - validation of phone number field
    - check that the missed response alert dialog appears after 4 seconds of inactivity

Ali Kazi   
[LinkedIn](linkedin.com/in/mdalikazi)  

# VoiceChatAPI
A Voice Chat API made for CheatBreaker+, built to make life easier on server developers.

## How this API works

In this API there are a few useful methods that you can put into your network. Some examples are as followed:

   Creates a voice channel that will be joinable:

```java
VoiceChatAPI.getInstance().createVoice(voicechannel);
``` 
   Deletes a voice channel if it exists by the name inputted:
   
```java
VoiceChatAPI.getInstance().deleteVoice(voicechannel);
``` 

   Filters through all available voice channels and returns the one with the name that you input:
   
```java
VoiceChatAPI.getInstance().getVoiceChannel(voicechannel);
``` 
   
   Adds any player to a voice channel with the name inputted (Player MUST be online):
   
```java
VoiceChatAPI.getInstance().addToChannel(player, voicechannel);
```
   
   This setting must be enabled with the CheatBreaker+ Bukkit API to implement custom voice channels into your server:
   
```java
CheatBreakerAPI.getInstance().getVoiceChatHandler().setVoiceEnabled(true);
```
   
   
    
   

    
    

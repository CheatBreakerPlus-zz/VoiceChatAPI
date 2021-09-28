package com.oldcheatbreaker.voice;

import com.oldcheatbreaker.api.CheatBreakerAPI;
import com.oldcheatbreaker.api.voice.VoiceChannel;
import com.oldcheatbreaker.voice.listeners.VoiceChatListener;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class VoiceChatAPI extends JavaPlugin {
    @Getter
    @Setter
    private static VoiceChatAPI instance;

    @Override
    public void onEnable() {
        setInstance(this);
        saveDefaultConfig();

        CheatBreakerAPI.getInstance().getVoiceChatHandler().setVoiceEnabled(true);
        if (this.getConfig().getBoolean("channel-on-join"))
            createVoice(this.getConfig().getString("public-voice-name"));
        getServer().getPluginManager().registerEvents(new VoiceChatListener(), this);

    }

    @Override
    public void onDisable() {
        if (this.getConfig().getBoolean("channel-on-join"))
            deleteVoice(this.getConfig().getString("public-voice-name"));
    }

    public void addToChannel(String channel, Player player) {
        VoiceChannel voiceChannel = VoiceChatAPI.getInstance().getVoiceChannel(channel);
        if (voiceChannel != null) {
            voiceChannel.addPlayer(player);
            CheatBreakerAPI.getInstance().getVoiceChatHandler().setActiveChannel(player, voiceChannel);
        }
    }

    public void createVoice(String name) {
        final VoiceChannel channel = new VoiceChannel(name);
        CheatBreakerAPI.getInstance().getVoiceChatHandler().createBulkChannels(channel);
    }

    public void deleteVoice(String name) {
        VoiceChannel channel = getVoiceChannel(name);
        if (channel != null) CheatBreakerAPI.getInstance().getVoiceChatHandler().deleteChannel(channel);
    }

    public VoiceChannel getVoiceChannel(String name) {
        for (VoiceChannel channel : CheatBreakerAPI.getInstance().getVoiceChatHandler().getVoiceChannels()) {
            if (channel.getName().equalsIgnoreCase(name)) return channel;
        }
        return null;
    }
}

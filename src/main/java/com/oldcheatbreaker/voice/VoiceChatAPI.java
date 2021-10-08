package com.oldcheatbreaker.voice;

import com.oldcheatbreaker.api.handler.VoiceChatHandler;
import com.oldcheatbreaker.api.voice.VoiceChannel;
import com.oldcheatbreaker.voice.listener.VoiceChatListener;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class VoiceChatAPI extends JavaPlugin {

    private VoiceChatHandler voiceChatHandler;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getPluginManager().registerEvents(new VoiceChatListener(), this);

        this.voiceChatHandler.setVoiceEnabled(true);

        if (this.getConfig().getBoolean("channel-on-join")) {
            this.createVoice(this.getConfig().getString("public-voice-name"));
        }
    }

    @Override
    public void onDisable() {
        if (this.getConfig().getBoolean("channel-on-join")) {
            this.deleteVoice(this.getConfig().getString("public-voice-name"));
        }
    }

    public void addToChannel(String channel, Player player) {
        VoiceChannel voiceChannel = VoiceChatAPI.getInstance().getVoiceChannel(channel);

        if (voiceChannel == null)
            return;

        voiceChannel.addPlayer(player);
        this.voiceChatHandler.setActiveChannel(player, voiceChannel);
    }

    public void createVoice(String name) {
        this.voiceChatHandler.createBulkChannels(
                new VoiceChannel(name)
        );
    }

    public void deleteVoice(String name) {
        VoiceChannel channel = getVoiceChannel(name);

        if (channel != null) {
            this.voiceChatHandler.deleteChannel(channel);
        }
    }

    public VoiceChannel getVoiceChannel(String name) {
        return this.voiceChatHandler.getVoiceChannels().stream()
                .filter(voiceChannel -> voiceChannel.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public static VoiceChatAPI getInstance() {
        return JavaPlugin.getPlugin(VoiceChatAPI.class);
    }

}

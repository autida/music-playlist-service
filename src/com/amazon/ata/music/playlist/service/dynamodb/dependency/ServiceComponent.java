package com.amazon.ata.music.playlist.service.dynamodb.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {
    CreatePlaylistActivity provideCreatePlaylistActivity();
    GetPlaylistActivity provideGetPlaylistActivity();
    UpdatePlaylistActivity provideUpdatePlaylistActivity();
    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();
    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();
    PlaylistDao providePlaylistDao();
    AlbumTrackDao provideAlbumTrackDao();
}

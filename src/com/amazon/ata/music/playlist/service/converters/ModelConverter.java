package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
        import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongModel;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
        import java.util.List;
        import java.util.Set;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {

        return PlaylistModel.builder()
                .withId(playlist.getId())
                .withCustomerId(playlist.getCustomerId())
                .withName(playlist.getName())
                .withTags(playlist.getTags())
                .build();
    }

    public SongModel toSongModel(AlbumTrack albumTrack) {
        return SongModel.builder()
                .withAsin(albumTrack.getAsin())
                .withTrackNumber(albumTrack.getTrackNumber())
                .withAlbum(albumTrack.getAlbumName())
                .withTitle(albumTrack.getSongTitle())
                .build();
    }

    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
        List<SongModel> albums = new ArrayList<>();
        for(AlbumTrack albumTrack: albumTracks) {
            albums.add(toSongModel(albumTrack));
        }
        return albums;
    }
}

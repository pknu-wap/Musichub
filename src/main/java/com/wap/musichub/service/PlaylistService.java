package com.wap.musichub.service;

import com.wap.musichub.domain.entity.PlaylistEntity;
import com.wap.musichub.domain.repository.JpaPlaylistRepository;
import com.wap.musichub.dto.PlaylistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private JpaPlaylistRepository playlistRepository;

    @Autowired
    public PlaylistService(JpaPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Transactional
    public Long savePlaylist(PlaylistDto playlistDto) {
        return playlistRepository.save(playlistDto.toPlaylistEntity()).getId();
    }

    @Transactional
    public List<PlaylistDto> getList() {
        List<PlaylistEntity> Entities = playlistRepository.findAll();
        List<PlaylistDto> DtoList = new ArrayList<>();

        for (PlaylistEntity entity : Entities) {
            PlaylistDto dto = PlaylistDto.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .build();

            DtoList.add(dto);
        }

        return DtoList;
    }

    @Transactional
    public PlaylistDto getById(Long id) {
        Optional<PlaylistEntity> EntityWrapper = playlistRepository.findById(id);
        PlaylistEntity entity = EntityWrapper.get();

        PlaylistDto dto = PlaylistDto.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .createDate(entity.getCreateDate())
                .build();

        return dto;
    }

    @Transactional
    public void deleteById(Long id) {
        playlistRepository.deleteById(id);
    }

    //검색 관련 서비스
    public List<PlaylistDto> searchPlaylist(String keyword) {
        List<PlaylistEntity> playlistEntities = playlistRepository.findByTitleContaining(keyword);
        List<PlaylistDto> playDtoList = new ArrayList<>();

        if (playlistEntities.isEmpty()) return playDtoList;

        for (PlaylistEntity playlistEntity : playlistEntities) {
            playDtoList.add(this.convertEntityToDto(playlistEntity));
        }

        return playDtoList;
    }

    private PlaylistDto convertEntityToDto(PlaylistEntity boardEntity) {
        return PlaylistDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .build();
    }
}

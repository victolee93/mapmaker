package com.mapmaker.service;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.domain.repository.TravelRepository;
import com.mapmaker.dto.TravelDto;
import com.mapmaker.util.JsonManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;
    private MarkerRepository markerRepository;
    private LikeService likeService;

    @Transactional
    public List<TravelDto> getRecentTravelList(UserEntity userEntity){
        Page<TravelEntity> page = travelRepository.findAll(PageRequest.of(0, 9, Sort.by(Sort.Direction.ASC, "createdDate")));
        return getDtoListByAddingField(page.getContent(), userEntity);
    }

    @Transactional
    public List<TravelDto> getPopularTravelList(UserEntity userEntity){
        List<TravelEntity> travelEntities = travelRepository.findByTravelLikesOrderByDesc();
        return getDtoListByAddingField(travelEntities, userEntity);
    }

    @Transactional
    public List<TravelDto> getTravelListByUser(UserEntity userEntity){
        List<TravelEntity> travelEntityList = travelRepository.findAllByUserEntity(userEntity);
        List<TravelDto> travelDtoList = new ArrayList<>();

        if (travelEntityList.isEmpty()) {
            return travelDtoList;
        }

        for(TravelEntity travelEntity : travelEntityList) {
            TravelDto travelDto = convertEntityToDto(travelEntity);
            travelDto.setUserEntity(userEntity);
            travelDtoList.add(travelDto);
        }

        return travelDtoList;
    }

    @Transactional
    public List<TravelDto> searchTravelList(String keyword){
        List<TravelEntity> travelEntityList = travelRepository.findByTitleContaining(keyword);
        List<TravelDto> travelDtoList = new ArrayList<>();

        if (travelEntityList.isEmpty()) {
            return travelDtoList;
        }

        for (TravelEntity travelEntity : travelEntityList) {
            TravelDto travelDto = convertEntityToDto(travelEntity);
            travelDto.setUserName(travelEntity.getUserEntity().getNickname());

            travelDtoList.add(convertEntityToDto(travelEntity));
        }

        return travelDtoList;
    }

    @Transactional
    public String getTravelInfoByMarker(Long no){
        String travelInfoJson = "";
        Optional<MarkerEntity> markerEntityWrapper = markerRepository.findById(no);

        if (markerEntityWrapper.isEmpty() == true) {
            return travelInfoJson;
        }

        TravelEntity travelEntity = markerEntityWrapper.get().getTravelEntity();
        TravelDto travelDto = convertEntityToDto(travelEntity);

        travelInfoJson = JsonManager.convertDtoToJson(travelDto);
        return travelInfoJson;
    }

    @Transactional
    public TravelDto getTravelInfo(Long no){
        Optional<TravelEntity> travelEntityWrapper = travelRepository.findById(no);

        return convertEntityToDto(travelEntityWrapper.get());
    }

    @Transactional
    public String getTravelInfoJson(Long no){
        TravelDto travelDto = getTravelInfo(no);
        return JsonManager.convertDtoToJson(travelDto);
    }

    private List<TravelDto> getDtoListByAddingField(List<TravelEntity> travelEntities, UserEntity userEntity) {
        List<TravelDto> travelDtoList = new ArrayList<>();

        for (TravelEntity travelEntity : travelEntities) {
            TravelDto travelDto = convertEntityToDto(travelEntity);

            travelDto.setUserName(travelEntity.getUserEntity().getNickname());
            travelDto.setLikeCount(travelEntity.getTravelLikes().size());
            travelDto.setCommentCount(travelEntity.getTravelComments().size());
            travelDto.setChecked(likeService.isUserCheckedTravelLike(userEntity, travelEntity));

            travelDtoList.add(travelDto);
        }
        return travelDtoList;
    }

    private TravelDto convertEntityToDto(TravelEntity travelEntity) {
        return TravelDto.builder()
                .id(travelEntity.getId())
                .title(travelEntity.getTitle())
                .periodStartDate(travelEntity.getPeriodStartDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .periodEndDate(travelEntity.getPeriodEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .description(travelEntity.getDescription())
                .foodInfo(travelEntity.getFoodInfo())
                .placeInfo(travelEntity.getPlaceInfo())
                .lodgingInfo(travelEntity.getLodgingInfo())
                .transportInfo(travelEntity.getTransportInfo())
                .cost(travelEntity.getCost())
                .totalReview(travelEntity.getTotalReview())
                .memo(travelEntity.getMemo())
                .filePath(travelEntity.getFilePath())
                .openStatus(travelEntity.getOpenStatus())
                .createdDate(travelEntity.getCreatedDate())
                .build();
    }
}

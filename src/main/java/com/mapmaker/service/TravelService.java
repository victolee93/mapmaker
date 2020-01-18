package com.mapmaker.service;

import com.mapmaker.domain.entity.MarkerEntity;
import com.mapmaker.domain.entity.Travel.TravelEntity;
import com.mapmaker.domain.entity.UserEntity;
import com.mapmaker.domain.repository.MarkerRepository;
import com.mapmaker.domain.repository.Travel.TravelRepository;
import com.mapmaker.dto.Travel.TravelDto;
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


@Service
@AllArgsConstructor
public class TravelService {
    private TravelRepository travelRepository;
    private MarkerRepository markerRepository;
    private LikeService likeService;

    // 최근 여행 리스트
    @Transactional
    public List<TravelDto> getRecentList(UserEntity userEntity) {
        Page<TravelEntity> page = travelRepository.findAll(PageRequest.of(0, 9, Sort.by(Sort.Direction.ASC, "createdDate")));
        return getDtoListByAddingField(page.getContent(), userEntity);
    }

    // 인기 여행 리스트
    @Transactional
    public List<TravelDto> getPopularList(UserEntity userEntity) {
        List<TravelEntity> travelEntities = travelRepository.findByTravelLikesOrderByDesc();
        return getDtoListByAddingField(travelEntities, userEntity);
    }

    // 현재 유저의 여행 리스트
    @Transactional
    public List<TravelDto> getListByUser(UserEntity userEntity) {
        List<TravelEntity> travelEntities = travelRepository.findAllByUserEntity(userEntity);
        List<TravelDto> travelDtoList = new ArrayList<>();

        if (travelEntities.isEmpty()) {
            return travelDtoList;
        }

        for (TravelEntity travelEntity : travelEntities) {
            TravelDto travelDto = convertEntityToDto(travelEntity);
            travelDto.setUserEntity(userEntity);
            travelDtoList.add(travelDto);
        }

        return travelDtoList;
    }

    // 제목을 기준으로 여행 검색
    @Transactional
    public List<TravelDto> searchTravels(String keyword) {
        List<TravelEntity> travelEntities = travelRepository.findByTitleContaining(keyword);
        List<TravelDto> travelDtoList = new ArrayList<>();

        if (travelEntities.isEmpty()) return travelDtoList;

        for (TravelEntity travelEntity : travelEntities) {
            TravelDto travelDto = convertEntityToDto(travelEntity);
            travelDto.setUserName(travelEntity.getUserEntity().getNickname());

            travelDtoList.add(convertEntityToDto(travelEntity));
        }

        return travelDtoList;
    }

    // 여행 상세정보
    @Transactional
    public TravelDto getDetail(Long no) {
        TravelEntity travelEntity = travelRepository.findById(no).get();

        return convertEntityToDto(travelEntity);
    }

    // 여행 상세정보를 Json으로 반환
    @Transactional
    public String getDetailJson(Long no) {
        TravelDto travelDto = this.getDetail(no);

        return JsonManager.convertDtoToJson(travelDto);
    }

    // 마커 번호로 여행 상세정보 가져오기
    @Transactional
    public String getDetailJsonByMarker(Long no) {
        MarkerEntity markerEntity = markerRepository.findById(no).get();
        TravelEntity travelEntity = markerEntity.getTravelEntity();
        TravelDto travelDto = convertEntityToDto(travelEntity);

        return JsonManager.convertDtoToJson(travelDto);
    }

    // TravelEntity에 정의된 필드 외에 필요한 정보를 Dto에 추가하는 메서드
    private List<TravelDto> getDtoListByAddingField(List<TravelEntity> travelEntities, UserEntity userEntity) {
        List<TravelDto> travelDtoList = new ArrayList<>();

        for (TravelEntity travelEntity : travelEntities) {
            TravelDto travelDto = convertEntityToDto(travelEntity);

            travelDto.setUserName(travelEntity.getUserEntity().getNickname());
            travelDto.setLikeCount(travelEntity.getTravelLikes().size());
            travelDto.setLikeChecked(likeService.isUserCheckedTravelLike(userEntity, travelEntity));
            travelDto.setCommentCount(travelEntity.getTravelComments().size());

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

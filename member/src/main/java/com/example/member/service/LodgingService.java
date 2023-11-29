package com.example.member.service;

import com.example.member.dto.LodgingDto;
import com.example.member.entity.ItemImg;
import com.example.member.entity.Lodging;
//import com.example.member.repository.ItemImgRepository;
import com.example.member.entity.Member;
import com.example.member.repository.LodgingRepository;
import com.example.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class LodgingService {

    private final LodgingRepository lodgingRepository;
    private final MemberRepository memberRepository;

//    private final ItemImgService itemImgService;
//
//    private final ItemImgRepository itemImgRepository;

//    public Long saveItem(LodgingDto lodgingDto, List<MultipartFile> itemImgFileList) throws Exception{
        public Long saveItem(LodgingDto lodgingDto, String email) throws Exception{
        //상품 등록
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(EntityNotFoundException::new);

        Lodging lodging = Lodging.toLodging(member, lodgingDto);
        lodgingRepository.save(lodging);

        //이미지등록
//        for(int i=0; i<itemImgFileList.size();i++ ){
//            ItemImg itemImg = new ItemImg();
//            itemImg.setItem(lodging);//해당 이미지 객체에 상품 정보를 연결
//            if(i == 0)
//                itemImg.setRepimgYn("Y"); //이미지넘버가 0 이면 대표이미지
//            else
//                itemImg.setRepimgYn("N");
//            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
//        }
        return lodging.getId();
    }

    public List<LodgingDto> lodgingDtos() {
            List<Lodging> lodgingList = lodgingRepository.findAll();

            List<LodgingDto> lodgingDtoList = new ArrayList<>();

            for (Lodging lodging : lodgingList) {
                LodgingDto lodgingDto = LodgingDto.toLodgingDto(lodging);
                lodgingDtoList.add(lodgingDto);
            }

            return lodgingDtoList;
    }

    // master 브렌치에서 단 주석입니다.

}

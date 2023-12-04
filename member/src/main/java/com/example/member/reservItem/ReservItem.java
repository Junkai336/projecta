package com.example.member.reservItem;

import com.example.member.entity.BaseEntity;
import com.example.member.entity.Lodging;
import com.example.member.entity.Member;
import com.example.member.entity.Room;
import com.example.member.reserv.Reserv;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.security.Principal;

@Entity
@Getter
@Setter
public class ReservItem extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "reserv_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lodging_id")
    private Lodging lodging;
    // 하나의 숙소는 여러 예약의 숙소으로 들어갈 수 있으므로
    // 예약 숙소 기준으로 다대일 단방향 매핑을 설정함

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reserv_id")
    private Reserv reserv;
    // 한 번의 주문에 여러개의 숙소를 예약할 수 있으므로
    // 예약 숙소 엔티티와 예약 엔티티를 다대일 단방향 매핑


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    private String reservPrice; // 주문가격

    // 예약한 숙소의 정보를 reservItem에 넣기
    public static ReservItem createReservItem(Room room){
        ReservItem reservItem = new ReservItem();
        reservItem.setRoom(room);
        reservItem.setReservPrice(room.getPrice());
        return reservItem;
    }
}

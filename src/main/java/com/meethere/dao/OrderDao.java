package com.meethere.dao;

import com.meethere.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderDao extends JpaRepository<Order,Integer> {
    List<Order> findByUserID(String userID);

    Order findByOrderID(int orderID);

    Page<Order> findAllByState(int State,Pageable pageable);

    @Query(value = "select * from `order` where state = ?1 or state = ?2 ", nativeQuery = true)
    List<Order> findAudit(int state1,int state2);

    Page<Order> findAllByUserID(String userID, Pageable pageable);
    @Transactional
    @Modifying
    @Query(value="update `order` o set o.state=?1 where o.orderID=?2",nativeQuery =true)
    void updateState(int state, int orderID);
}

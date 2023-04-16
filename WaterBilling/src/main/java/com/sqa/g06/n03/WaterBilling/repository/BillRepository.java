package com.sqa.g06.n03.WaterBilling.repository;

import com.sqa.g06.n03.WaterBilling.entity.Bill;
import com.sqa.g06.n03.WaterBilling.model.BillDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface BillRepository extends JpaRepository<Bill, Integer> {
    // thông tin lịch sử đóng tiền
    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.BillDTO(b, p.paymentDate)" +
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id WHERE b.client.id = :client_id")
    Page<BillDTO> findBillsByClientId(@Param("client_id") String clientId, Pageable pageable);

    // thông tin lịch sử nhưng trả / chưa trả
    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.BillDTO(b, p.paymentDate)" +
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id WHERE b.client.id = :client_id AND b.status = :status")
    Page<BillDTO> findBillsByClientIdAndStatus(@Param("client_id") String clientId, boolean status, Pageable pageable);
}

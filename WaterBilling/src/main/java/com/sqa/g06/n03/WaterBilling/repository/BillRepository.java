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
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id WHERE b.client.id = :client_id order by b.year desc, b.month desc")
    Page<BillDTO> findBillsByClientId(@Param("client_id") String clientId, Pageable pageable);

    // thông tin lịch sử nhưng trả / chưa trả
    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.BillDTO(b, p.paymentDate)" +
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id WHERE b.client.id = :client_id AND b.status = :status order by b.year desc, b.month desc")
    Page<BillDTO> findBillsByClientIdAndStatus(@Param("client_id") String clientId, boolean status, Pageable pageable);

    // tim bang status
    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.BillDTO(b, p.paymentDate)" +
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id WHERE  b.status = :status order by b.year desc, b.month desc")
    Page<BillDTO> findBillsByStatus(boolean status, Pageable pageable);

    @Query(value = "SELECT new com.sqa.g06.n03.WaterBilling.model.BillDTO(b, p.paymentDate)" +
            " FROM Bill b LEFT JOIN Payment p ON b.id = p.bill.id order by b.year desc, b.month desc")
    Page<BillDTO> findBills(Pageable pageable);

    @Query("SELECT b FROM Bill b WHERE b.month = :month AND b.year = :year AND b.client.id = :client_id")
    Bill findOneBill(@Param("month") int month, @Param("year") int year,  @Param("client_id") String client_id)
;}

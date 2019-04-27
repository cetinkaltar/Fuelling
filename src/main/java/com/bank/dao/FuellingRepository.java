package com.bank.dao;

import com.bank.model.Fuelling;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by cetin on 4/23/2019.
 */
public interface FuellingRepository extends CrudRepository<Fuelling, Long> {
    @Query(value = "SELECT  YEAR(DATE),\n" +
            "    SUM(CASE MONTH(DATE) WHEN 1 THEN (PRICE*VOLUME) END) AS Jan,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 2 THEN (PRICE*VOLUME) END) AS Feb,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 3 THEN (PRICE*VOLUME) END) AS Mar,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 4 THEN (PRICE*VOLUME) END) AS Apr,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 5 THEN (PRICE*VOLUME) END) AS May,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 6 THEN (PRICE*VOLUME) END) AS Jun,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 7 THEN (PRICE*VOLUME) END) AS Jul,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 8 THEN (PRICE*VOLUME) END) AS Agu,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 9 THEN (PRICE*VOLUME) END) AS Sep,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 10 THEN (PRICE*VOLUME) END) AS Oct,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 11 THEN (PRICE*VOLUME) END) AS Nov,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 12 THEN (PRICE*VOLUME) END) AS Dec\n" +
            "FROM FUELLING\n" +
            "GROUP BY YEAR(DATE)", nativeQuery = true)
    List<String> findMonthlyExpense();

    @Query(value = "SELECT  YEAR(DATE),\n" +
            "    SUM(CASE MONTH(DATE) WHEN 1 THEN (PRICE*VOLUME) END) AS Jan,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 2 THEN (PRICE*VOLUME) END) AS Feb,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 3 THEN (PRICE*VOLUME) END) AS Mar,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 4 THEN (PRICE*VOLUME) END) AS Apr,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 5 THEN (PRICE*VOLUME) END) AS May,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 6 THEN (PRICE*VOLUME) END) AS Jun,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 7 THEN (PRICE*VOLUME) END) AS Jul,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 8 THEN (PRICE*VOLUME) END) AS Agu,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 9 THEN (PRICE*VOLUME) END) AS Sep,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 10 THEN (PRICE*VOLUME) END) AS Oct,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 11 THEN (PRICE*VOLUME) END) AS Nov,\n" +
            "    SUM(CASE MONTH(DATE) WHEN 12 THEN (PRICE*VOLUME) END) AS Dec\n" +
            "FROM FUELLING\n" +
            "WHERE DRIVER_ID =:driverID\n" +
            "GROUP BY YEAR(DATE)", nativeQuery = true)
    List<String> findMonthlyExpense(@Param("driverID") int driverID);
    
    @Query(value = "SELECT * ,(PRICE*VOLUME) AS EXPENSE FROM FUELLING\n" +
            "WHERE MONTH(DATE)=:monthIndex\n" +
            "GROUP BY YEAR(DATE), FUELLING.ID", nativeQuery = true)
    List<Fuelling> findFuellingsByMonth(@Param("monthIndex") int monthIndex);

    @Query(value = "SELECT * ,(PRICE*VOLUME) AS EXPENSE FROM FUELLING\n" +
            "WHERE MONTH(DATE)=:monthIndex AND DRIVER_ID =:driverID\n" +
            "GROUP BY YEAR(DATE), FUELLING.ID", nativeQuery = true)
    List<Fuelling> findFuellingsByMonth(@Param("monthIndex") int monthIndex, @Param("driverID") int driverID);
    
    @Query(value = "SELECT  YEAR(DATE),MONTH(DATE),FUEL_TYPE ,\n" +
            "SUM(VOLUME) AS TOTAL_VOLUME,\n" +
            "SUM((PRICE*VOLUME)) AS TOTAL_PRICE\n" +
            "FROM FUELLING\n" +
            "GROUP BY YEAR(DATE),MONTH(DATE),FUEL_TYPE ", nativeQuery = true)
    List<String>findMonthlyStatistics();

    @Query(value = "SELECT  YEAR(DATE),MONTH(DATE),FUEL_TYPE ,\n" +
            "SUM(VOLUME) AS TOTAL_VOLUME,\n" +
            "SUM((PRICE*VOLUME)) AS TOTAL_PRICE\n" +
            "FROM FUELLING\n" +
            "WHERE DRIVER_ID =:driverID\n" +
            "GROUP BY YEAR(DATE),MONTH(DATE),FUEL_TYPE ", nativeQuery = true)
    List<String>findMonthlyStatistics(@Param("driverID") int driverID);

}

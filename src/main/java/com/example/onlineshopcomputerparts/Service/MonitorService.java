package com.example.onlineshopcomputerparts.Service;

import com.example.onlineshopcomputerparts.DTO.MonitorDTO;
import java.util.Collection;

/**
 * Сервис Монитора
 */
public interface MonitorService {

  /**
   * Добавление в ноутбук Монитора
   * @param monitorDTO
   * @return
   */
  MonitorDTO add(MonitorDTO monitorDTO);

  /**
   * Изменение данных Монитора
   * @param id
   * @param diagonal
   * @param serialNumber
   * @param manufacturer
   * @param price
   * @param quantity
   * @return
   */
  MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Мониторах в магазине
   * @return
   */
  Collection<MonitorDTO> findAll();

  /**
   * Поиск Монитора по идентификатору
   * @param id
   * @return
   */
  MonitorDTO findById(Long id);
}

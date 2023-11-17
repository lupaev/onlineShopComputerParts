package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import java.util.Collection;

/**
 * Сервис Монитора
 */
public interface MonitorService {

  /**
   * Добавление в ноутбук Монитора
   */
  MonitorDTO add(MonitorDTO monitorDTO);

  /**
   * Изменение данных Монитора
   */
  MonitorDTO patch(Long id, Integer diagonal, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Мониторах в магазине
   */
  Collection<MonitorDTO> findAll();

  /**
   * Поиск Монитора по идентификатору
   */
  MonitorDTO findById(Long id);
}

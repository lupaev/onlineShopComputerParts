package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.MonitorDTO;
import com.example.onlineshopcomputerparts.dto.MonitorFullDTO;

import java.util.Collection;

/**
 * Сервис Монитора
 */
public interface MonitorService {

  /**
   * Добавление в ноутбук Монитора
   */
  MonitorFullDTO add(MonitorDTO monitorDTO);

  /**
   * Изменение данных Монитора
   */
  MonitorFullDTO patch(Long id, MonitorDTO monitorDTO);

  /**
   * Данные о всех Мониторах в магазине
   */
  Collection<MonitorFullDTO> findAll();

  /**
   * Поиск Монитора по идентификатору
   */
  MonitorFullDTO findById(Long id);
}

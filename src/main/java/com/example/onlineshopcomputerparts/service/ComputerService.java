package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import java.util.Collection;

/**
 * Сервис Компьютера
 */

public interface ComputerService {

  /**
   * Добавление в магазин Компьютера
   */
  ComputerDTO add(ComputerDTO computerDTO);

  /**
   * Изменение данных Компьютера
   */
  ComputerDTO patch(Long id, String form, Integer serialNumber, String manufacturer,
      Double price, Integer quantity);

  /**
   * Данные о всех Компьютерах в магазине
   */
  Collection<ComputerDTO> findAll();

  /**
   * Поиск Компьютера по идентификатору
   */
  ComputerDTO findById(Long id);
}

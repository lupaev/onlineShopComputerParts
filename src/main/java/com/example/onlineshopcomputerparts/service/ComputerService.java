package com.example.onlineshopcomputerparts.service;

import com.example.onlineshopcomputerparts.dto.ComputerDTO;
import com.example.onlineshopcomputerparts.dto.ComputerFullDTO;

import java.util.Collection;

/**
 * Сервис Компьютера
 */

public interface ComputerService {

  /**
   * Добавление в магазин Компьютера
   */
  ComputerFullDTO add(ComputerDTO computerDTO);

  /**
   * Изменение данных Компьютера
   */
  ComputerFullDTO patch(Long id, ComputerDTO computerDTO);

  /**
   * Данные о всех Компьютерах в магазине
   */
  Collection<ComputerFullDTO> findAll();

  /**
   * Поиск Компьютера по идентификатору
   */
  ComputerFullDTO findById(Long id);
}

package com.than.go_out_api.tour;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1/tour")
@Slf4j
public class TourController {
  private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(1);
  private final Map<Integer, Tour> tourInMemDb;
  private final Logger logger = LoggerFactory.getLogger(TourController.class);

  public TourController() {
    tourInMemDb = new HashMap<>();
  }

  @GetMapping
  public List<Tour> getAllTours() {
    logger.info("get all tours");
    return tourInMemDb.values().stream().toList();
  }

  @GetMapping("/{id}")
  public Tour getTourById(@PathVariable("id") int id) {
    logger.info("get tour by id: {}", id);
    return Optional.ofNullable(tourInMemDb.get(id))
        .orElseThrow(() -> {
          logger.error("tour id: {} not found", id);
          return new RuntimeException("not found");
        });
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Tour addTour(@RequestBody Tour tour) {
    var id = ATOMIC_INTEGER.getAndIncrement();
    var newTour = new Tour(id, tour.getTitle(), tour.getMaxPeople());
    tourInMemDb.put(id, newTour);
    logger.info("Create new tour: {}", newTour);
    return tourInMemDb.get(id);
  }

  @PutMapping("/{id}")
  public Tour updateTour(@PathVariable("id") int id, @RequestBody Tour tour) {
    var updatedTour = new Tour(id, tour.getTitle(), tour.getMaxPeople());
    tourInMemDb.put(id, updatedTour);
    logger.info("Update tour: {}", updatedTour);
    return tourInMemDb.get(id);
  }

  @DeleteMapping("/{id}")
  public String deleteTour(@PathVariable("id") int id) {
    tourInMemDb.remove(id);
    logger.info("Delete tour: {}", id);
    return "Deleted tour: " + id;
  }
}

package com.oak.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oak.entities.Placement;
import com.oak.repositories.PlacementRepo;

@Service("placementService")
public class PlacementService {

	@Autowired
	PlacementRepo placementRepo;

	public List<Placement> getPlacements() {

		return placementRepo.getPlacements();

	}

	public Placement getPlacementById(String id) {

		return placementRepo.getPlacementById(id);
	}

	public void deletePlacementById(String id) {

		placementRepo.deletePlacementById(id);
	}

	@Transactional
	public void createPlacement(Placement placement) {

		placementRepo.createPlacement(placement);

	}

	@Transactional
	public void updatePlacement(Placement placement) {

		placementRepo.updatePlacement(placement);
	}

}
